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
import br.unisul.model.enums.CutPointType;
import br.unisul.util.GAUtil;

public class KnapsackModel {
	
	public static final int TOTAL_ITEM_QUANTITY = 25;
	private static final String PATH_CSV_ITENS = "docs/tabela_artigos.csv";
	private static final int ONE = 1;
	private static final int ZERO = 0;
	
	private Map<Integer, Item> mapItens;
	private Population population;
	private ParametersDTO parameters;
	private Double maxKnapsackWeight;
	private Double maxKnapsackVolume;
	private Double maxKnapsackValue;
	
	private static Random random = new Random();
	
	public KnapsackModel(ParametersDTO parameters) {
		this.parameters = parameters;
		
		KnapsackHelper helper = new KnapsackHelper();
		mapItens = helper.recoverItensData(PATH_CSV_ITENS);
		
		setTotalCapacity(helper.recoverTotalKnapsackCapacity(PATH_CSV_ITENS));
		
	}
	
	public void process() {
		
		initializePopulation();
		applyEvaluationFunction();
		population = doRecombination();
		removeOrAdjustDeadKnapsacks();
		resizePopulation();
		
	}
	
	private void removeOrAdjustDeadKnapsacks() {
		
		List<Knapsack> disposalList = new ArrayList<Knapsack>();
		
		for (Knapsack knapsack : population.getPopulation()) {
			
			if(knapsack.getLifeExpectancy() != null) {
			
				if(knapsack.getLifeExpectancy() == ZERO) {
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
			
			removeRandomKnapsackFromPopulation(parameters.getDisposalAmount());
			
		}
		
		if(population.getPopulationSize() < parameters.getMinimumPopulationSize()) {
			
			addRandomKnapsackToPopulation(parameters.getIncrementAmount());
			
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
		int amountOf1 = random.nextInt(TOTAL_ITEM_QUANTITY);
		
		int[] array = new int[TOTAL_ITEM_QUANTITY];
		
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
			}
			
			setLifeExpectancy(knapsack);
			
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
			if(knapsack.itemArray[i] == 1) {
				//Get his data
				Item item = mapItens.get(i);
				
				//Set his data on actual knapsack
				knapsack.setWeight(knapsack.getWeight() + item.getWeight());
				knapsack.setVolume(knapsack.getVolume() + item.getVolume());
				knapsack.setValue(knapsack.getValue() + item.getValue());
				
			}
			
		}
		
	}

	private void setLifeExpectancy(Knapsack knapsack) {
		
		//((Valor da Mochila / valor total possível) * numero gerações
		double calc = (knapsack.getValue() / maxKnapsackValue) * parameters.getTotalGeneration();
		
		int lifeExpectancy = (int) calc;
		//Set the life expectancy based on just knapsack value 
		knapsack.setLifeExpectancy(lifeExpectancy);
		
		//Now gives a penalty to the knapsack if it surpass the limit of weight and volume
		if(knapsack.getWeight() > maxKnapsackWeight) {
			
			//Penalty based on weight
			
		}
		
		if(knapsack.getVolume() > maxKnapsackVolume) {
			
			//Penalty based on volume
			
		}
		
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
		
		maxKnapsackWeight = data[KnapsackHelper.INDEX_WEIGHT];
		maxKnapsackVolume = data[KnapsackHelper.INDEX_VOLUME];
		maxKnapsackValue = data[KnapsackHelper.INDEX_VALUE];
		
	}

	public static void main(String[] args) {
		
		int initialPopulationSize = 100;;
		Integer firstCutPointPosition = null;
		Integer secondCutPointPosition = null;
		boolean randomCutPoint = false;
		CutPointType cutPointType = CutPointType.DUAL;
		double knapsackWeight = 80.0;
		double knapsackVolume = 80.0;
		int maximumPopulationSize = 600;
		int minimumPopulationSize = 40;
		int disposalAmount = 50;
		int incrementAmount = 20;
		int totalGeneration = 100;
		int recombinationRate = 80;
		
		ParametersDTO parameters = new ParametersDTO(initialPopulationSize, firstCutPointPosition, secondCutPointPosition, 
				randomCutPoint, cutPointType, knapsackWeight, knapsackVolume, maximumPopulationSize, 
				minimumPopulationSize, disposalAmount, incrementAmount, totalGeneration, recombinationRate);
		
		KnapsackModel model = new KnapsackModel(parameters);
		model.process();
	}

}
