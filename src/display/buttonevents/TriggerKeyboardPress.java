package display.buttonevents;

import authoring.display.popups.eventspopup.TWKPopup;
import authoring.display.popups.eventspopup.TriggerWindow;

public class TriggerKeyboardPress implements ButtonEvent {
	
	private TriggerWindow tw;

	public TriggerKeyboardPress(TriggerWindow intw) {
		tw = intw;
	}

	@Override
	public void pressed() {
		new TWKPopup(tw);
	}

}
