package gamifier.model.animation;

import gamifier.model.Model;
import javafx.geometry.Point2D;

public class LinearMoveAnimation extends MoveAnimation {
    // type of movement : "move/linearprop" = prop. to the distance => factor is a speed in pixel/frame
    //                    "move/linearcst" = constant duration => factor is the duration
    protected double factor;

    public LinearMoveAnimation(Model model, Point2D start, Point2D end, int type, double factor) {
        super(model, start, end);

        if (type == AnimationTypes.getType("move/linearcst")) {
            this.type = type;
            if (factor < frameGap) {
                this.factor = frameGap;
            } else {
                this.factor = factor;
            }
            duration = (int)factor;
        } else if (type == AnimationTypes.getType("move/linearprop")) {
            this.type = type;
            this.factor = factor;
        } else {
            this.type = AnimationTypes.getType("move/linearprop");
            this.factor = 1.0;
        }
    }

    public void computeSteps() {
        double x1 = start.getX();
        double x2 = end.getX();
        double width = x1 < x2 ? x2 - x1 : x1 - x2;

        double y1 = start.getY();
        double y2 = end.getY();
        // the length of the segment
        double length = Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
        double slope = (y2 - y1) / (x2 - x1);
        double orig = -y1 + slope * x1;
        int nbPoints;
        if (type == AnimationTypes.getType("move/linearcst")) {
            nbPoints = duration / frameGap;
        } else {
            nbPoints = (int) (length / factor);
            // knowing the number of steps, the whole duration can be computed.
            duration = nbPoints*frameGap;
        }
        double stepX = width / (double) nbPoints;
        AnimationStep step = new AnimationStep();
        step.addData(start.getX());
        step.addData(start.getY());
        steps.add(step);

        for (int i = 0; i < nbPoints - 1; i++) {
            double xx = x1;
            double yy;
            if (x1 > x2) {
                xx -= i * stepX;
            } else {
                xx += i * stepX;
            }
            yy = -orig + slope * xx;
            step = new AnimationStep();
            step.addData(xx);
            step.addData(yy);
            steps.add(step);
        }
        step = new AnimationStep();
        step.addData(end.getX());
        step.addData(end.getY());
        steps.add(step);
    }
}
