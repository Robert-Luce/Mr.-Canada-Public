package Page;


import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JFrame;
import javax.swing.JPanel;

import components.BannerComponent;
import components.HTMLComponent;
import components.ThumbnailComponent;

public class Page {

	private JPanel viewport;
	private BannerComponent banner;
	private String name;
	private ThumbnailComponent thumbnail;
	private ArrayList<Page> pages;
	private ArrayList<String> pageNames;
	private HTMLComponent content;

	/**
	 * ensures: The default constructor for the Page class. Calls a list of thumbnails and content based on its given name
	 * @param viewport - The JFrame that the Page will be added to
	 * @param name - The name of the Page. IMPORTANT - Must match the name in the file structure with its associated data.
	 */
	public Page(JPanel viewport, String name) {
		super();
		this.name = name;
		this.viewport = viewport;
		this.banner = new BannerComponent(this.viewport);
		this.thumbnail = new ThumbnailComponent("thumbnail", this.name, this.viewport);
		this.thumbnail.setPage(this);
		this.pages = new ArrayList<Page>();
		this.pageNames = new ArrayList<String>();
		this.content = new HTMLComponent("content", this.name, this.viewport);

	}
	
	/**
	 * ensures: that the correct content is displayed on this Page. Uses the name given in the constructor to 
	 * search through the MrCanadaData folder for the associated content and buttons for this Page.
	 */
	public void open() {
		this.banner.open();
		this.content.open();
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
			}

		}
		this.viewport.repaint();

	}

	/**
	 * ensures: displays the thumbnail associated with this page on a given page.
	 * @param page - the Page that the thumbnail should be displayed on.
	 */
	public void thumbnailOpen(Page page) {
		this.thumbnail.open();
		this.thumbnail.setPageDisplayedOn(page);
		this.viewport.repaint();
	}
	
	/**
	 * ensures: Makes the thumbnail for this page disappear from where it was originally displayed.
	 */
	public void thumbnailClose() {
		this.thumbnail.close();
		this.viewport.repaint();
	}

	/**
	 * ensures: Handles what should happen when the thumbnail is pressed: closes the Page it was 
	 * originally displayed on and opens this Page.
	 */
	public void thumbnailPressed() {
		this.thumbnail.getPageDisplayedOn().close();
		this.open();
		this.viewport.repaint();
	}

	/**
	 * ensures: makes it so that this Page is no longer displayed. removes the banner and content 
	 * associated with the page, as well as each each thumbnail that was displayed on the page.
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
