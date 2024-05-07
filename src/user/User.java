package user;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class User {
	private String username;
	private Password password;

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

}