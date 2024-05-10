package user;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
/**
 * @author lucerc
 */
public class User {
	private String username;
	private Password password;
	private ArrayList<String> criterias;
	private String language;

	public User(String username, String inputPassword, String language) throws Exception {
		this.username = username;
		this.password = new Password(this.username);
		this.language = language;
		Path path = Paths.get("MrCanadaData\\Users\\" + this.username);
		if (!Files.exists(path)) {
			Files.createDirectories(path);
			this.password.newPassword(inputPassword);
		} else {
			if (!this.password.checkPassword(inputPassword)) {
				throw new Exception();
			}
		}
		try {
			FileWriter writer = new FileWriter("MrCanadaData\\Users\\" + this.username + "\\language.txt");
			writer.write(this.language);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error setting language");
		}

	}

	public boolean hasCriteria() {
		try {
			this.criterias = new ArrayList<String>(Arrays.asList(Files.readString(Path
					.of(Path.of("MrCanadaData\\Users\\" + this.username + "criteria.txt").toAbsolutePath().toString()))
					.split("\r\n")));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}