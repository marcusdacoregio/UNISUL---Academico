package br.unisul.model.dto;

import java.util.Random;

import br.unisul.model.KnapsackModel;
import br.unisul.model.enums.CutPointType;

public class ParametersDTO {

	private Integer initialPopulationSize;
	private Integer firstCutPointPosition;
	private Integer secondCutPointPosition;
	private boolean randomCutPoint;
	private CutPointType cutPointType;
	private double knapsackWeight;
	private double knapsackVolume;
	
	private int maximumPopulationSize;
	private int minimumPopulationSize;
	private int disposalAmount;
	private int incrementAmount;
	private int totalGeneration;
	private int recombinationRate;
	
	public ParametersDTO(Integer initialPopulationSize, Integer firstCutPointPosition, Integer secondCutPointPosition,
			boolean randomCutPoint, CutPointType cutPointType, double knapsackWeight, double knapsackVolume, int maximumPopulationSize,
			int minimumPopulationSize, int disposalAmount, int incrementAmount, int totalGeneration, int recombinationRate) {
		this.initialPopulationSize = initialPopulationSize;
		this.randomCutPoint = randomCutPoint;
		this.cutPointType = cutPointType;
		this.knapsackWeight = knapsackWeight;
		this.knapsackVolume = knapsackVolume;
		this.maximumPopulationSize = maximumPopulationSize;
		this.minimumPopulationSize = minimumPopulationSize;
		this.disposalAmount = disposalAmount;
		this.incrementAmount = incrementAmount;
		this.totalGeneration = totalGeneration;
		this.recombinationRate = recombinationRate;
		
		if(!setCutPointPositions()) {
			this.firstCutPointPosition = firstCutPointPosition;
			this.secondCutPointPosition = secondCutPointPosition;
		}
	}
	
	private boolean setCutPointPositions() {
		
		if(randomCutPoint) {
			
			Random random = new Random();
			
			switch (cutPointType) {
			case SINGLE:
				
				firstCutPointPosition = random.nextInt(KnapsackModel.TOTAL_ITEM_QUANTITY);
				return true;

			case DUAL:
				
				firstCutPointPosition = random.nextInt(KnapsackModel.TOTAL_ITEM_QUANTITY);
				
				while(secondCutPointPosition.equals(firstCutPointPosition) 
						|| secondCutPointPosition < firstCutPointPosition) {
					
					secondCutPointPosition = random.nextInt(KnapsackModel.TOTAL_ITEM_QUANTITY);
					
				}
				
				return true;
			}
			
		}
		
		return false;
	}

	public Integer getInitialPopulationSize() {
		return initialPopulationSize;
	}

	public Integer getFirstCutPointPosition() {
		return firstCutPointPosition;
	}

	public Integer getSecondCutPointPosition() {
		return secondCutPointPosition;
	}

	public boolean isRandomCutPoint() {
		return randomCutPoint;
	}

	public CutPointType getCutPointType() {
		return cutPointType;
	}

	public double getKnapsackWeight() {
		return knapsackWeight;
	}

	public double getKnapsackVolume() {
		return knapsackVolume;
	}

	public int getMaximumPopulationSize() {
		return maximumPopulationSize;
	}

	public int getMinimumPopulationSize() {
		return minimumPopulationSize;
	}

	public int getDisposalAmount() {
		return disposalAmount;
	}

	public int getIncrementAmount() {
		return incrementAmount;
	}

	public int getTotalGeneration() {
		return totalGeneration;
	}

	public int getRecombinationRate() {
		return recombinationRate;
	}

}
