package user;

import java.io.FileReader;
import java.io.File;
import java.io.FileWriter;

public class Password {
	private String username;

	public Password(String username) {
		this.username = username;
	}

	public boolean checkPassword(String password) {
		try {
			File passwordFile = new File("MrCanadaData\\Users\\" + this.username + "\\password.txt");
			Hash inputHash = new Hash(password);
			FileReader reader = new FileReader(passwordFile);
			int data = reader.read();
			String fileHash = "";
			while (data != -1) {
				fileHash += (char) data;
				data = reader.read();
			}
			reader.close();
			return fileHash.equals(inputHash.getHash());
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
			System.out.println("Error making new password");
		}
	}
}
