package components;

import java.awt.Rectangle;
import javax.swing.JFrame;
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
		this.listener = new MouseListeners(this);
	}

	public void setPage(Page page) {
		this.pageToGoTo = page;
	}

	public Page getPageToGoTo() {
		return this.pageToGoTo;
	}

	public void pressed() {
			this.pageToGoTo.thumbnailPressed();
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

	@Override
	public void open() {
		super.open();
		this.frame.getContentPane().addMouseListener(this.listener);
	}

	@Override
	public void close() {
		super.close();
		this.frame.getContentPane().removeMouseListener(this.listener);
	}
	
}