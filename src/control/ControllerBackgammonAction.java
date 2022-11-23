package control;

import gamifier.control.Controller;
import gamifier.control.ControllerAction;
import gamifier.model.GameException;
import gamifier.model.Model;
import gamifier.view.View;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import view.BasicView;

public class ControllerBackgammonAction extends ControllerAction implements EventHandler<ActionEvent> {
    private BasicView basicView;

    public ControllerBackgammonAction(Model model, View view, Controller control) {
        super(model, view, control);

        basicView = (BasicView) view;

        setMenuHandlers();
    }

    private void setMenuHandlers() {

        basicView.getMenuStart().setOnAction(e -> {
            try {
                control.startGame();
                ConsoleMode consoleMode = new ConsoleMode(model, control, view);
                consoleMode.playATurn();
            }
            catch(GameException err) {
                System.err.println(err.getMessage());
                System.exit(1);
            }
        });
        // set event handler on the MenuIntro item
        basicView.getMenuIntro().setOnAction(e -> {
            try {
                control.stopGame();
                basicView.setView("intro",null);
            }
            catch(GameException err) {
                System.err.println(err.getMessage());
                System.exit(1);
            }
        });
        // set event handler on the MenuQuit item
        basicView.getMenuQuit().setOnAction(e -> {
            System.exit(0);
        });
    }

    @Override
    public void handle(ActionEvent event) {
        if(!model.isCaptureActionEvent()) return;
    }
}
