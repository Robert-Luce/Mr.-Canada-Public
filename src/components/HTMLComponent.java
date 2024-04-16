package components;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HTMLComponent extends JComponent {
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private String fileName;
	private String filePath;
	protected JFrame frame;
	private String fileData;
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

		if (this.fileData.contains("<img src=>")) {
			this.absolutePath = this.absolutePath.replace(" ", "%20");
			this.absolutePath = this.absolutePath.replace(".html", ".png");
			this.fileData = this.fileData.replace("<img src=>", "<img src=\"file:///" + this.absolutePath + "\">");
		}
		this.label.setText(this.fileData);
		this.panel.add(this.label);
	}

	public void open() {
		this.frame.add(this.panel);
	}

	public void close() {
		this.frame.remove(this.panel);
	}

	public void repaint() {
		super.repaint();
	}
}
