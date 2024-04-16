import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class User {
	private String username;
	private Password password;

	public User(String username, String inputPassword) {
		this.username = username;
		try {
			Path path = Paths.get(this.username);
			if (!Files.exists(path)) {
				Files.createDirectories(path);
				this.password = new Password(this.username);
				this.password.newPassword(inputPassword);
			} else {
				this.password = new Password(this.username);
				if (!this.password.checkPassword(inputPassword)) {
					System.out.println("Incorrect password");
				}
			}
		} catch (Exception e) {
			System.out.println("Error: " + e);
		}
	}

}