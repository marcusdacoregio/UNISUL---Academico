package br.unisul.model.domain;

import java.util.Arrays;

public class Knapsack {

	public int[] itemArray;
	private Integer lifeExpectancy;
	private boolean mutant;
	private double weight;
	private double volume;
	private double value;
	
	public Knapsack() {
	
	}
	
	public Knapsack(int[] itemArray) {
		this.itemArray = itemArray;
	}

	public Knapsack(int[] itemArray, Integer lifeExpectancy, boolean mutant, double weight,
			double volume, double value) {
		this(itemArray);
		this.lifeExpectancy = lifeExpectancy;
		this.mutant = mutant;
		this.weight = weight;
		this.volume = volume;
		this.value = value;
	}

	public Integer getLifeExpectancy() {
		return lifeExpectancy;
	}

	public void setLifeExpectancy(Integer lifeExpectancy) {
		this.lifeExpectancy = lifeExpectancy;
	}

	public boolean isMutant() {
		return mutant;
	}

	public void setMutant(boolean mutant) {
		this.mutant = mutant;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public double getVolume() {
		return volume;
	}

	public void setVolume(Double volume) {
		this.volume = volume;
	}

	public double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(itemArray);
		result = prime * result
				+ ((lifeExpectancy == null) ? 0 : lifeExpectancy.hashCode());
		long temp;
		temp = Double.doubleToLongBits(value);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(volume);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(weight);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Knapsack other = (Knapsack) obj;
		if (!Arrays.equals(itemArray, other.itemArray))
			return false;
		if (lifeExpectancy == null) {
			if (other.lifeExpectancy != null)
				return false;
		} else if (!lifeExpectancy.equals(other.lifeExpectancy))
			return false;
		if (Double.doubleToLongBits(value) != Double
				.doubleToLongBits(other.value))
			return false;
		if (Double.doubleToLongBits(volume) != Double
				.doubleToLongBits(other.volume))
			return false;
		if (Double.doubleToLongBits(weight) != Double
				.doubleToLongBits(other.weight))
			return false;
		return true;
	}

}
