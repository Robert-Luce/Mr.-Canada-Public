package components;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;

public class PageComponent {
	private JPanel panel;
	private String fileName;
	private String filePath;
	private JFrame frame;
	private String fileData;
	private Path path;
    private JEditorPane pane;
	private HTMLEditorKit kit;
	private Document doc;
	/**
	 * @param fileName
	 * @param filePath
	 * @param frame
	 */
	public PageComponent(String fileName, String filePath, JFrame frame) {
		this.panel = new JPanel();
		this.filePath = filePath;
		this.fileName = fileName;
		this.frame = frame;
		this.path = Path.of("MrCanadaData\\Pages\\" + this.filePath + "\\" + this.fileName);
		try {
			this.fileData = Files.readString(this.path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.pane = new JEditorPane();
		this.kit = new HTMLEditorKit();
	    this.pane.setEditorKit(kit);
	    this.doc = kit.createDefaultDocument();
	    this.pane.setDocument(doc);
        this.pane.setText(this.fileData);
        this.panel.add(this.pane);
        System.out.println(this.fileData);
	}
	public void open() {
		this.frame.add(this.panel);
	}
	public void close() {
		this.frame.remove(this.panel);
	}
	
}
