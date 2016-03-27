package br.unisul.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileUtil {
	
	public static final String FILE_SEPARATOR = System.getProperty("line.separator");
	
	public static String readCSVfile(String path) {
		
		BufferedReader br = null;
		
		StringBuilder arquivo = new StringBuilder();
		
		try {
			
			
			br = new BufferedReader(new FileReader(path));
			
			while(br.ready()) {
				arquivo.append(br.readLine());
				arquivo.append(System.getProperty("line.separator"));
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		
		return arquivo.toString();
	}

}
