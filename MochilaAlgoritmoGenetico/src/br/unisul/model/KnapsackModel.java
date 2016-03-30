package br.unisul.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;

import br.unisul.model.domain.Item;
import br.unisul.model.domain.Knapsack;
import br.unisul.model.domain.Population;
import br.unisul.model.dto.ParametersDTO;
import br.unisul.util.GAUtil;

public class KnapsackModel {
	
	private static final String PATH_CSV_ITENS = "tabela_artigos.csv";
	private static final int ONE = 1;
	private static final int ZERO = 0;
	
	private int totalItemQuantity;
	private Map<Integer, Item> mapItens;
	private Population population;
	private ParametersDTO parameters;
	private Double maxKnapsackValue;
	
	private Knapsack bestKnapsack;
	
	private static Random random = new Random();
	
	public KnapsackModel(ParametersDTO parameters) {
		this.parameters = parameters;
		bestKnapsack = new Knapsack();
		
		KnapsackHelper helper = new KnapsackHelper();
		
		String path = parameters.getCsvPath() != null && 
				!parameters.getCsvPath().isEmpty() ? parameters.getCsvPath() : PATH_CSV_ITENS;
		
		mapItens = helper.recoverItensData(path);
		
		setTotalCapacity(helper.recoverTotalKnapsackCapacity(path));
		
		totalItemQuantity = helper.getTotalItens(path);

		setCutPointPositions();
	}
	
	public void process() {
		initializePopulation();

		for (int i = 0; i < parameters.getTotalGeneration(); i++) {
			
			applyEvaluationFunction();

			population = doRecombination();

			removeOrAdjustDeadKnapsacks();

			resizePopulation();

		}

		applyEvaluationFunction();
	}
	
	private void removeOrAdjustDeadKnapsacks() {
		
		List<Knapsack> disposalList = new ArrayList<Knapsack>();
		
		for (Knapsack knapsack : population.getPopulation()) {
			
			if(knapsack.getLifeExpectancy() != null) {
			
				if(knapsack.getLifeExpectancy() <= ZERO) {
					disposalList.add(knapsack);
				} else {
					knapsack.setLifeExpectancy(knapsack.getLifeExpectancy() - ONE);
				}
				
			}
			
		}
		
		population.getPopulation().removeAll(disposalList);
	}

	private void resizePopulation() {
		
		if(population.getPopulationSize() > parameters.getMaximumPopulationSize()) {
			int removeAmount = (population.getPopulationSize() - parameters.getMaximumPopulationSize()) + parameters.getDisposalAmount();
			
			removeRandomKnapsackFromPopulation(removeAmount);
			
		}
		
		if(population.getPopulationSize() < parameters.getMinimumPopulationSize()) {
			
			int incrementAmount = (parameters.getMinimumPopulationSize() - population.getPopulationSize()) + parameters.getIncrementAmount();
			addRandomKnapsackToPopulation(incrementAmount);
			
		}
		
	}

	private void initializePopulation() {
		
		List<Knapsack> knapsackList = new ArrayList<>();

		for (int i = 0; i < parameters.getInitialPopulationSize(); i++) {
			
			//Create the knapsack object with a random array and add it to the list
			Knapsack knapsack = new Knapsack(generateRandomArray());
			knapsackList.add(knapsack);
			
		}
		
		population = new Population(knapsackList);
	}
	
	private int[] generateRandomArray() {
		
		//Get the amount of 1 in the array
		int amountOf1 = random.nextInt(totalItemQuantity);
		
		int[] array = new int[totalItemQuantity];
		
		//Fill the array with the amount of 1 sequentially
		for (int j = 0; j < amountOf1; j++) {
			array[j] = ONE;
		}
		//Shuffle the array to obtain variety
		GAUtil.shuffle(array);

		return array;
	}
	
	private void applyEvaluationFunction() {
		for (Knapsack knapsack : population.getPopulation()) {
			
			//Check if this knapsack have not a life expectancy
			//The life expectancy will be calculated only one time per knapsack
			if(knapsack.getLifeExpectancy() == null) {
				calculateKnapsackData(knapsack);
				setLifeExpectancy(knapsack);
			}
			
		}
	}

	private Population doRecombination() {
		
		RecombinationHelper helper = new RecombinationHelper(population, parameters);
		
		return helper.recombine();
	}

	private void calculateKnapsackData(Knapsack knapsack) {
		//Iterate over the array
		for(int i = 0; i < knapsack.itemArray.length; i++) {
			//Check if the item is presents (1) on the array
			if(knapsack.itemArray[i] == ONE) {
				//Get his data
				Item item = mapItens.get(i);
				
				//Set his data on actual knapsack
				knapsack.setWeight(knapsack.getWeight() + item.getWeight());
				knapsack.setVolume(knapsack.getVolume() + item.getVolume());
				knapsack.setValue(knapsack.getValue() + item.getValue());
				
			}
			
		}
		
		if(knapsack.getValue() > bestKnapsack.getValue() && isInBoundaries(knapsack)) {
			this.bestKnapsack = knapsack;
		}
	}

	private void setLifeExpectancy(Knapsack knapsack) {
		
		//((Valor da Mochila / valor total possível) * numero gerações
		double calc = (knapsack.getValue() / maxKnapsackValue) * parameters.getTotalGeneration();
		
		int lifeExpectancy = (int) calc;
		//Set the life expectancy based on just knapsack value 
		knapsack.setLifeExpectancy(lifeExpectancy);
		
		//Now gives a penalty to the knapsack if it surpass the limit of weight and volume
		if(knapsack.getWeight() > parameters.getKnapsackWeight()) {
			
			int penalty = doPenaltyFormula(knapsack.getWeight(), knapsack.getValue());
			knapsack.setLifeExpectancy(knapsack.getLifeExpectancy() - penalty);
		}
		
		if(knapsack.getVolume() > parameters.getKnapsackVolume()) {
			
			int penalty = doPenaltyFormula(knapsack.getVolume(), knapsack.getValue());
			knapsack.setLifeExpectancy(knapsack.getLifeExpectancy() - penalty);
			
		}
		
	}
	
	private int doPenaltyFormula(double volumeOrWeight, double value) {
		
		double math = ((volumeOrWeight - parameters.getKnapsackVolume()) / value) * parameters.getTotalGeneration();
		int penalty = (int) math;
		
		return penalty;
	}
	
	private void addRandomKnapsackToPopulation(int amount) {
		
		for(int i = 0; i < amount; i++) {
			
			Knapsack knapsack = new Knapsack(generateRandomArray());
			population.getPopulation().add(knapsack);
			
		}
		
	}
	
	private void removeRandomKnapsackFromPopulation(int amount) {
		
		Collections.shuffle(population.getPopulation());
		
		for(int i = 0; i < amount; i++) {
			
			population.getPopulation().remove(0);
			
		}
		
	}
	
	private void setTotalCapacity(double[] data) {
		maxKnapsackValue = data[KnapsackHelper.INDEX_VALUE];
	}
	
	private boolean isInBoundaries(Knapsack knapsack) {
		if(knapsack.getWeight() <= parameters.getKnapsackWeight()
				&& knapsack.getVolume() <= parameters.getKnapsackVolume()) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean setCutPointPositions() {
		
		if(parameters.isRandomCutPoint()) {
			
			Random random = new Random();
			
			switch (parameters.getCutPointType()) {
			case SINGLE:
				
				parameters.setFirstCutPointPosition(random.nextInt(totalItemQuantity));
				return true;

			case DUAL:
				
				int value1 = random.nextInt(totalItemQuantity);
				
				int value2 = random.nextInt(totalItemQuantity);
				
				while(value2 == value1) {
					value2 = random.nextInt(totalItemQuantity);
				}
				
				parameters.setFirstCutPointPosition(value1 < value2 ? value1 : value2);
				parameters.setSecondCutPointPosition(value1 > value2 ? value1 : value2);
				
				return true;
			}
			
		}
		
		return false;
	}

	public Population getPopulation() {
		return population;
	}

	public Knapsack getBestKnapsack() {
		return bestKnapsack;
	}

	public Map<Integer, Item> getMapItens() {
		return mapItens;
	}

	public Double getMaxKnapsackValue() {
		return maxKnapsackValue;
	}

	public int getFirstCutPointPosition() {
		return parameters.getFirstCutPointPosition();
	}
	
	public int getSecondCutPointPosition() {
		return parameters.getSecondCutPointPosition();
	}
}
