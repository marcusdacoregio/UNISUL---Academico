package br.unisul.model;

import java.util.ArrayList;
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
	
	private Map<Integer, Item> mapItens;
	private Population population;
	private ParametersDTO parameters;
	private Knapsack knapsackWithMaxedValues;
	
	private static Random random = new Random();
	
	public KnapsackModel(ParametersDTO parameters) {
		this.parameters = parameters;
		
		KnapsackHelper helper = new KnapsackHelper();
		mapItens = helper.recoverItensData(PATH_CSV_ITENS);
		
		knapsackWithMaxedValues = helper.recoverTotalKnapsackCapacity(PATH_CSV_ITENS);
	}
	
	public void process() {
		
		initializePopulation();
		
	}
	
	private void initializePopulation() {
		
		List<Knapsack> knapsackList = new ArrayList<>();

		for (int i = 0; i < parameters.getInitialPopulationSize(); i++) {
			
			//Get the amount of 1 in the array
			int amountOf1 = random.nextInt(TOTAL_ITEM_QUANTITY);

			int[] array = new int[TOTAL_ITEM_QUANTITY];
			
			//Fill the array with the amount of 1 sequentially
			for (int j = 0; j < amountOf1; j++) {
				array[j] = ONE;
			}
			//Shuffle the array to obtain variety
			GAUtil.shuffle(array);
			
			//Create the knapsack object and add it to the list
			Knapsack knapsack = new Knapsack(array);
			knapsackList.add(knapsack);
			
		}
		
		population = new Population(knapsackList);
	}
	
	private void applyEvaluationFunction() {
		
		for (Knapsack knapsack : population.getPopulation()) {
			
			calculateKnapsackData(knapsack);
			setLifeExpectancy(knapsack);
			
		}
		
	}

	private void calculateKnapsackData(Knapsack knapsack) {
		
		
		
	}

	private void setLifeExpectancy(Knapsack knapsack) {
		
		//((Valor da Mochila / valor total possível) * numero gerações
		
		//temos que calcular o peso, volume e valor total da mochila recebida por parametro 
		//para poder realizar os cálculos de expectativa de vida
		
	}

	public static void main(String[] args) {
		
		int initialPopulationSize = 100;;
		Integer firstCutPointPosition = null;
		Integer secondCutPointPosition = null;
		boolean randomCutPoint = false;
		CutPointType cutPointType = CutPointType.DUAL;
		Double knapsackWeight = 80.0;
		Double knapsackVolume = 80.0;
		int maximumPopulationSize = 600;
		int minimumPopulationSize = 40;
		int disposalAmount = 50;
		int incrementAmount = 20;
		
		ParametersDTO parameters = new ParametersDTO(initialPopulationSize, firstCutPointPosition, secondCutPointPosition, 
				randomCutPoint, cutPointType, knapsackWeight, knapsackVolume, maximumPopulationSize, 
				minimumPopulationSize, disposalAmount, incrementAmount);
		
		KnapsackModel model = new KnapsackModel(parameters);
		model.process();
	}

}
