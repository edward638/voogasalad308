package gamePlayer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Paths;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.restfb.BinaryAttachment;
import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import com.restfb.types.User;

public class FBMainTest {

	public static void main(String[] args) {

		String domain = "https://www2.cs.duke.edu/courses/compsci308/current/";
		String appId = "184863348812033";

		String authUrl = "https://graph.facebook.com/oauth/authorize?type=user_agent&client_id=" + appId
				+ "&redirect_uri=" + domain + "&scope=" + "publish_actions,email,public_profile";

		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

		WebDriver driver = new ChromeDriver();

		driver.get(authUrl);

		String accessToken;
		while (true) {

			if (!driver.getCurrentUrl().contains("facebook.com")) {
				String url = driver.getCurrentUrl();
				accessToken = url.replaceAll(".*#access_token=(.+)&.*", "$1");
				FacebookClient fbClient = new DefaultFacebookClient(accessToken);
				User user = fbClient.fetchObject("me", User.class);
				String currentPath = Paths.get(".").toAbsolutePath().normalize().toString() + "/data/gamedata/games";
				FileInputStream file;
				try {
					file = new FileInputStream(currentPath + "/enginetestmario/description/descriptionImage.jpg");
					FacebookType response = fbClient.publish("me/photos", FacebookType.class,
							BinaryAttachment.with("descriptionImage.jpg", file), Parameter.with("message", "hi jeff"));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				driver.quit();

			}

		}

		// String accessToken =
		// "EAACEdEose0cBAEN3DDSOq1LJx9Ek77gRYIB9vv3Wv8VfiUxlRA4yWoZA8mrELVUyMPp1vjwsmhqoxDzEmgTSUIfVhzawdZACfmjsLn4f596wKxByjUUaIQ69ZBReeHJFixFQaAXYLdE2WorsaaSEIhlS25VFZCeWTD20HU09ZBc5wcsZCyOLtnZBZC7rpow5PqQsdseFjywrhQZDZD";
		//
		// @SuppressWarnings("deprecation")
		// FacebookClient fbClient = new DefaultFacebookClient(accessToken);
		// try {
		// String currentPath = Paths.get(".").toAbsolutePath().normalize().toString() +
		// "/data/gamedata/games";
		//
		// FileInputStream file = new FileInputStream(currentPath +
		// "/enginetestmario/description/descriptionImage.jpg");
		// FacebookType response = fbClient.publish("me/photos", FacebookType.class,
		// BinaryAttachment.with("descriptionImage.jpg", file),
		// Parameter.with("message", "hi jeff"));
		//
		// } catch (FileNotFoundException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		//
	}

}
