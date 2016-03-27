package br.unisul.model;

import java.util.HashMap;
import java.util.Map;

import br.unisul.model.domain.Item;
import br.unisul.util.FileUtil;

public class ItemHelper {
	
	public Map<Integer, Item> recoverItensData(String pathCSVitens) {
		
		Map<Integer, Item> mapItens = new HashMap<Integer, Item>();
		
		String csvContent = FileUtil.readCSVfile(pathCSVitens);
		
		String[] eachLineFromCSV = csvContent.split(FileUtil.FILE_SEPARATOR);
		
		for(int i = 0; i < eachLineFromCSV.length; i++) {
			
			if(i == 0 || i == eachLineFromCSV.length-1) {
				continue;
			}
			
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

}
