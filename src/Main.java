import javax.swing.JFrame;

import components.PageComponent;

public class Main {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		PageComponent page = new PageComponent(frame, "Montreal HTML");
		page.open();
//		BannerComponent banner = new BannerComponent(frame);
//		banner.open();
//		HTMLComponent button  = new HTMLComponent("Destination Label.html", "Montreal HTML", frame);
//		button.open();
		frame.setVisible(true);
		frame.pack();
		frame.repaint();
	}
}