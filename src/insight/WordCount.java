package insight;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class WordCount {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File input_dir = new File("wc_input");
		File output_file = new File("wc_output", "wc_result.txt");
		File[] listOfFiles = input_dir.listFiles();

		FileReader fr = null;
		BufferedReader br = null;
		FileWriter fw = null;
		BufferedWriter bw = null;

		String line;
		String output_line;
		int val;
		TreeMap<String, Integer> tm = new TreeMap<String, Integer>();

		if (listOfFiles == null) {
			System.out
					.println("Following is not a directory or does not exist "
							+ input_dir.getName());
		} else {
			for (File file : listOfFiles) {
				if (file.isFile()) {
					try {
						fr = new FileReader(file);
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						System.out.println("Cannot obtain filereader for file "
								+ file.getName());
					}

					br = new BufferedReader(fr);

					try {
						while ((line = br.readLine()) != null) {
							String[] word = line.replaceAll("[^a-zA-Z ]", "")
									.toLowerCase().split(" ");
							for (int i = 0; i < word.length; i++) {
								if (word[i].length() != 0) {
									if (word[i] != null) {
										if (tm.containsKey(word[i])) {
											val = 0;
											val = tm.get(word[i]);
											tm.put(word[i], val + 1);
										} else {
											tm.put(word[i], 1);
										}
									}
								}
							}
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						System.out
								.println("BufferedReader cannot read line from file "
										+ file.getName());
					}

				}
			}

			// Get a set of the entries.
			if (br != null) {
				Set<Map.Entry<String, Integer>> set = tm.entrySet();
				try {
					fw = new FileWriter(output_file);
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					System.out.println("Cannot obtain FileWriter for file "
							+ output_file.getName());
				}
				bw = new BufferedWriter(fw);

				if (!output_file.exists()) {
					try {
						output_file.createNewFile();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						System.out.println("Cannot create output file "
								+ output_file.getName());
					}
				}

				// Display the elements.
				for (Map.Entry<String, Integer> me : set) {
					output_line = me.getKey() + "\t" + me.getValue();
					try {
						bw.write(output_line);
						bw.newLine();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						System.out
								.println("BufferedWriter cannot write output line "
										+ output_line);
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
					if (fr != null)
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