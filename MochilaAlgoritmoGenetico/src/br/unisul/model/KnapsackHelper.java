package br.unisul.model;

import java.util.LinkedHashMap;
import java.util.Map;

import br.unisul.model.domain.Item;
import br.unisul.util.FileUtil;

public class KnapsackHelper {
	
	public static final int INDEX_WEIGHT = 0;
	public static final int INDEX_VOLUME = 1;
	public static final int INDEX_VALUE = 2;
	
	public int getTotalItens(String pathCSVitens) {
		
		String csvContent = FileUtil.readCSVfile(pathCSVitens);
		
		String[] eachLineFromCSV = csvContent.split(FileUtil.FILE_SEPARATOR);
		
		//Return eachLineFromCSV - 2 to ignore the header and footer
		return eachLineFromCSV.length - 2;
	}
	
	public Map<Integer, Item> recoverItensData(String pathCSVitens) {
		
		Map<Integer, Item> mapItens = new LinkedHashMap<Integer, Item>();
		
		String csvContent = FileUtil.readCSVfile(pathCSVitens);
		
		String[] eachLineFromCSV = csvContent.split(FileUtil.FILE_SEPARATOR);
		
		//Starts from 1 to avoid header
		//Stop on length-1 to avoid the footer
		for(int i = 1; i < eachLineFromCSV.length-1; i++) {
			
			String[] eachDataFromLine = eachLineFromCSV[i].split(";");
			
			Integer index = Integer.parseInt(eachDataFromLine[0]);
			String name = eachDataFromLine[1];
			double volume = Double.parseDouble(eachDataFromLine[2]);
			double weight = Double.parseDouble(eachDataFromLine[3]);
			double value = Double.parseDouble(eachDataFromLine[4]);
			
			Item item = new Item(name, volume, weight, value);
			mapItens.put(index, item);
		}
		
		return mapItens;
	}
	
	public double[] recoverTotalKnapsackCapacity(String pathCSVitens) {
		
		String csvContent = FileUtil.readCSVfile(pathCSVitens);
		
		String[] eachLineFromCSV = csvContent.split(FileUtil.FILE_SEPARATOR);
		
		int lastIndex = eachLineFromCSV.length - 1;
		
		String[] eachDataFromLine = eachLineFromCSV[lastIndex].split(";");
		
		double volume = Double.parseDouble(eachDataFromLine[2]);
		double weight = Double.parseDouble(eachDataFromLine[3]);
		double value = Double.parseDouble(eachDataFromLine[4]);
		
		double[] returnData = new double[3];
		returnData[INDEX_WEIGHT] = weight;
		returnData[INDEX_VOLUME] = volume;
		returnData[INDEX_VALUE] = value;
		
		return returnData;
	}
	
}
