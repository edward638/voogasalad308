package engine.collision;

import javafx.scene.image.Image;
import javafx.scene.shape.Shape;

public class SubtractiveRectangleExtrapolator implements ShapeExtrapolator {
	int resolution = 8;

	@Override
	public Shape extrapolateShape(Image image) {
		int widthStep = (int) image.getWidth()/resolution;
		for (int i = widthStep/2; i < image.getWidth(); i += widthStep);
		image.getPixelReader().getArgb(x, y)
		return null;
	}

}
