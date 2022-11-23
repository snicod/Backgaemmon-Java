package view;

import gamifier.model.GameElement;
import gamifier.view.ElementLook;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import model.Pawn;

public class PawnLook extends ElementLook {
    private Circle circle;
    public PawnLook(int radius, GameElement element) {
        super(element);
        Pawn pawn = (Pawn) element;
        circle = new Circle();
        circle.setRadius(radius);
        if (pawn.getColor() == Pawn.PAWN_BLACK) {
            circle.setFill(Color.BLACK);
        }
        else {
            circle.setFill(Color.RED);
        }

        circle.setCenterX(radius);
        circle.setCenterY(radius);
        addShape(circle);
    }
}
