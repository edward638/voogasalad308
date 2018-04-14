package engine.collision;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class SubtractiveRectangleExtrapolator implements ShapeExtrapolator {
	int resolution = 8;

	@Override
	public Shape extrapolateShape(ImageView imageview) {
		Rectangle s = new Rectangle(imageview.getBoundsInLocal().getMinX(), imageview.getBoundsInLocal().getMinY(),
				imageview.getBoundsInLocal().getWidth(), imageview.getBoundsInLocal().getHeight());
		Shape result = s;
		
		Image image = imageview.getImage();
		
		int widthStep = (int) s.getX()/resolution;
		int heightStep = (int) s.getY()/resolution;
		Rectangle empty = new Rectangle(imageview.getBoundsInLocal().getMinX(), imageview.getBoundsInLocal().getMinY(),
				widthStep, heightStep);
		
		PixelReader px = image.getPixelReader();
		
		for (int i = widthStep/2; i < s.getX(); i += widthStep) {
			for (int j = widthStep/2; j < s.getY(); j += widthStep) {
				if (px.getArgb(i, j)>>24 == 0) {
					empty.setX(i-widthStep/2);
					empty.setY(j-heightStep/2);
					result = Shape.subtract(result, empty);
				}
			}
		}
		return result;
	}
	
}
