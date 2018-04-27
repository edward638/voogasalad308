package gamePlayer;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.dropbox.core.DbxException;

public class test {
	
	public static void main(String[] args) {
		VoogaDropbox vd = new VoogaDropbox();
		try {
			vd.uploadGame("hello");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DbxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
