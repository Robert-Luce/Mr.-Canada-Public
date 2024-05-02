package components;

import javax.swing.JFrame;
import javax.swing.JPanel;

import listeners.MouseListeners;
import page.Page;

public class ThumbnailComponent extends ButtonComponent implements PressableComponent {
	Page pageToGoTo;
	private Page pageDisplayedOn;
	/**
	 * @param fileName
	 * @param filePath
	 * @param viewport
	 */
	public ThumbnailComponent(String filePath, JPanel viewport) {
		super("thumbnail", filePath, viewport);
	}

	public void setPage(Page page) {
		this.pageToGoTo = page;
	}

	public Page getPageToGoTo() {
		return this.pageToGoTo;
	}

	public void setPageDisplayedOn(Page pageDisplayedOn) {
		this.pageDisplayedOn = pageDisplayedOn;
	}

	public Page getPageDisplayedOn() {
		return this.pageDisplayedOn;
	}

	public void pressed() {
			this.pageToGoTo.thumbnailPressed();
	}

	public void open(ThumbnailComponent thumbnailComponent) {
		super.open();
		thumbnailComponent.getViewport().addMouseListener(this.listener);
	}

	public void close(ThumbnailComponent thumbnailComponent) {
		super.close();
		thumbnailComponent.getViewport().removeMouseListener(this.listener);
	}
	
}