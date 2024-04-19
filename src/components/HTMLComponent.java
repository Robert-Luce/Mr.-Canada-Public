package components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import listeners.MouseListeners;

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
	private PageComponent page;

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
			String[] splitFileData = this.fileData.split(" ");
			int savedWidthIndex = 0;
			int savedHeightIndex = 0;
			for (int widthIndex = 0; widthIndex < splitFileData.length; widthIndex++) {
				if (splitFileData[widthIndex].contains("width=")) {
					this.htmlWidth = Integer.valueOf(splitFileData[widthIndex].replaceAll("[^0-9]", ""));
					break;
				}
			}
			for (int heightIndex = 0; heightIndex < splitFileData.length; heightIndex++) {
				if (splitFileData[heightIndex].contains("height=")) {
					this.htmlHeight = Integer.valueOf(splitFileData[heightIndex].replaceAll("[^0-9]", ""));
					break;
				}
			}
		}
		this.label.setText(this.fileData);
		this.panel.add(this.label);
		this.add(this.panel);
		System.out.println((int) this.htmlWidth + "," + (int) this.htmlHeight);
		this.setPreferredSize(new Dimension((int) this.htmlWidth, (int) this.htmlHeight));
	}

	public void open() {
		this.frame.add(this);
		this.frame.addMouseListener(new MouseListeners(this));
		this.repaint();
	}

	public void close() {
		this.frame.remove(this);
		this.repaint();
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
		this.fileData = String.join(" ", splitFileData);
		this.label.setText(this.fileData);
		this.label.update(g);
		this.panel.update(g);
		if (this.htmlHeight == 0) {
			this.htmlHeight = 24;
			return;
		}
		if (this.htmlWidth == 0) {
			this.htmlWidth = 125;
			return;
		}
		this.htmlHeight = scaledHeight;
		this.htmlWidth = scaledWidth;
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
	public void setPage(PageComponent page) {
		this.page = page;
	}
	public PageComponent getPage() {
		return this.page;
	}
	public void pressed() {
		this.page.thumbnailPressed();
		this.close();
		HTMLComponent montreal = new HTMLComponent("Destination Page.html", "Montreal HTML", this.frame);
		montreal.open();
		montreal.repaint();
	}

}
