package components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import listeners.MouseListeners;

public class HTMLComponent extends HTML {

	private JPanel panel;
	private String txtFileData;
	private JLabel label;
	private String txtAbsolutePath;
	private int htmlX;
	private int htmlY;
	public HTMLComponent(String fileName, String filePath, JPanel viewport) {
		super(filePath, filePath, viewport);
		this.panel = new JPanel();
		this.label = new JLabel();
		this.htmlX = 0;
		this.htmlY = 0;
		try {
			this.txtAbsolutePath = Path.of("MrCanadaData\\" + this.getFilePath() + "\\" + this.getFileName() + ".txt")
					.toAbsolutePath().toString();
		} catch (Exception e) {
			System.out.println("Please add " + "MrCanadaData\\" + this.getFilePath() + "\\" + this.getFileName() + ".txt");
		}
	
		try {
			this.txtFileData = Files.readString(Path.of(this.txtAbsolutePath));
		} catch (Exception e) {
			System.out
					.println("Please add text to " + "MrCanadaData\\" + this.getFilePath() + "\\" + this.getFileName() + ".txt");
		}
		if (!(this.txtFileData == null)) {
			try {
				this.setHtmlWidth(extractIntegerAfter("width=", this.txtFileData));
			} catch (Exception e) {
				System.out.println("Please add width location to " + "MrCanadaData\\" + this.getFilePath() + "\\"
						+ this.getFileName() + ".txt");
			}
			try {
				this.setHtmlHeight(extractIntegerAfter("height=", this.txtFileData));
			} catch (Exception e) {
				System.out.println("Please add height location to " + "MrCanadaData\\" + this.getFilePath() + "\\"
						+ this.getFileName() + ".txt");
			}

			try {
				this.htmlX = extractIntegerAfter("x=", this.txtFileData);
			} catch (Exception e) {
				System.out.println(
						"Please add x location to " + "MrCanadaData\\" + this.getFilePath() + "\\" + this.getFileName() + ".txt");
			}
			try {
				this.htmlY = extractIntegerAfter("y=", this.txtFileData);
			} catch (Exception e) {
				System.out.println(
						"Please add y location to " + "MrCanadaData\\" + this.getFilePath() + "\\" + this.getFileName() + ".txt");
			}

		}
		if (!(this.getHtmlFileData() == null)) {
			this.label.setText(this.getHtmlFileData());
		}

		this.panel.add(this.label, BorderLayout.NORTH);
		this.add(this.panel, BorderLayout.NORTH);
		this.setLocation(this.getHtmlX(), this.getHtmlY());
		this.setSize(this.getHtmlWidth(), this.getHtmlHeight());
		this.setPreferredSize(new Dimension(this.getHtmlWidth(), this.getHtmlHeight()));
	}
	public void open() {
		super.open();
		this.revalidate();
		this.repaint();
	}

	
	public void close() {
		this.getViewport().remove(this);
		this.revalidate();
		this.repaint();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.label.setText(this.getHtmlFileData());
		this.label.update(g);
		this.panel.update(g);
	}
	public int getHtmlX() {
		return htmlX;
	}
	public int getHtmlY() {
		return htmlY;
	}
	public JPanel getViewport() {
		return super.getViewport();
	}


}