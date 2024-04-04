package components;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HTMLComponent {
	private JPanel panel;
	private String fileName;
	private String filePath;
	private JFrame frame;
	private String fileData;
	private Path path;
	private JLabel label;
	/**
	 * @param fileName
	 * @param filePath
	 * @param frame
	 */
	public HTMLComponent(String fileName, String filePath, JFrame frame) {
		this.panel = new JPanel();
		this.filePath = filePath;
		this.fileName = fileName;
		this.frame = frame;
		this.path = Path.of("MrCanadaData\\Pages\\" + this.filePath + "\\" + this.fileName);
		try {
			this.fileData = Files.readString(this.path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.label = new JLabel();
		this.label.setText(this.fileData);
        this.panel.add(this.label);
        System.out.println(this.fileData);
	}
	public void open() {
		this.frame.add(this.panel);
	}
	public void close() {
		this.frame.remove(this.panel);
	}
}
