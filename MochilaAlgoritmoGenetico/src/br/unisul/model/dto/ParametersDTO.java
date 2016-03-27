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
	
	/**
	 * 
	 * @param initialPopulationSize
	 * @param firstCutPointPosition
	 * @param secondCutPointPosition
	 * @param randomCutPoint
	 * @param cutPointType
	 * @param knapsackWeight
	 * @param knapsackVolume
	 * 
	 * @see firstCutPointPosition and secondCutPointPosition would be null if randomCutPoint is true
	 */
	public ParametersDTO(Integer initialPopulationSize, Integer firstCutPointPosition, Integer secondCutPointPosition,
			boolean randomCutPoint, CutPointType cutPointType, double knapsackWeight, double knapsackVolume) {
		this.initialPopulationSize = initialPopulationSize;
		this.randomCutPoint = randomCutPoint;
		this.cutPointType = cutPointType;
		this.knapsackWeight = knapsackWeight;
		this.knapsackVolume = knapsackVolume;
		
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
				
				while(secondCutPointPosition.equals(firstCutPointPosition)) {
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

	private double knapsackVolume;

}
