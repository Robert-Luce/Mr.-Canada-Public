package components;

import java.awt.Graphics;
import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import listeners.MouseListeners;

public class DestinationComponent extends JPanel {

	private JFrame frame;
	private String filePath;
	private Path path;
	private String absolutePath;
	private String[] fileNames;
	private File folder;
	private ArrayList<HTMLComponent> buttons;

	public DestinationComponent(String filePath, JFrame frame) {
		super();
		this.frame = frame;
		this.filePath = filePath;
		this.path = Path.of("MrCanadaData\\HTML Files\\" + this.filePath);
		this.absolutePath = this.path.toAbsolutePath().toString();
		this.folder = new File(this.absolutePath);
		this.fileNames = folder.list();
		this.buttons = new ArrayList<HTMLComponent>();
		this.buttons.add(new HTMLComponent("Destination Label.html", this.filePath, this.frame));
		for (String fileName : this.fileNames) {
			if(fileName.contains(".html")) {
			this.buttons.add(new HTMLComponent(fileName, this.filePath, this.frame));
			}
		}
		for (HTMLComponent button : this.buttons) {
			this.add(button);
			button.updateSize();
		}

	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (HTMLComponent button : this.buttons) {
			button.update(g);
		}
		System.out.println("hi");
	}
	public void open() {
		this.frame.add(this);
		for (HTMLComponent button : this.buttons) {
			button.open();
			this.addMouseListener(new MouseListeners(button));
		}
		this.repaint();
	}
}
