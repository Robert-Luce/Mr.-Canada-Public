package Page;


import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JFrame;

import components.BannerComponent;
import components.HTMLComponent;
import components.ThumbnailComponent;

public class Page {

	private JFrame frame;
	private BannerComponent banner;
	private String name;
	private ThumbnailComponent thumbnail;
	private ArrayList<Page> pages;
	private ArrayList<String> pageNames;
	private HTMLComponent content;


	public Page(JFrame frame, String name) {
		super();
		this.name = name;
		this.frame = frame;
		this.banner = new BannerComponent(this.frame);
		this.thumbnail = new ThumbnailComponent("thumbnail", this.name, this.frame);
		this.thumbnail.setPage(this);
		this.pages = new ArrayList<Page>();
		this.pageNames = new ArrayList<String>();
		this.content = new HTMLComponent("content", this.name, this.frame);

	}

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
				this.pages.add(new Page(this.frame, pageName));
			}
			for (Page page : this.pages) {
				page.thumbnailOpen(this);
			}

		}
		this.frame.repaint();

	}


	public void thumbnailOpen(Page page) {
		this.thumbnail.open();
		this.thumbnail.setPageDisplayedOn(page);
		this.frame.repaint();
	}

	public void thumbnailClose() {
		this.thumbnail.close();
		this.frame.repaint();
	}

	public void thumbnailPressed() {
		this.thumbnail.getPageDisplayedOn().close();
		this.open();
		this.frame.repaint();
	}

	public void close() {
		this.banner.close();
		this.content.close();
		for (Page page : this.pages) {
			page.thumbnailClose();
		}
		this.frame.repaint();
	}



}
