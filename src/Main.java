import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import components.BannerComponent;
import components.DestinationComponent;
import components.HTMLComponent;

public class Main {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BannerComponent banner = new BannerComponent(frame);
		banner.open();
		frame.add(new DestinationComponent("Montreal HTML",frame));
		frame.setVisible(true);
		frame.pack();
		frame.repaint();
	}
}