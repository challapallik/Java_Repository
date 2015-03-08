package insight;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class RunningMedian {
	public static void main(String[] args) {

		String line;
		String output_line;

		File input_dir = new File("wc_input");
		File output_file = new File("wc_output", "med_result.txt");
		File[] listOfFiles = input_dir.listFiles();

		FileReader fr = null;
		BufferedReader br = null;
		FileWriter fw = null;
		BufferedWriter bw = null;

		ArrayList<Integer> no_of_words = new ArrayList<Integer>();
		ArrayList<Double> median_ouput = new ArrayList<Double>();

		if (listOfFiles == null) {
			System.out.println("Files does not exist in this directory"
					+ input_dir.getName());
		} else {
			for (int i = 0; i < listOfFiles.length; i++) {
				File filename = listOfFiles[i];
				try {
					fr = new FileReader(filename);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					System.out.println("FileReader cannot open file "
							+ filename.getName());
				}
				br = new BufferedReader(fr);
				try {
					while ((line = br.readLine()) != null) {
						String[] word = line.replaceAll("[^a-zA-Z ]", "")
								.split(" ");
						no_of_words.add(word.length);
						Collections.sort(no_of_words);
						int line_length = no_of_words.size();
						double median = 0.0;
						if (line_length % 2 == 0) {
							median = (no_of_words.get(line_length / 2) + no_of_words
									.get((line_length / 2) - 1)) / 2.0;
						} else {
							median = no_of_words.get((line_length - 1) / 2);
						}
						median_ouput.add(median);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out
							.println("BufferedReader cannot read line from file "
									+ filename.getName());
				}
			}

			if (br != null) {
				if (!output_file.exists()) {
					try {
						output_file.createNewFile();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						System.out.println("Cannot create new file "
								+ output_file.getName());
					}
				}

				try {
					fw = new FileWriter(output_file);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("Cannot obtain filewriter for file"
							+ output_file.getName());
				}

				bw = new BufferedWriter(fw);

				for (Double counter : median_ouput) {
					output_line = Double.toString(counter);
					try {
						bw.write(output_line);
						bw.newLine();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						System.out.println("Cannot write to output file "
								+ output_file.getName());
					}
				}
				try {
					bw.flush();
					fw.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out
							.println("Cannot flush FileWriter and BufferedWriter");
				}
				try {
					br.close();
					fr.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out
							.println("Cannot close FileWriter and BufferedWriter");
				}
			}
		}
	}
}
