import javax.swing.JFrame;

import components.BannerComponent;
import components.PageComponent;

public class Main {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BannerComponent banner = new BannerComponent(frame);
		banner.open();
		PageComponent page = new PageComponent(frame, "Montreal");
		page.open();
		frame.setVisible(true);
		frame.pack();
		frame.repaint();
	}
}