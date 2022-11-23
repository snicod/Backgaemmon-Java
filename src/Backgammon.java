import control.ControllerBackgammon;
import gamifier.control.StageFactory;
import gamifier.model.Model;
import gamifier.view.PaneView;
import gamifier.view.SimpleTextView;
import javafx.application.Application;
import javafx.stage.Stage;
import view.BasicView;


public class Backgammon extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Model model = new Model();


        //add some player
//        model.addHumanPlayer("player1");
//        model.addHumanPlayer("player2");

        //uncommant to chose the first ai
        model.addHumanPlayer("player1");
        model.addComputerPlayer("Computer1");
        int aiChoice = 1;

        //uncommant to chose the second ai
//        model.addHumanPlayer("player1");
//        model.addComputerPlayer("Computer2");
//        int aiChoice = 2;



        //register the stage called backgammon
        StageFactory.registerModel("backgammon", "model.BackgammonStageModel");
        StageFactory.registerView("backgammon", "view.BackgammonStageView");

        BasicView view = new BasicView(model, stage);

        SimpleTextView introView = new SimpleTextView(600, 100, "intro", "playing to Backgammon");
        PaneView paneView = new PaneView("gamepane");

        view.addPaneView(introView);
        view.addPaneView(paneView);

        ControllerBackgammon control = new ControllerBackgammon(model, view);

        control.setGamePaneViewName("gamepane");
        control.setFirstStageName("backgammon");
        control.setAi(aiChoice);

        //set the title of the stage
        stage.setTitle("Backgammon");
        //show the JavaFx main stage
        stage.show();
        // set the current view to the intro pane view
        view.setView(introView.getName(), null);

    }
}
