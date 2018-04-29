package gamePlayer;

import engine.EngineInterface;
import gamePlayer.buttons.ButtonData;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.text.Font;

/**
 * Front end and back end of volume control
 * 
 * @author jeffreyli
 *
 */

public class VolumeSlider extends Slider {
	private ButtonData buttonData;
	private boolean musicOn;
	private double volumeBeforeMuting;
	private double volume;
	private static final double INITIAL_VOLUME = 0.5;
	private static final double MINIMUM_VOLUME = 0;
	private static final double MAXIMUM_VOLUME = 1;
	private EngineInterface engine;
	private Slider slider;
	private Label volumeText;
	private String font = "Din Alternate";

	public VolumeSlider(ButtonData buttonData, EngineInterface engine) {
		musicOn = true;
		volume = INITIAL_VOLUME;
		this.buttonData = buttonData;
		this.engine = engine;
		setupVolumeSlider();
		setupVolumeText();
	}

	private void setupVolumeSlider() {
		slider = new Slider(MINIMUM_VOLUME, MAXIMUM_VOLUME, INITIAL_VOLUME);
		slider.setLayoutX(1080);
		slider.setLayoutY(417);
		slider.setMaxWidth(130);
		slider.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
				volume = new_val.doubleValue();
				volumeBeforeMuting = volume;
				if (musicOn) {
					System.out.println(musicOn);
					if (engine != null) {
						engine.setVolume(volume);
					}
				}
			}
		});
	}

	private void setupVolumeText() {
		volumeText = new Label("Volume: ");
		volumeText.setFont(Font.font(font, 20));
		volumeText.setLayoutX(970);
		volumeText.setLayoutY(410);
	}

	public void toggleMusic() {
		if (musicOn) {
			if (engine != null)
				engine.setVolume(0);
		} else {
			volume = volumeBeforeMuting;
			if (engine != null)
				engine.setVolume(volume);
		}
		musicOn = !musicOn;
	}

	public Boolean getMusicOn() {
		return musicOn;
	}

	public void setEngine(EngineInterface engine) {
		this.engine = engine;
		engine.setVolume(volume);
	}

	public Slider getSlider() {
		return slider;
	}

	public Label getVolumeText() {
		return volumeText;
	}
}
