package components;

import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import Page.Page;
import listeners.MouseListeners;

public class ThumbnailComponent extends HTMLComponent {
	private Page pageToGoTo;
	private Page pageDisplayedOn;
	MouseListeners listener;

	/**
	 * @param fileName
	 * @param filePath
	 * @param frame
	 */
	public ThumbnailComponent(String fileName, String filePath, JFrame frame) {
		super(fileName, filePath, frame);
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