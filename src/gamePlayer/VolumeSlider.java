package gamePlayer;

import engine.EngineInterface;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * Front end and back end of volume control
 * 
 * @author jeffreyli
 *
 */

public class VolumeSlider extends Slider {
	private boolean musicOn;
	private double volumeBeforeMuting;
	private double volume;
	private static final double INITIAL_VOLUME = 0.5;
	private static final double MINIMUM_VOLUME = 0;
	private static final double MAXIMUM_VOLUME = 1;
	private static final double SLIDER_X = 1080;
	private static final double SLIDER_Y = 427;
	private static final double SLIDER_WIDTH = 130;
	private static final double FONTSIZE = 20;
	private static final double VOLUMETEXT_X = 970;
	private static final double VOLUMETEXT_Y = 420;
	private EngineInterface engine;
	private Slider slider;
	private Label volumeText;
	private String font = "Din Alternate";

	public VolumeSlider(EngineInterface engine) {
		musicOn = true;
		volume = INITIAL_VOLUME;
		this.engine = engine;
		setupVolumeSlider();
		setupVolumeText();
	}

	private void setupVolumeSlider() {
		slider = new Slider(MINIMUM_VOLUME, MAXIMUM_VOLUME, INITIAL_VOLUME);
		slider.setLayoutX(SLIDER_X);
		slider.setLayoutY(SLIDER_Y);
		slider.setMaxWidth(SLIDER_WIDTH);
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
		volumeText.setFont(Font.font(font, FONTSIZE));
		volumeText.setTextFill(Color.WHITE);
		volumeText.setLayoutX(VOLUMETEXT_X);
		volumeText.setLayoutY(VOLUMETEXT_Y);
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
