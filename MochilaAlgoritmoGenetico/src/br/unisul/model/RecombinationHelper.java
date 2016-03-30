package br.unisul.model;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import br.unisul.model.domain.Knapsack;
import br.unisul.model.domain.Population;
import br.unisul.model.dto.ParametersDTO;
import br.unisul.util.GAUtil;

public class RecombinationHelper {
	
	private Population population;
	
	private List<Integer> positions;
	private ParametersDTO parameters;
	
	public RecombinationHelper(Population population, ParametersDTO parameters) {
		
		this.population = population;
		this.parameters = parameters;
		
		this.positions = GAUtil.generateRandomPositionsForTheList(population.getPopulation(),
				parameters.getRecombinationRate());
	}

	public Population recombine() {
		
		while(positions.size() > 0) {
			
			Knapsack father = getNextKnapsack();
			Knapsack mother = getNextKnapsack();
			
			Knapsack kid1 = applyCrossover(father, mother);
			Knapsack kid2 = applyCrossover(mother, father);
			
			population.getPopulation().add(kid1);
			population.getPopulation().add(kid2);
		}
		
		return population;
	}
	
	private Knapsack applyCrossover(Knapsack father, Knapsack mother) {
		
		switch (parameters.getCutPointType()) {
		case SINGLE:
			Knapsack kid = doSingleCutPointCrossOver(father, mother); 
			applyMutation(kid);
			
			return kid;
			
		case DUAL:
			Knapsack kid2 = doDualCutPointCrossOver(father, mother); 
			applyMutation(kid2);
			
			return kid2;

		}
		
		return null;
	}
	
	private void applyMutation(Knapsack kid) {
		double mutationRate = parameters.getMutationRate() / 100;
		
		if(Math.random() <= mutationRate) {
			
			int randomPosition = new Random().nextInt(kid.itemArray.length);
			
			kid.itemArray[randomPosition] = kid.itemArray[randomPosition] == 1 ? 0 : 1;
			
			kid.setMutant(true);
		}
	}

	private Knapsack doDualCutPointCrossOver(Knapsack father, Knapsack mother) {
		
		int firstCutPoint = parameters.getFirstCutPointPosition();
		int secondCutPoint = parameters.getSecondCutPointPosition();
		
		int[] kidMiddleArray = Arrays.copyOfRange(mother.itemArray, firstCutPoint, secondCutPoint);
		int[] kidArray = father.itemArray.clone();
		
		int middleArrayIndex = 0;
		for(int i = firstCutPoint; i < secondCutPoint; i++) {
			
			kidArray[i] = kidMiddleArray[middleArrayIndex];
			middleArrayIndex++;
			
		}
		
		Knapsack kid = new Knapsack(kidArray);
		
		return kid;
	}

	private Knapsack doSingleCutPointCrossOver(Knapsack father, Knapsack mother) {
		
		int cutPoint = parameters.getFirstCutPointPosition();
		
		int[] kidRightArray = Arrays.copyOfRange(mother.itemArray, cutPoint, mother.itemArray.length);
		
		int[] kidArray = father.itemArray.clone();
		
		int rightArrayIndex = 0;
		
		for(int i = cutPoint; i < kidArray.length; i++) {
			
			kidArray[i] = kidRightArray[rightArrayIndex];
			rightArrayIndex++;
			
		}
		
		Knapsack kid = new Knapsack(kidArray);
		
		return kid;
	}

	private Knapsack getNextKnapsack() {
		
		if(positions.size() == 0) {
			Random gerador = new Random();
			
			return population.getPopulation().get(gerador.nextInt(population.getPopulationSize()));
		}
		
		int posicao = positions.get(0);
		positions.remove(0);
		
		return population.getPopulation().get(posicao);
		
	}
	
}
