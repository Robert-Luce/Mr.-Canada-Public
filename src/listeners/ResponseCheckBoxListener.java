package listeners;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import survey.SurveyComponent;

/**
 * @author lucerc
 */
public class ResponseCheckBoxListener implements ItemListener {

	private ArrayList<String> criteria;
	private String filePath;

	/**
	 * 
	 * @param criteria
	 * @param filePath
	 */
	public ResponseCheckBoxListener(ArrayList<String> criteria, String filePath) {
		this.criteria = criteria;
		this.filePath = filePath;
		this.filePath = Path.of("MrCanadaData\\Users\\" + this.filePath).toAbsolutePath().toString();
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			BufferedReader reader;
			ArrayList<String> currentCriteria = new ArrayList<String>();
			try {
				reader = new BufferedReader(new FileReader(filePath));
				String line;
				while ((line = reader.readLine()) != null) {
					currentCriteria.add(line);
				}
			} catch (Exception error) {
				System.out.println("Please add " + this.filePath);
			}
			try {
				for (String textToAppend : criteria) {
					try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.filePath))) {
						if (!currentCriteria.contains(textToAppend)) {
							reader = new BufferedReader(new FileReader(this.filePath));
							writer.newLine();
							writer.write(textToAppend);
						}
					}
				}
			} catch (Exception error) {
				System.out.println("Please add " + this.filePath);
			}
		} else if (e.getStateChange() == ItemEvent.DESELECTED) {
			BufferedReader reader;
			try {
				reader = new BufferedReader(new FileReader(filePath));
				ArrayList<String> filteredLines = new ArrayList<>();
				String line;

				while ((line = reader.readLine()) != null) {
					if (!this.criteria.contains(line.trim())) {
						filteredLines.add(line);
					}
				}
				reader.close();
				BufferedWriter writer = new BufferedWriter(new FileWriter(this.filePath));
				for (String filteredLine : filteredLines) {
					if (!filteredLine.trim().isEmpty()) {
						writer.write(filteredLine);
						writer.newLine();
					}
				}
				writer.close();
			} catch (Exception error) {
				System.out.println("Please add " + this.filePath);
			}
		}
	}

}
