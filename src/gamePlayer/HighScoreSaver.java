package gamePlayer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class HighScoreSaver {

	public HighScoreSaver() {
		
	}

	public void saveScores(List<Score> highScores, String gameName) throws FileNotFoundException, IOException {
		File file = new File("data/gamedata/games/" + gameName + "/HighScores.txt");
		System.out.println("Absolute path:" + file.getAbsolutePath());
		if (file.exists()) {
			file.delete();
		}
		PrintWriter out = new PrintWriter(file);
		for (Score score : highScores) {
			out.println(score.getPlayerName() + " " + score.getScore());
		}
		out.close();
	}

}
