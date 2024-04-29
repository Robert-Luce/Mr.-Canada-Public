package components;

import java.awt.Rectangle;
import javax.swing.JFrame;
import javax.swing.JPanel;

import listeners.MouseListeners;
import page.Page;

public class ThumbnailComponent extends HTMLComponent implements PressableComponent {
	private Page pageToGoTo;
	private Page pageDisplayedOn;
	private MouseListeners listener;

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
		return new Rectangle(this.getHtmlX(), this.getHtmlY(), this.getHtmlWidth(), this.getHtmlHeight());
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
		this.getViewport().addMouseListener(this.listener);
	}

	@Override
	public void close() {
		super.close();
		this.getViewport().removeMouseListener(this.listener);
	}
	
}