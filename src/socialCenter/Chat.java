package socialCenter;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Chat extends Application{

	private TextArea messages = new TextArea();
	
	private Parent createContent() {
		
		TextField input = new TextField();
		VBox root = new VBox(20, messages, input);
		return root;
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setScene(new Scene(createContent()));
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
