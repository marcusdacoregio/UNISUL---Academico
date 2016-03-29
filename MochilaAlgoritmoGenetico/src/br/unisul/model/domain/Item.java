package br.unisul.model.domain;

public class Item {

	private String name;
	private double volume;
	private double weight;
	private double value;

	public Item(String name, double volume, double weight, double value) {
		this.name = name;
		this.volume = volume;
		this.weight = weight;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public double getVolume() {
		return volume;
	}

	public double getWeight() {
		return weight;
	}

	public double getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "Item [name=" + name + ", volume=" + volume + ", weight=" + weight + ", value=" + value + "]";
	}

}
