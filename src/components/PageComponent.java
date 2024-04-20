package components;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import javax.swing.JFrame;

public class PageComponent {

	private JFrame frame;
	private BannerComponent banner;
	private String name;
	private HTMLComponent thumbnail;
	private ArrayList<PageComponent> pages;
	private String[] pageNames;


	public PageComponent(JFrame frame, String name) {
		this.name = name;
		this.frame = frame;
		this.banner = new BannerComponent(this.frame);
		this.thumbnail = new HTMLComponent("MrCanadaData\\" + this.name, "thumbnail.html", this.frame);
		this.pages = new ArrayList<PageComponent>();

	}

	public void open() {
		this.banner.open();
		try {
			this.pageNames = Files.readString(Path.of(
					Path.of("MrCanadaData\\" + this.name + "\\Page Names.txt").toAbsolutePath().toString()))
					.split(" ");
		} catch (IOException e) {
			this.pageNames[0] = "";
		} finally {
			for (String pageName : this.pageNames) {
				this.pages.add(new PageComponent(this.frame, pageName));
			}
			for (PageComponent page : this.pages) {
				page.thumbnailOpen();
			}
		}

	}

	public void thumbnailOpen() {
		this.thumbnail.open();
	}

	public void ThumbnailClose() {
		this.thumbnail.close();
	}

	public void thumbnailPressed() {
		this.frame.removeAll();
		this.frame.repaint();
		this.open();
	}

}
