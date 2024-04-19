package components;

import javax.swing.JFrame;

public class PageComponent {

	private JFrame frame ;
	private BannerComponent banner;

	public PageComponent(JFrame frame) {
		this.frame = frame;
		this.banner = new BannerComponent(this.frame);
	}

	public void open() {
		this.banner.open();
		
	}
	
}
