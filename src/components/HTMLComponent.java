package components;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HTMLComponent extends JComponent {
	public JPanel getPanel() {
		return panel;
	}

	public JLabel getLabel() {
		return label;
	}

	private JPanel panel;
	private String fileName;
	private String filePath;
	private JFrame frame;
	private String fileData;
	private Path path;
	private JLabel label;
	private String absolutePath;
	private double htmlWidth;
	private double htmlHeight;

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
		this.fileData = new String();
		try {
			this.fileData = Files.readString(Path.of(this.absolutePath));
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
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		String[] splitFileData = this.fileData.split(" ");
		int savedWidthIndex = 0;
		int savedHeightIndex = 0;
		for (int widthIndex = 0; widthIndex < splitFileData.length; widthIndex++) {
			if (splitFileData[widthIndex].contains("width=")) {
				savedWidthIndex = widthIndex;
				break;
			}
		}
		for (int heightIndex = 0; heightIndex < splitFileData.length; heightIndex++) {
			if (splitFileData[heightIndex].contains("height=")) {
				savedHeightIndex = heightIndex;
				break;
			}
		}
		double scaleX = Math.abs((double) this.frame.getContentPane().getWidth() / this.htmlWidth);
		double scaleY = Math.abs((double) this.frame.getContentPane().getHeight() / this.htmlHeight);
		double scale = Math.min(scaleX, scaleY);
		int scaledWidth = (int) (this.htmlWidth * scale);
		int scaledHeight = (int) (this.htmlHeight * scale);
		splitFileData[savedWidthIndex] = "width=\"" + scaledWidth + "\"";
		splitFileData[savedHeightIndex] = "height=\"" + scaledHeight + "\"";
		this.label.setSize(scaledWidth, scaledHeight);
		this.fileData=String.join(" ", splitFileData);
		this.label.setText(this.fileData);
		this.label.update(g);
		this.panel.update(g);
		this.updateSize();
	}

	public void setHtmlWidth(int htmlWidth) {
		this.htmlWidth = (double) htmlWidth;
	}

	public void setHtmlHeight(int htmlHeight) {
		this.htmlHeight = (double) htmlHeight;
	}

	public JFrame getFrame() {
		return frame;
	}

	public int getHTMLWidth() {
		return (int) this.htmlWidth;
	}
	public int getHTMLHeight() {
		return (int) this.htmlHeight;
	}
	public void updateSize() {
		this.htmlHeight = 100;
		this.htmlWidth = 100;
	}
	public void pressed() {
		System.out.println("pressed");
	}


}
