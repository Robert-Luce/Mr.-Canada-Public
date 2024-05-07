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
			Path path = Paths.get("MrCanadaData\\Users\\" + this.username);
			if (!Files.exists(path)) {
				Files.createDirectories(path);
				this.password = new Password(this.username);
				this.password.newPassword(inputPassword);
			} else {
				this.password = new Password(this.username);
				if (!this.password.checkPassword(inputPassword)) {
					throw new Exception();
				}
			}
		
	}

	public boolean hasCriteria() {
		try {
			this.criterias = new ArrayList<String>(Arrays.asList(Files.readString(Path.of(Path.of("MrCanadaData\\Criteria Test.txt").toAbsolutePath().toString())).split("\r\n")));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}