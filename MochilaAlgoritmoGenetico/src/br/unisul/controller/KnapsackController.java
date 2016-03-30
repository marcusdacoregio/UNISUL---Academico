package br.unisul.controller;

import java.util.Map;

import br.unisul.model.KnapsackModel;
import br.unisul.model.domain.Item;
import br.unisul.model.domain.Knapsack;
import br.unisul.model.domain.Population;
import br.unisul.model.dto.ParametersDTO;

public class KnapsackController {
	
	private KnapsackModel model;
	
	public KnapsackController(ParametersDTO parameters) {
		model = new KnapsackModel(parameters);
	}
	
	public void process() {
		model.process();
	}
	
	public Population getPopulation() {
		return model.getPopulation();
	}
	
	public Knapsack getBestKnapsack() {
		return model.getBestKnapsack();
	}
	
	public Map<Integer, Item> getMapItens() {
		return model.getMapItens();
	}
	
	public double getMaxKnapsackValue() {
		return model.getMaxKnapsackValue();
	}
	
	public int getFirstCutPointPosition() {
		return model.getFirstCutPointPosition();
	}
	
	public int getSecondCutPointPosition() {
		return model.getSecondCutPointPosition();
	}
}
