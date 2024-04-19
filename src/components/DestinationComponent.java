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
	private ArrayList<HTMLComponent> mainComponents;
	private HTMLComponent thumbnailComponent;

	public DestinationComponent(String filePath, JFrame frame) {
		super();
		this.frame = frame;
		this.filePath = filePath;
		this.path = Path.of("MrCanadaData\\HTML Files\\" + this.filePath);
		this.absolutePath = this.path.toAbsolutePath().toString();
		this.folder = new File(this.absolutePath);
		this.fileNames = folder.list();
		this.mainComponents = new ArrayList<HTMLComponent>();
		this.mainComponents.add(new HTMLComponent("Destination Label.html", this.filePath, this.frame));
		for (String fileName : this.fileNames) {
			if(fileName.contains(".html")&& !fileName.contains("thumbnail")) {
			this.mainComponents.add(new HTMLComponent(fileName, this.filePath, this.frame));
			}
		}
		for (HTMLComponent button : this.mainComponents) {
			this.add(button);
		}

	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (HTMLComponent button : this.mainComponents) {
			button.update(g);
		}
	}
	public void open() {
		this.frame.add(this);
		for (HTMLComponent button : this.mainComponents) {
			button.open();
			this.addMouseListener(new MouseListeners(button));
		}
		this.repaint();
	}
}
