package components;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import Page.Page;
import listeners.MouseListeners;

public class HTMLComponent extends JComponent {
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
	private Page pageToGoTo;
	private Page pageDisplayedOn;
	private int htmlWidth;
	private int htmlHeight;
	private int htmlX;
	private int htmlY;
	private MouseListeners listener;

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
	}

	public void open() {
		this.listener = new MouseListeners(this);
		this.frame.getContentPane().addMouseListener(this.listener);
		this.frame.add(this);
		this.revalidate();
		this.repaint();
	}

	public void close() {
		this.frame.remove(this);
		this.frame.removeMouseListener(this.listener);
		this.revalidate();
		this.repaint();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.label.setText(this.htmlFileData);
		this.label.update(g);
		this.panel.update(g);
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setPage(Page page) {
		this.pageToGoTo = page;
	}

	public Page getPageToGoTo() {
		return this.pageToGoTo;
	}

	public void pressed() {
		if (!this.pageDisplayedOn.getName().equals(this.pageToGoTo.getName())) {
			this.pageToGoTo.thumbnailPressed();
		}

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

	@Override
	public Rectangle getBounds() {
		return new Rectangle(this.htmlX, this.htmlY, this.htmlWidth, this.htmlHeight);
	}

	public void setPageDisplayedOn(Page pageDisplayedOn) {
		this.pageDisplayedOn = pageDisplayedOn;
	}

	public Page getPageDisplayedOn() {
		return this.pageDisplayedOn;
	}

}