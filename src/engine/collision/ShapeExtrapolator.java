package engine.collision;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Shape;

public interface ShapeExtrapolator {
	public Shape extrapolateShape(Image image, double x, double y, double width, double height);

}
