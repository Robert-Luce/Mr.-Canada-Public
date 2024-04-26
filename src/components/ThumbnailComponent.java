package components;

import java.awt.Rectangle;
import javax.swing.JFrame;
import javax.swing.JPanel;

import listeners.MouseListeners;
import page.Page;

public class ThumbnailComponent extends HTMLComponent {
	private Page pageToGoTo;
	private Page pageDisplayedOn;
	MouseListeners listener;

	/**
	 * @param fileName
	 * @param filePath
	 * @param viewport
	 */
	public ThumbnailComponent(String fileName, String filePath, JPanel viewport) {
		super(fileName, filePath, viewport);
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
		this.viewport.addMouseListener(this.listener);
	}

	@Override
	public void close() {
		super.close();
		this.viewport.removeMouseListener(this.listener);
	}
	
}