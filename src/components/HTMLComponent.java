package components;

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
	private String txtFileData;
	private String htmlFileData;
	private JLabel label;
	private String txtAbsolutePath;
	private String pngAbsolutePath;
	private String htmlAbsolutePath;
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
		try {
			this.txtAbsolutePath = Path.of("MrCanadaData\\" + this.filePath + "\\" + this.fileName + ".txt")
					.toAbsolutePath().toString();
		} catch (Exception e) {
			System.out.println("Please add " + "MrCanadaData\\" + this.filePath + "\\" + this.fileName + ".txt");
		}
		try {
		this.pngAbsolutePath = Path.of("MrCanadaData\\" + this.filePath + "\\" + this.fileName + ".png")
				.toAbsolutePath().toString();
		} catch (Exception e) {
			System.out.println("Please add " + "MrCanadaData\\" + this.filePath + "\\" + this.fileName + ".png");
		}
		try {
		this.htmlAbsolutePath = Path.of("MrCanadaData\\" + this.filePath + "\\" + this.fileName + ".html")
				.toAbsolutePath().toString();
		} catch (Exception e) {
			System.out.println("Please add " + "MrCanadaData\\" + this.filePath + "\\" + this.fileName + ".html");
		}
		try {
			this.txtFileData = Files.readString(Path.of(this.txtAbsolutePath));
			this.htmlFileData = Files.readString(Path.of(this.htmlAbsolutePath));
		} catch (Exception e) {
			this.txtFileData = "";
			this.htmlFileData = "";
		}
		String[] splitTXTFileData = this.txtFileData.split(" ");
		for (int widthIndex = 0; widthIndex < splitTXTFileData.length; widthIndex++) {
			if (splitTXTFileData[widthIndex].contains("width=")) {
				this.htmlWidth = Integer.valueOf(splitTXTFileData[widthIndex].replaceAll("[^0-9]", ""));
				break;
			}
		}
		for (int heightIndex = 0; heightIndex < splitTXTFileData.length; heightIndex++) {
			if (splitTXTFileData[heightIndex].contains("height=")) {
				this.htmlHeight = Integer.valueOf(splitTXTFileData[heightIndex].replaceAll("[^0-9]", ""));
				break;
			}
		}

		if (this.htmlFileData.contains("<img src=")) {
			this.pngAbsolutePath = this.pngAbsolutePath.replace(" ", "%20");
			this.htmlFileData = this.htmlFileData.replace("<img src=",
					"<img src=\"file:///" + this.pngAbsolutePath + "\"");

		}
		this.label.setText(this.htmlFileData);
		this.panel.add(this.label);
		this.add(this.panel);
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
//		String[] splitFileData = this.fileData.split(" ");
//		int savedWidthIndex = 0;
//		int savedHeightIndex = 0;
//		for (int widthIndex = 0; widthIndex < splitFileData.length; widthIndex++) {
//			if (splitFileData[widthIndex].contains("width=")) {
//				savedWidthIndex = widthIndex;
//				break;
//			}
//		}
//		for (int heightIndex = 0; heightIndex < splitFileData.length; heightIndex++) {
//			if (splitFileData[heightIndex].contains("height=")) {
//				savedHeightIndex = heightIndex;
//				break;
//			}
//		}
//		double scaleX = Math.abs((double) this.frame.getContentPane().getWidth() / this.htmlWidth);
//		double scaleY = Math.abs((double) this.frame.getContentPane().getHeight() / this.htmlHeight);
//		double scale = Math.min(scaleX, scaleY);
//		int scaledWidth = (int) (this.htmlWidth * scale);
//		int scaledHeight = (int) (this.htmlHeight * scale);
//		splitFileData[savedWidthIndex] = "width=\"" + scaledWidth + "\"";
//		splitFileData[savedHeightIndex] = "height=\"" + scaledHeight + "\"";
//		this.label.setSize(scaledWidth, scaledHeight);
//		this.fileData = String.join(" ", splitFileData);
		this.label.setText(this.htmlFileData);
		this.label.update(g);
		this.panel.update(g);
//		if (this.htmlHeight == 0) {
//			this.htmlHeight = 24;
//			return;
//		}
//		if (this.htmlWidth == 0) {
//			this.htmlWidth = 125;
//			return;
//		}
//		this.htmlHeight = scaledHeight;
//		this.htmlWidth = scaledWidth;
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
	}
}