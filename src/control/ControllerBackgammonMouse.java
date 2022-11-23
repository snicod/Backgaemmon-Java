package control;

import gamifier.control.Controller;
import gamifier.control.ControllerMouse;
import gamifier.model.GameElement;
import gamifier.model.Model;
import gamifier.view.View;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;

import java.util.List;

public class ControllerBackgammonMouse extends ControllerMouse implements EventHandler<MouseEvent> {
    public ControllerBackgammonMouse(Model model, View view, Controller control) {
        super(model, view, control);
    }

    @Override
    public void handle(MouseEvent event) {
        if(!model.isCaptureMouseEvent()) return;

        Point2D clic = new Point2D(event.getSceneX(), event.getSceneY());
        List<GameElement> list = control.elementsAt(clic);


    }
}
