package br.unisul.model.domain;

public class Item {

	private String name;
	private Double volume;
	private Double weight;
	private Double value;

	public Item(String name, Double volume, Double weight, Double value) {
		this.name = name;
		this.volume = volume;
		this.weight = weight;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public Double getVolume() {
		return volume;
	}

	public Double getWeight() {
		return weight;
	}

	public Double getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "Item [name=" + name + ", volume=" + volume + ", weight=" + weight + ", value=" + value + "]";
	}

}
