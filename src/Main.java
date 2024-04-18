import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import components.BannerComponent;
import components.DestinationComponent;
import components.HTMLComponent;
import listeners.MouseListeners;

public class Main {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BannerComponent banner = new BannerComponent(frame);
		banner.open();
		HTMLComponent button  = new HTMLComponent("Destination Label.html", "Montreal HTML", frame);
		button.open();
//		DestinationComponent montreal = new DestinationComponent("Montreal HTML",frame);
//		montreal.open();
		frame.setVisible(true);
		frame.pack();
		frame.repaint();
	}
}