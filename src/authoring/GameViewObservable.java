package authoring;

import java.util.List;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public interface GameViewObservable {

	List<ImageView> getImageViews();
	
	List<SceneBackgroundImage> getBackgroundImages();
}
