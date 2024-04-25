package components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import listeners.MouseListeners;

public class HTMLComponent extends JComponent {

	protected JPanel panel;
	protected String fileName;
	protected String filePath;
	protected JPanel viewport;
	protected String txtFileData;
	protected String htmlFileData;
	protected JLabel label;
	protected String txtAbsolutePath;
	protected String pngAbsolutePath;
	protected String htmlAbsolutePath;
	protected int htmlWidth;
	protected int htmlHeight;
	protected int htmlX;
	protected int htmlY;
	public HTMLComponent(String fileName, String filePath, JPanel viewport) {
		super();
		this.setOpaque(false);
		this.panel = new JPanel();
		this.panel.setOpaque(false);
		this.label = new JLabel();
		this.filePath = filePath;
		this.fileName = fileName;
		this.viewport = viewport;
		this.htmlWidth = 0;
		this.htmlHeight = 0;
		this.htmlX = 0;
		this.htmlY = 0;
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
		} catch (Exception e) {
			System.out
					.println("Please add text to " + "MrCanadaData\\" + this.filePath + "\\" + this.fileName + ".txt");
		}
		try {
			this.htmlFileData = Files.readString(Path.of(this.htmlAbsolutePath));
		} catch (Exception e) {
			System.out
					.println("Please add text to " + "MrCanadaData\\" + this.filePath + "\\" + this.fileName + ".html");
		}
		if (!(this.txtFileData == null)) {
			try {
				this.htmlWidth = extractIntegerAfter("width=", this.txtFileData);
			} catch (Exception e) {
				System.out.println("Please add width location to " + "MrCanadaData\\" + this.filePath + "\\"
						+ this.fileName + ".txt");
			}
			try {
				this.htmlHeight = extractIntegerAfter("height=", this.txtFileData);
			} catch (Exception e) {
				System.out.println("Please add height location to " + "MrCanadaData\\" + this.filePath + "\\"
						+ this.fileName + ".txt");
			}

			try {
				this.htmlX = extractIntegerAfter("x=", this.txtFileData);
			} catch (Exception e) {
				System.out.println(
						"Please add x location to " + "MrCanadaData\\" + this.filePath + "\\" + this.fileName + ".txt");
			}
			try {
				this.htmlY = extractIntegerAfter("y=", this.txtFileData);
			} catch (Exception e) {
				System.out.println(
						"Please add y location to " + "MrCanadaData\\" + this.filePath + "\\" + this.fileName + ".txt");
			}

		}
		if (!(this.htmlFileData == null)) {
			if (this.htmlFileData.contains("<img src=")) {
				this.pngAbsolutePath = this.pngAbsolutePath.replace(" ", "%20");
				this.htmlFileData = this.htmlFileData.replace("<img src=",
						"<img src=\"file:///" + this.pngAbsolutePath + "\"");

			}
			this.label.setText(this.htmlFileData);
		}

		this.panel.add(this.label, BorderLayout.NORTH);
		this.add(this.panel, BorderLayout.NORTH);
		this.setLocation(this.htmlX, this.htmlY);
		this.setSize(this.htmlWidth, this.htmlHeight);
		this.setPreferredSize(new Dimension(this.htmlWidth, this.htmlHeight));
	}
	public static int extractIntegerAfter(String target, String text) throws Exception {
		Pattern pattern = Pattern.compile(target + "(\\d+)");
		Matcher matcher = pattern.matcher(text);
		if (matcher.find()) {
			String numberString = matcher.group(1);
			return Integer.parseInt(numberString);
		} else {
			throw new Exception();
		}
	}

	public void open() {
		this.viewport.add(this);
		this.revalidate();
		this.repaint();
	}

	/**
	 * @return the panel
	 */
	public JPanel getPanel() {
		return panel;
	}
	/**
	 * @param panel the panel to set
	 */
	public void setPanel(JPanel panel) {
		this.panel = panel;
	}
	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * @return the filePath
	 */
	public String getFilePath() {
		return filePath;
	}
	/**
	 * @param filePath the filePath to set
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	/**
	 * @return the txtFileData
	 */
	public String getTxtFileData() {
		return txtFileData;
	}
	/**
	 * @param txtFileData the txtFileData to set
	 */
	public void setTxtFileData(String txtFileData) {
		this.txtFileData = txtFileData;
	}
	/**
	 * @return the htmlFileData
	 */
	public String getHtmlFileData() {
		return htmlFileData;
	}
	/**
	 * @param htmlFileData the htmlFileData to set
	 */
	public void setHtmlFileData(String htmlFileData) {
		this.htmlFileData = htmlFileData;
	}
	/**
	 * @return the label
	 */
	public JLabel getLabel() {
		return label;
	}
	/**
	 * @param label the label to set
	 */
	public void setLabel(JLabel label) {
		this.label = label;
	}
	/**
	 * @return the txtAbsolutePath
	 */
	public String getTxtAbsolutePath() {
		return txtAbsolutePath;
	}
	/**
	 * @param txtAbsolutePath the txtAbsolutePath to set
	 */
	public void setTxtAbsolutePath(String txtAbsolutePath) {
		this.txtAbsolutePath = txtAbsolutePath;
	}
	/**
	 * @return the pngAbsolutePath
	 */
	public String getPngAbsolutePath() {
		return pngAbsolutePath;
	}
	/**
	 * @param pngAbsolutePath the pngAbsolutePath to set
	 */
	public void setPngAbsolutePath(String pngAbsolutePath) {
		this.pngAbsolutePath = pngAbsolutePath;
	}
	/**
	 * @return the htmlAbsolutePath
	 */
	public String getHtmlAbsolutePath() {
		return htmlAbsolutePath;
	}
	/**
	 * @param htmlAbsolutePath the htmlAbsolutePath to set
	 */
	public void setHtmlAbsolutePath(String htmlAbsolutePath) {
		this.htmlAbsolutePath = htmlAbsolutePath;
	}
	/**
	 * @return the htmlWidth
	 */
	public int getHtmlWidth() {
		return htmlWidth;
	}
	/**
	 * @param htmlWidth the htmlWidth to set
	 */
	public void setHtmlWidth(int htmlWidth) {
		this.htmlWidth = htmlWidth;
	}
	/**
	 * @return the htmlHeight
	 */
	public int getHtmlHeight() {
		return htmlHeight;
	}
	/**
	 * @param htmlHeight the htmlHeight to set
	 */
	public void setHtmlHeight(int htmlHeight) {
		this.htmlHeight = htmlHeight;
	}
	/**
	 * @return the htmlX
	 */
	public int getHtmlX() {
		return htmlX;
	}
	/**
	 * @param htmlX the htmlX to set
	 */
	public void setHtmlX(int htmlX) {
		this.htmlX = htmlX;
	}
	/**
	 * @return the htmlY
	 */
	public int getHtmlY() {
		return htmlY;
	}
	/**
	 * @param htmlY the htmlY to set
	 */
	public void setHtmlY(int htmlY) {
		this.htmlY = htmlY;
	}
	/**
	 * @param viewport the frame to set
	 */
	public void setFrame(JPanel viewport) {
		this.viewport = viewport;
	}
	public void close() {
		this.viewport.remove(this);
		this.revalidate();
		this.repaint();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.label.setText(this.htmlFileData);
		this.label.update(g);
		this.panel.update(g);
	}

	public JPanel getFrame() {
		return viewport;
	}


}