package components;

import javax.swing.JFrame;
import javax.swing.JPanel;

import listeners.MouseListeners;
import page.Page;
/**
 * @author lucerc
 */
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

	/**
	 * ensures: Sets page 
	 * @param page
	 */
	public void setPage(Page page) {
		this.pageToGoTo = page;
	}

	/**
	 * ensures: gets the page that it should go to 
	 * @return
	 */
	public Page getPageToGoTo() {
		return this.pageToGoTo;
	}

	/**
	 * ensures: sets the pageDisplayedOn
	 * @param pageDisplayedOn
	 */
	public void setPageDisplayedOn(Page pageDisplayedOn) {
		this.pageDisplayedOn = pageDisplayedOn;
	}

	/**
	 * ensures: gets the pageDisplayedOn
	 * @return
	 */
	public Page getPageDisplayedOn() {
		return this.pageDisplayedOn;
	}

	/**
	 * ensures: calls the thumnailPressed method associated with this thumbnailComponent
	 */
	public void pressed() {
			this.pageToGoTo.thumbnailPressed();
	}

	/**
	 * ensures: the thumbnailComponent is added to the frame and adds an associated MouseListener
	 * @param thumbnailComponent
	 */
	public void open(ThumbnailComponent thumbnailComponent) {
		this.repaint();
		super.open();
		thumbnailComponent.getViewport().addMouseListener(this.getListener());
	}

	/**
	 * ensures: the thumbnailComponent is removed from the frame and removes its associated Mouselistener 
	 * @param thumbnailComponent
	 */
	public void close(ThumbnailComponent thumbnailComponent) {
		super.close();
		thumbnailComponent.getViewport().removeMouseListener(this.getListener());
	}
	
}