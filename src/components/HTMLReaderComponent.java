package components;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JComponent;
import javax.swing.JPanel;
/**
 * @author lucerc
 */
public class HTMLReaderComponent extends JComponent {

	private String fileName;
	private String filePath;
	private String htmlFileData;
	private String pngAbsolutePath;
	private String htmlAbsolutePath;
	private int htmlWidth;
	private int htmlHeight;
	private JPanel viewport;
	private String txtAbsolutePath;
	private String txtFileData;

	
	public static int extractIntegerAfter(String target, String text) throws Exception {
		Pattern pattern = Pattern.compile(target + "(\\d+)");
		Matcher matcher = pattern.matcher(text);
		if (matcher.find()) {
			String numberString = matcher.group(1);
			return Integer.parseInt(numberString);
		} else {
			throw new Exception();
		}
	}

	/**
	 * ensures: HTMLReaderComponent is constructed
	 * @param fileName
	 * @param filePath
	 * @param viewport
	 */
	public HTMLReaderComponent(String fileName, String filePath, JPanel viewport) {
		super();
		this.viewport = viewport;
		this.setHtmlWidth(0);
		this.setHtmlHeight(0);
		this.fileName = fileName;
		this.filePath = filePath;
		try {
			this.txtAbsolutePath = Path.of("MrCanadaData\\" + this.getFilePath() + "\\" + this.getFileName() + ".txt")
					.toAbsolutePath().toString();
			this.txtFileData = Files.readString(Path.of(this.txtAbsolutePath));
		} catch (Exception e) {
			System.out.println("Error reading" + "MrCanadaData\\" + this.getFilePath() + "\\" + this.getFileName() + ".txt");;
		}
		try {
			this.pngAbsolutePath = Path.of("MrCanadaData\\" + this.getFilePath() + "\\" + this.getFileName() + ".png")
					.toAbsolutePath().toString();
		} catch (Exception e) {
			System.out.println(
					"Please add " + "MrCanadaData\\" + this.getFilePath() + "\\" + this.getFileName() + ".png");
		}
		try {
			this.htmlAbsolutePath = Path.of("MrCanadaData\\" + this.getFilePath() + "\\" + this.getFileName() + ".html")
					.toAbsolutePath().toString();
			this.htmlFileData = Files.readString(Path.of(this.htmlAbsolutePath));
		} catch (Exception e) {
			System.out.println(
					"Error reading " + "MrCanadaData\\" + this.getFilePath() + "\\" + this.getFileName() + ".html");
		}
		if (!(this.getTxtFileData() == null)) {
			try {
				this.setHtmlWidth(extractIntegerAfter("width=", this.getTxtFileData()));
			} catch (Exception e) {
				System.out.println("Please add width location to " + "MrCanadaData\\" + this.getFilePath() + "\\"
						+ this.getFileName() + ".txt");
			}
			try {
				this.setHtmlHeight(extractIntegerAfter("height=", this.getTxtFileData()));
			} catch (Exception e) {
				System.out.println("Please add height location to " + "MrCanadaData\\" + this.getFilePath() + "\\"
						+ this.getFileName() + ".txt");
			}
		}
		if (!(this.getHtmlFileData() == null)) {
			if (this.getHtmlFileData().contains("<img src=")) {
				this.pngAbsolutePath = this.pngAbsolutePath.replace(" ", "%20");
				this.htmlFileData = this.getHtmlFileData().replace("<img src=",
						"<img src=\"file:///" + this.pngAbsolutePath + "\"");

			}
		}
	}

	/**
	 * ensures: gets filePath
	 * @return filePath
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * ensures: gets file's name 
	 * @return fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * ensures: gets HTML width
	 * @return htmlWidth
	 */
	public int getHtmlWidth() {
		return htmlWidth;
	}

	/**
	 * ensures: sets the HTML width
	 * @param htmlWidth
	 */
	public void setHtmlWidth(int htmlWidth) {
		this.htmlWidth = htmlWidth;
	}

	/**
	 * ensures: gets the height of the HTML
	 * @return htmlHeight
	 */
	public int getHtmlHeight() {
		return htmlHeight;
	}

	/**
	 * ensures: sets the height of the HTML
	 * @param htmlHeight
	 */
	public void setHtmlHeight(int htmlHeight) {
		this.htmlHeight = htmlHeight;
	}

	/**
	 * ensures: gets Html data
	 * @return htmlFileData
	 */
	public String getHtmlFileData() {
		return htmlFileData;
	}

	/**
	 * ensures: gets the viewport JPanel
	 * @return viewport
	 */
	public JPanel getViewport() {
		return this.viewport;
	}

	/**
	 * ensures: corresponding is added to the Jpanel 
	 */
	public void open() {
		this.getViewport().add(this);
		this.revalidate();
		this.repaint();
	}

	/**
	 * ensures: gets the textFileData
	 * @return txtFileData
	 */
	public String getTxtFileData() {
		return txtFileData;
	}

}