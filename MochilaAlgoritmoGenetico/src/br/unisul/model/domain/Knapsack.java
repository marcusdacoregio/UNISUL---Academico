package br.unisul.model.domain;

public class Knapsack {

	public int[] itemArray;
	private int lifeExpectancy;
	private boolean mutant;
	private Double weight;
	private Double volume;
	private Double value;
	
	public Knapsack(int[] itemArray) {
		this.itemArray = itemArray;
	}

	public Knapsack(int[] itemArray, int lifeExpectancy, boolean mutant, Double weight,
			Double volume, Double value) {
		this(itemArray);
		this.lifeExpectancy = lifeExpectancy;
		this.mutant = mutant;
		this.weight = weight;
		this.volume = volume;
		this.value = value;
	}

	public int getLifeExpectancy() {
		return lifeExpectancy;
	}

	public void setLifeExpectancy(int lifeExpectancy) {
		this.lifeExpectancy = lifeExpectancy;
	}

	public boolean isMutant() {
		return mutant;
	}

	public void setMutant(boolean mutant) {
		this.mutant = mutant;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Double getVolume() {
		return volume;
	}

	public void setVolume(Double volume) {
		this.volume = volume;
	}

	public Double getValue() {
		return value;
	}

}
