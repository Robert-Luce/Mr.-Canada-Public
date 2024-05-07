package components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import listeners.MouseListeners;

public class HTMLComponent extends HTMLReaderComponent {

	private JPanel panel;
	private JLabel label;
	private int htmlX;
	private int htmlY;
	public HTMLComponent(String fileName, String filePath, JPanel viewport) {
		super(fileName, filePath, viewport);
		this.panel = new JPanel();
		this.label = new JLabel();
		this.htmlX = 0;
		this.htmlY = 0;
		
		if (!(this.getTxtFileData() == null)) {

			try {
				this.htmlX = extractIntegerAfter("x=", this.getTxtFileData());
			} catch (Exception e) {
				System.out.println(
						"Please add x location to " + "MrCanadaData\\" + this.getFilePath() + "\\" + this.getFileName() + ".txt");
			}
			try {
				this.htmlY = extractIntegerAfter("y=", this.getTxtFileData());
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
	/**
	 * ensures: The HTML opens and repaints (resets) the frame
	 */
	public void open() {
		super.open();
		this.revalidate();
		this.repaint();
	}

	/**
	 * ensures: The HTML is removed from the frame and repaints (resets) the frame to display this change
	 */
	public void close() {
		this.getViewport().remove(this);
		this.revalidate();
		this.repaint();
	}

	/**
	 * ensures: Sets the label text on the frame from the HTML file
	 */
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

	/**
	 * ensures: A new rectangle is created based on the buttonComponent passed in 
	 * @param buttonComponent
	 * @return
	 */
	public Rectangle getBounds(ButtonComponent buttonComponent) {
		return new Rectangle(this.htmlX, this.htmlY, this.getHtmlWidth(), this.getHtmlHeight());
	}


}