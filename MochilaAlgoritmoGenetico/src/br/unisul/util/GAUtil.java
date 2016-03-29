package br.unisul.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class GAUtil {
	
	private static Random random = new Random();
	
	/**
     * Code from method java.util.Collections.shuffle();
     */
	public static void shuffle(int[] array) {
        if (random == null) random = new Random();
        int count = array.length;
        for (int i = count; i > 1; i--) {
            swap(array, i - 1, random.nextInt(i));
        }
    }

	private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static HashMap sortByValues(Map map) {
		List list = new LinkedList(map.entrySet());
		// Defined Custom Comparator here
		Collections.sort(list, new Comparator() {
			public int compare(Object o1, Object o2) {
				return ((Comparable) ((Map.Entry) (o1)).getValue())
						.compareTo(((Map.Entry) (o2)).getValue());
			}
		});

		// Here I am copying the sorted list in HashMap
		// using LinkedHashMap to preserve the insertion order
		HashMap sortedHashMap = new LinkedHashMap();
		for (Iterator it = list.iterator(); it.hasNext();) {
			Map.Entry entry = (Map.Entry) it.next();
			sortedHashMap.put(entry.getKey(), entry.getValue());
		}

		return sortedHashMap;
	}
	
	/**
	 * Returns a list of random positions from the list that come from parameter
	 * 
	 * @param list
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List<Integer> generateRandomPositionsForTheList(List list, int recombinationRate) {
		
		List tempList = new ArrayList<>();
		tempList.addAll(list);
		
		Collections.shuffle(tempList);
		
		double rate = ((double) recombinationRate) / 100;
		double result = tempList.size() * rate;
		
		int parentsAmount = (int) result;
		
		List<Integer> arrayPositions = new ArrayList<Integer>();
		
		for(int i = 0; i < parentsAmount; i++){
			
			arrayPositions.add(tempList.indexOf(tempList.get(i)));
			
		}
		
		Collections.shuffle(arrayPositions);
		
		return arrayPositions;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List<Integer> generateRandomPositionsForTheList(int amount, List list) {
		
		List tempList = new ArrayList<>();
		tempList.addAll(list);
		
		Collections.shuffle(tempList);
		
		List<Integer> arrayPositions = new ArrayList<Integer>();
		
		for(int i = 0; i < amount; i++){
			
			arrayPositions.add(tempList.indexOf(tempList.get(i)));
			
		}
		
		Collections.shuffle(arrayPositions);
		
		return arrayPositions;
	}

}
