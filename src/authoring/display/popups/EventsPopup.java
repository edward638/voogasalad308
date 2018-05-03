package authoring.display.popups;

import authoring.GameObject;
import authoring.display.controllers.EventsPopupController;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import java.util.List;

/**
 * @author August Ning
 *
 */
public class EventsPopup extends Popup {
	
	List<GameObject> gos;
	List<GameObject> allGos;
	EventsPopupController epuc;
	HBox epuBox;
	
	private static final int xSize = 1260;
	private static final int ySize = 400;
	
	public EventsPopup(List<GameObject> inGos, List<GameObject> allGO) {
		super();
		gos = inGos;
		allGos = allGO;
		epuc = new EventsPopupController(gos, allGos);
		epuBox = new HBox();
		epuBox.setPadding(new Insets(10));
	    epuBox.setSpacing(8);
		epuBox.getChildren().addAll(epuc.getWindows());
		generatePopup();
		open(xSize, ySize);
	}

	@Override
	protected void generatePopup() {
		this.getPane().getChildren().add(epuBox);
	}

	@Override
	protected void mapButtons() {
		// no buttons are being mapped
	}

}
