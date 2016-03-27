package br.unisul.model.domain;

import java.util.List;

public class Population {
	
	private List<Knapsack> population;
	
	public Population(List<Knapsack> population) {
		this.population = population;
	}

	public List<Knapsack> getPopulation() {
		return population;
	}
	
	public int getPopulationSize() {
		return population.size();
	}

}
