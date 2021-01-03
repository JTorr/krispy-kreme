package utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CsvReader {
	String file;
	static List<String> output = new ArrayList<>();

	public static List<String> readCsv(String fileName) throws Exception {
		File file = new File(fileName);
		Scanner sc = new Scanner(file);
		sc.useDelimiter("\r\n");
		while (sc.hasNext())
		{
			output.add(sc.next()); 
		}
		sc.close();
		return output;
	}
}
