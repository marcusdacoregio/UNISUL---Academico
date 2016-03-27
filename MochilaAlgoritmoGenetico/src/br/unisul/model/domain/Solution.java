package br.unisul.model.domain;

public class Solution {

	public int[] array;
	private int lifeExpectancy;
	private boolean mutant;
	private Double weight;
	private Double volume;
	
	public Solution(int[] array, int lifeExpectancy, boolean mutant, Double weight, Double volume) {
		this.array = array;
		this.lifeExpectancy = lifeExpectancy;
		this.mutant = mutant;
		this.weight = weight;
		this.volume = volume;
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

}
