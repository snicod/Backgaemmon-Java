package gamifier.control;

import gamifier.model.*;
import gamifier.view.*;
import javafx.application.Platform;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.StageStyle;

import java.util.*;

public abstract class Controller {
    protected Model model;
    protected View view;
    protected ControllerAnimation controlAnimation;
    protected ControllerKey controlKey;
    protected ControllerMouse controlMouse;
    protected ControllerAction controlAction;
    protected String gamePaneViewName;
    protected String firstStageName;
    protected Map<GameElement, ElementLook> mapElementLook;
    private boolean inUpdate;
    private boolean firstFrame;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        controlAnimation = new ControllerAnimation(model,view, this);
        gamePaneViewName = "";
        firstStageName = "";
        inUpdate = false;
        firstFrame = true;
    }

    public void setControlKey(ControllerKey controlKey) {
        this.controlKey = controlKey;
    }

    public void setControlMouse(ControllerMouse controlMouse) {
        this.controlMouse = controlMouse;
    }

    public void setControlAction(ControllerAction controlAction) {
        this.controlAction = controlAction;
    }

    public void setGamePaneViewName(String gamePaneViewName) {
        this.gamePaneViewName = gamePaneViewName;
    }
    public void setFirstStageName(String firstStageName) {
        this.firstStageName = firstStageName;
    }

    public void startGame() throws GameException {
        if (firstStageName.isEmpty() || gamePaneViewName.isEmpty()) throw new GameException("The name of the first stage and/or first game view have not been set. Abort");
        System.out.println("START THE GAME");
        startStage(gamePaneViewName, firstStageName);
    }

    public void stopGame() {
        controlAnimation.stopGame();
        model.reset();
    }

    /**
     * Start a stage of the game.
     * This method MUST NOT BE called directly, except in the endStage() overrideen method.
     * @param paneViewName The name of the pane view that is used to render the scene
     * @param stageName The name of the stage, as registered in the StageFactory.
     * @throws GameException
     */
    protected void startStage(String paneViewName, String stageName) throws GameException {
        if (model.isStarted()) stopGame();
        System.out.println("START STAGE "+stageName);
        // create the model of the stage by using the StageFactory
        GameStageModel gameStageModel = StageFactory.createStageModel(stageName, model);
        // create the elements of the stage by getting the default factory of this stage and giving it to createElements()
        gameStageModel.createElements(gameStageModel.getDefaultElementFactory());
        // create the view of the stage by using the StageFactory
        GameStageView gameStageView = StageFactory.createStageView(stageName, gameStageModel);
        // create the looks of the stage (NB: no factory this time !)
        gameStageView.createLooks();
        // start the game, from the model point of view.
        model.startGame(gameStageModel);
        // set the view so that the current pane view can integrate all the looks of the current game stage view.
        view.setView(paneViewName, gameStageView);
        /* CAUTION: since starting the game implies to
           remove the intro pane from root, then root has no more
           children. It seems that this removal causes a focus lost
           which must be set once again in order to catch keyboard events.
        */
        view.getRootNode().setFocusTraversable(true);
        view.getRootNode().requestFocus();

        // create a map of GameElement <-> ElementLook, that helps the controller in its update() method
        mapElementLook = new HashMap<>();
        for(GameElement element : model.getElements()) {
            ElementLook look = gameStageView.getElementLook(element);
            if (look != null) mapElementLook.put(element, look);
        }

       /* set first frame to true so that collision detection is not done
          during the first frame. Indeed, if sprites are just put in a grid
          their location is set to 0,0 until the update() method acutalize them
          by calling setElementLocationToCellCenter(). Nevertheless, this call
          occurs after the detection collision. Thus, this detection must be postponed
          from 1 frame.
         */
        firstFrame = true;
        controlAnimation.startGame();
    }

    /**
     * Execute actions needed at the end of each stage.
     * This method does nothing by default. It can be overridden to "compute" the name of the next game stage
     * and to start it. It may also be used update the model, for example by computing reward points, or somthg else.
     */
    public void endStage() {
        /* Does nothing by default, but can be overridden to "compute" the name of the next game stage
        and to start it. It may also be used update the model, for example by computing reward points, or somthg else.
         */
    }

    /**
     * Execute actions when it is the next player to playAmove
     * By default, this method does nothing because it is useless in sprite games.
     * In board games, it can be overridden in subclasses to compute who is the
     * next player, and then to take actions if needed. For example, a method of the model can be called to update who is the current player.
     * Then, if it is a computer, a Decider object can be used to determine what to playAmove and then to playAmove it.
     */
    public void nextPlayer() {};

    /**
     * Execute actions at the end of the game.
     * This method defines a default behaviour, which is to display a dialog box with the name of the
     * winner and that proposes to start a new game or to quit.
     */
    public void endGame() {
        //System.out.println("END THE GAME");
        String winner = model.getWinnerName();
        String message = "";
        if ("".equals(winner)) {
            message = "Draw game";
        }
        else {
            message = winner+ " wins";
        }
        // disable all events
        model.setCaptureEvents(false);
        // create a dialog
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        // remove the frame around the dialog
        alert.initStyle(StageStyle.UNDECORATED);
        // make it a children of the main game window => it appears centered
        alert.initOwner(view.getStage());
        // set the message displayed
        alert.setHeaderText(message);
        // define new ButtonType to fit with our needs => one type is for Quit, one for New Game
        ButtonType quit = new ButtonType("Quit");
        ButtonType newGame = new ButtonType("New Game");
        // remove default ButtonTypes
        alert.getButtonTypes().clear();
        // add the new ones
        alert.getButtonTypes().addAll(quit, newGame);
        // show the dialog and wait for the result
        Optional<ButtonType> option = alert.showAndWait();
        // check if result is quit
        if (option.get() == quit) {
            System.exit(0);
        }
        // check if result is new game
        else if (option.get() == newGame) {
            try {
                startGame();
            } catch (GameException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
        // abnormal case :-)
        else {
            System.err.println("Abnormal case: dialog closed with not choice");
            System.exit(1);
        }
    }

    /**
     * Update model and view.
     * This method MUST NOT BE called directly, and is only called by the ControllerAnimation
     * at each frame. It is used to update the model and then the view.
     * It must be noticed that the process of updating follows a fixed scheme :
     *   - compute collisions (if it must do something, implies to define the method computeCollisions() in subclasses)
     *   - update all game element of the current game stage,
     *   - update the grid cell of element that are in a grid and that have moved in space, and thus may have changed of cell,
     *   - update the looks of all elements, calling dedicated methods according the type indicators of change (location, look, selection, ...),
     *   - reset the change indicators in elements,
     *   - check if the sage is finished,
     *   - check if the game is finished.
     */
    public void update() {
        if (inUpdate) {
            System.err.println("Abnormal case: concurrent updates");
        }
        inUpdate = true;

        // compute collisions if needed (method that must be overridden in subclasses)
        if (!firstFrame) {
            computeCollisions();
        }
        else {
            firstFrame = false;
        }
        // update the model of all elements :
        mapElementLook.forEach((k,v) -> {
            // get the bounds of the look
            Bounds b = v.getGroup().getBoundsInParent();
            // get the geometry of the grid that owns the element, if it exists
            if (k.getGrid() != null) {
                GridLook look = getElementGridLook(k);
                k.update(b.getWidth(), b.getHeight(), look.getGeometry());
            }
            else {
                k.update(b.getWidth(), b.getHeight(), null);
            }
            // if the element has just been put in a grid (NB: this is different from moving within the same grid)
            if (k.isInGridChanged()) {
                setElementLocationToCellCenter(k);
            }
        });
        // update the grid cells of elements that have moved.
        updateGridCells();
        // update the looks
        view.update();
        // reset changed indicators
        mapElementLook.forEach((k,v) -> {
            k.resetChanged();
        });

        if (model.isEndStage()) {
            controlAnimation.stopGame();
            Platform.runLater( () -> {
                endStage();});
        }
        else if (model.isEndGame()) {
            controlAnimation.stopGame();
            Platform.runLater( () -> {endGame();});
        }

        inUpdate = false;
    }

    /**
     * Collision detection, done at each frame.
     * By default, this method deos nothing because it is useless in board games.
     * For sprite games, it must be overridden in subclasses to define a fixed behaviour for computation collisions
     * whatever be the current game stage. For example, a pacman must always detect collisions with ghosts, gums, ...
     * Nevertheless, since the controller has an access to the name of the current game stage,
     * this method can use it to obtain a behaviour that takes the stage into account.
     */
    public void computeCollisions() {}

    /* ***************************************
       HELPERS METHODS
    **************************************** */

    /**
     * Get the look of a given element
     * @param element the element for which the look is asked.
     * @return an ElementLook object that is the look of the element
     *
     */
    public ElementLook getElementLook(GameElement element) {
        return mapElementLook.get(element);
    }

    /**
     * Get the look of the grid that owns an element
     * @param element the element for which the grid llok is asked.
     * @return an ElementLook object that is the look of the grid that owns the element.
     */
    public GridLook getElementGridLook(GameElement element) {
        return (GridLook) (view.getElementGridLook(element));
    }

    /**
     * Set the location of an element at the center of the cell it is placed.
     * @param element
     */
    public void setElementLocationToCellCenter(GameElement element) {
        if (element.getGrid() == null) return;
        int[] coords = element.getGrid().getElementCell(element); // RECALL: grid is the current grid this element is within
        GridLook gridLook = getElementGridLook(element);
        // get the center of the current cell because we can at least reach this center if Me is not already on it.
        Point2D center = gridLook.getRootPaneLocationForCellCenter(coords[0], coords[1]);
        element.setLocation(center.getX(), center.getY());
    }

    /**
     * Get all visible and clickable elements that are at a given point in the scene coordinate space.
     * @param point the coordinate of a point
     * @return A list of game element
     */
    public List<GameElement> elementsAt(Point2D point) {
        List<GameElement> list = new ArrayList<>();
        for(GameElement element : model.getElements()) {
            if ((element.isVisible()) && (element.isClickable())) {
                ElementLook look = mapElementLook.get(element);
                if ((look != null) && (look.isPointWithin(point))) {
                    list.add(element);
                }
            }
        }
        return list;
    }

    private void updateGridCells() {
        for(GameElement element : model.getElements()) {
            updateGridCell(element);
        }
    }
    private void updateGridCell(GameElement element) {
        GridElement grid = element.getGrid();
        if ( (element.isDetectCellChange()) && (element.isLocationChanged()) && (grid != null)) {
            // get the grid look that owns the element
            GridLook gridLook = (GridLook) mapElementLook.get(grid);
            if (gridLook == null) return;
            // get the cell from the current location of the element
            int[] cell = gridLook.getCellFromRootPaneLocation(element.getX(),element.getY());
            if (cell == null) {
                System.err.println("Abnormal case: element went outside its grid");
                return;
            }
            // move the element to this new cell (if needed, the method will decide)
            grid.moveElement(element, cell[0], cell[1]);
        }
    }

    protected List<GameElement> computeElementCollisions(GameElement element) {
        List<GameElement> list = new ArrayList<>();
        for(GameElement other : model.getElements()) {
            if (other != element) {
                ElementLook look1 = mapElementLook.get(element);
                ElementLook look2 = mapElementLook.get(other);
                if ((look1 != null) && (look2 != null)) {
                    if (look1.collideWith(look2)) list.add(other);
                }
            }
        }
        // bind the collision list to the element so that it can do something with it if needed.
        element.setCollisions(list);
        // return the list so that the stage can do something with it if needed
        return list;
    }

}
