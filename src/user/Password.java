package user;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
/**
 * @author lucerc
 */
public class Password {
	private String username;

	public Password(String username) {
		this.username = username;
	}

	public boolean checkPassword(String password) {
		try {
			Hash inputHash = new Hash(password);
			BufferedReader reader = new BufferedReader(
					new FileReader("MrCanadaData\\Users\\" + this.username + "\\password.txt"));
			String line = reader.readLine();
			reader.close();

			if (inputHash.equals(line)) {
				return true;

			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	public void newPassword(String password) {
		try {
			FileWriter writer = new FileWriter("MrCanadaData\\Users\\" + this.username + "\\password.txt");
			Hash inputHash = new Hash(password);
			writer.write(inputHash.getHash());
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error making new password");
		}
	}
}
