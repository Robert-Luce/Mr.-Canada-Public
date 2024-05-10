package page;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JFrame;
import javax.swing.JPanel;

import components.BannerComponent;
import components.HTMLComponent;
import components.ThumbnailComponent;
import place.Place;
import place.PlaceLinkedList;

/**
 * @author lucerc
 */
public class Page {

	private JPanel viewport;
	private BannerComponent banner;
	private String name;
	private ThumbnailComponent thumbnail;
	private ArrayList<Page> pages;
	private ArrayList<String> pageNames;
	private HTMLComponent content;
	private PlaceLinkedList pLL;

	/**
	 * @param pLL the pLL to set
	 */
	public void setPLL(PlaceLinkedList pLL) {
		this.pLL = pLL;
	}

	/**
	 * ensures: The default constructor for the Page class. Calls a list of
	 * thumbnails and content based on its given name
	 * 
	 * @param viewport - The JFrame that the Page will be added to
	 * @param name     - The name of the Page. IMPORTANT - Must match the name in
	 *                 the file structure with its associated data.
	 */
	public Page(JPanel viewport, String name) {
		super();
		this.name = name;
		this.viewport = viewport;
		this.banner = new BannerComponent(this.viewport);
		this.thumbnail = new ThumbnailComponent(this.name, this.viewport);
		this.thumbnail.setPage(this);
		this.pages = new ArrayList<Page>();
		this.pageNames = new ArrayList<String>();
		this.content = new HTMLComponent("content", this.name, this.viewport);
	}

	/**
	 * ensures: that the correct content is displayed on this Page. Uses the name
	 * given in the constructor to search through the MrCanadaData folder for the
	 * associated content and buttons for this Page.
	 */
	public void open() {
		int maxY = 0;
		this.banner.open();
		if (this.banner.getHtmlHeight() + this.banner.getHtmlY() > maxY) {
			maxY = this.banner.getHtmlHeight() + this.banner.getHtmlY();
		}
		this.content.open();
		if (this.content.getHtmlHeight() + this.content.getHtmlY() > maxY) {
			maxY = this.content.getHtmlHeight() + this.content.getHtmlY();
		}
		try {
			this.pageNames = new ArrayList<String>(Arrays.asList(Files
					.readString(Path
							.of(Path.of("MrCanadaData\\" + this.name + "\\Page Names.txt").toAbsolutePath().toString()))
					.split("\r\n")));
		} catch (Exception e) {
			System.out.println("Please add " + "MrCanadaData\\" + this.name + "\\Page Names.txt");
		}
		if (!(this.pageNames.isEmpty())) {
			for (String pageName : this.pageNames) {
				this.pages.add(new Page(this.viewport, pageName));
			}

			for (Page page : this.pages) {
				page.thumbnailOpen(this);
				if (page.thumbnail.getHtmlHeight() + page.thumbnail.getHtmlY() > maxY) {
					maxY = page.thumbnail.getHtmlHeight() + page.thumbnail.getHtmlY();
				}
			}
			if (this.pLL != null) {
				this.openPLL(0, maxY);
			}
		}
		this.viewport.repaint();

	}

	public void openPLL(int count, int maxY) {
		if (count == this.pLL.length()) {
			return;
		} else {
			this.pLL.getHTMLAtIndex(count).open();
			this.pLL.getHTMLAtIndex(count).setHtmlY(maxY);
			this.openPLL(count + 1, maxY + this.pLL.getHTMLAtIndex(count).getHtmlHeight());
		}
	}

	/**
	 * ensures: displays the thumbnail associated with this page on a given page.
	 * 
	 * @param page - the Page that the thumbnail should be displayed on.
	 */
	public void thumbnailOpen(Page page) {
		this.thumbnail.open(this.thumbnail);
		this.thumbnail.setPageDisplayedOn(page);
		this.viewport.repaint();
	}

	/**
	 * ensures: Makes the thumbnail for this page disappear from where it was
	 * originally displayed.
	 */
	public void thumbnailClose() {
		this.thumbnail.close(this.thumbnail);
		this.viewport.repaint();
	}

	/**
	 * ensures: Handles what should happen when the thumbnail is pressed: closes the
	 * Page it was originally displayed on and opens this Page.
	 */
	public void thumbnailPressed() {
		this.thumbnail.getPageDisplayedOn().close();
		this.open();
		this.viewport.repaint();
	}

	/**
	 * ensures: makes it so that this Page is no longer displayed. removes the
	 * banner and content associated with the page, as well as each each thumbnail
	 * that was displayed on the page.
	 */
	public void close() {
		this.banner.close();
		this.content.close();
		for (Page page : this.pages) {
			page.thumbnailClose();
		}
		this.viewport.repaint();
	}


}
