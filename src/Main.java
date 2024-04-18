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

public class Main {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		BannerComponent banner = new BannerComponent(frame);
		banner.open();
		HTMLComponent button  = new HTMLComponent("Destination Label.html", "Montreal HTML", frame);
		button.open();
		System.out.println(button.getHTMLHeight());
		System.out.println(button.getHTMLWidth());
//		DestinationComponent montreal = new DestinationComponent("Montreal HTML",frame);
//		montreal.open();
		frame.setVisible(true);
		frame.pack();
		frame.repaint();
	}
}