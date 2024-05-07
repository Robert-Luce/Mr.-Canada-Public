package user;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class User {
	private String username;
	private Password password;
	private ArrayList<String> criterias;

	public User(String username, String inputPassword) throws Exception {
		this.username = username;
		this.password = new Password(this.username);
		Path path = Paths.get("MrCanadaData\\Users\\" + this.username);
		if (!Files.exists(path)) {
			Files.createDirectories(path);
			this.password.newPassword(inputPassword);
		} else {
			if (!this.password.checkPassword(inputPassword)) {
				System.out.println(this.password.checkPassword(inputPassword));
				throw new Exception();
			}
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