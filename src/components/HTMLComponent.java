package components;

import java.awt.Graphics;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HTMLComponent extends JComponent {
	protected JPanel panel;
	private String fileName;
	private String filePath;
	protected JFrame frame;
	protected String fileData;
	private Path path;
	protected JLabel label;
	private String absolutePath;

	/**
	 * @param fileName
	 * @param filePath
	 * @param frame
	 */
	public HTMLComponent(String fileName, String filePath, JFrame frame) {
		super();
		this.panel = new JPanel();
		this.label = new JLabel();
		this.filePath = filePath;
		this.fileName = fileName;
		this.frame = frame;
		this.path = Path.of("MrCanadaData\\HTML Files\\" + this.filePath + "\\" + this.fileName);
		this.absolutePath = this.path.toAbsolutePath().toString();
		try {
			this.fileData = Files.readString(this.path);
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (this.fileData.contains("<img src=")) {
			this.absolutePath = this.absolutePath.replace(" ", "%20");
			this.absolutePath = this.absolutePath.replace(".html", ".png");
			this.fileData = this.fileData.replace("<img src=", "<img src=\"file:///" + this.absolutePath + "\"");
		}
		this.label.setText(this.fileData);
		this.panel.add(this.label);
		this.add(this.panel);
	}

	public void open() {
		this.frame.add(this);
		this.repaint();
	}

	public void close() {
		this.frame.remove(this);
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

}
