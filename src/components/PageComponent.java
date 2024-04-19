package components;

import javax.swing.JFrame;

public class PageComponent {

	private JFrame frame ;
	private BannerComponent banner;
	private String pageName;
	private HTMLComponent thumbnail;

	public PageComponent(JFrame frame, String pageName) {
		this.pageName = pageName;
		this.frame = frame;
		this.banner = new BannerComponent(this.frame);
		this.thumbnail = new HTMLComponent("MrCanadaData\\HTML Files\\" + this.pageName, "thumbnail.html", this.frame);
	}

	public void open() {
		this.banner.open();
		
	}
	public void thumbnailOpen() {
		this.thumbnail.open();
	}
	public void thumbnailPressed() {
		this.frame.removeAll();
		this.frame.repaint();
		this.open();
		
	}
	
}
