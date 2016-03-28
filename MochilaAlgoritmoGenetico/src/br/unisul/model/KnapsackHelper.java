package br.unisul.model;

import java.util.HashMap;
import java.util.Map;

import br.unisul.model.domain.Item;
import br.unisul.model.domain.Knapsack;
import br.unisul.util.FileUtil;

public class KnapsackHelper {
	
	public Map<Integer, Item> recoverItensData(String pathCSVitens) {
		
		Map<Integer, Item> mapItens = new HashMap<Integer, Item>();
		
		String csvContent = FileUtil.readCSVfile(pathCSVitens);
		
		String[] eachLineFromCSV = csvContent.split(FileUtil.FILE_SEPARATOR);
		
		//Starts from 1 to avoid header
		//Stop on lenght-1 to avoid the footer
		for(int i = 1; i < eachLineFromCSV.length-1; i++) {
			
			String[] eachDataFromLine = eachLineFromCSV[i].split(";");
			
			Integer index = Integer.parseInt(eachDataFromLine[0]);
			String name = eachDataFromLine[1];
			Double volume = Double.parseDouble(eachDataFromLine[2]);
			Double weight = Double.parseDouble(eachDataFromLine[3]);
			Double value = Double.parseDouble(eachDataFromLine[4]);
			
			Item item = new Item(name, volume, weight, value);
			mapItens.put(index, item);
		}
		
		return mapItens;
	}
	
	public Knapsack recoverTotalKnapsackCapacity(String pathCSVitens) {
		
		String csvContent = FileUtil.readCSVfile(pathCSVitens);
		
		String[] eachLineFromCSV = csvContent.split(FileUtil.FILE_SEPARATOR);
		
		int lastIndex = eachLineFromCSV.length - 1;
		
		Knapsack knapsack = new Knapsack();
		
		String[] eachDataFromLine = eachLineFromCSV[lastIndex].split(";");
		
		Double volume = Double.parseDouble(eachDataFromLine[2]);
		Double weight = Double.parseDouble(eachDataFromLine[3]);
		Double value = Double.parseDouble(eachDataFromLine[4]);
		
		knapsack.setVolume(volume);
		knapsack.setWeight(weight);
		knapsack.setValue(value);
		
		return knapsack;
	}

}
