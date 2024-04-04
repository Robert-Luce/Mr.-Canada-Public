import javax.swing.JFrame;

import components.BannerComponent;
import components.HTMLComponent;

public class Main {
  public static void main(String[] args) {
	  JFrame frame = new JFrame();
	  frame.setSize(400,500); 
	  BannerComponent banner = new BannerComponent(frame); 
	  banner.open();
	  frame.pack();
	  frame.repaint();
	  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  frame.setLayout(null);
	  frame.setVisible(true);
  }   
}