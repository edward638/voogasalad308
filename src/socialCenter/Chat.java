package socialCenter;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.User;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Chat extends Application {

	private TextArea messages = new TextArea();

	private Parent createContent() {

		// TextField input = new TextField();
		// VBox root = new VBox(20, messages, input);
		Button b = new Button("hi");
		b.setOnAction(event -> {
			String domain = "https://www2.cs.duke.edu/courses/compsci308/current/";
			String appId = "184863348812033";

			String authUrl = "https://graph.facebook.com/oauth/authorize?type=user_agent&client_id=" + appId
					+ "&redirect_uri=" + domain + "&scope=user_about_me,"
					+ "user_actions.books,user_actions.fitness,user_actions.music,user_actions.news,user_actions.video,user_activities,user_birthday,user_education_history,"
					+ "user_events,user_photos,user_friends,user_games_activity,user_groups,user_hometown,user_interests,user_likes,user_location,user_photos,user_relationship_details,"
					+ "user_relationships,user_religion_politics,user_status,user_tagged_places,user_videos,user_website,user_work_history,ads_management,ads_read,email,"
					+ "manage_notifications,manage_pages,publish_actions,read_friendlists,read_insights,read_mailbox,read_page_mailboxes,read_stream,rsvp_event";

			System.setProperty("webdirver.chrome.driver", "/chromedriver.exe");

			WebDriver driver = new ChromeDriver();
			driver.get(authUrl);
			String accessToken;
			while (true) {

				if (!driver.getCurrentUrl().contains("facebook.com")) {
					String url = driver.getCurrentUrl();
					accessToken = url.replaceAll(".*#access_token=(.+)&.*", "$1");

					driver.quit();

					FacebookClient fbClient = new DefaultFacebookClient(accessToken);
					User user = fbClient.fetchObject("me", User.class);

					System.out.println(user.getName());
					
				}

			}
		});
		Group root = new Group();
		root.getChildren().add(b);
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
