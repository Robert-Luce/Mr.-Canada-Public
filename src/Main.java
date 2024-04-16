import javax.swing.JFrame;

import components.BannerComponent;
import components.HTMLComponent;

public class Main {
  public static void main(String[] args) {
	  JFrame frame = new JFrame();
	  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  BannerComponent banner = new BannerComponent(frame); 
	  banner.open();
	  frame.setVisible(true);
	  frame.pack();
	  frame.repaint();
	  
  }   
}