import javax.swing.JFrame;

import components.PageComponent;

public class Main {
  public static void main(String[] args) {
	  JFrame frame = new JFrame();
	  frame.setSize(400,500); 
	  PageComponent page = new PageComponent("test.htm", "testpages" , frame);
	  page.open();
	  frame.pack();
	  frame.repaint();
	  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  frame.setLayout(null);
	  frame.setVisible(true);
  }   
}