package br.unisul.model.dto;

import br.unisul.model.enums.CutPointType;

public class ParametersDTO {

	private int initialPopulationSize;
	private int firstCutPointPosition;
	private int secondCutPointPosition;
	private boolean randomCutPoint;
	private CutPointType cutPointType;
	private double knapsackWeight;
	private double knapsackVolume;
	private String csvPath;
	
	private int maximumPopulationSize;
	private int minimumPopulationSize;
	private int disposalAmount;
	private int incrementAmount;
	private int totalGeneration;
	private int recombinationRate;
	
	public ParametersDTO(int initialPopulationSize, int firstCutPointPosition, int secondCutPointPosition,
			boolean randomCutPoint, CutPointType cutPointType, double knapsackWeight, double knapsackVolume, int maximumPopulationSize,
			int minimumPopulationSize, int disposalAmount, int incrementAmount, int totalGeneration, int recombinationRate, String csvPath) {
		
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
		this.csvPath = csvPath;
		this.firstCutPointPosition = firstCutPointPosition;
		this.secondCutPointPosition = secondCutPointPosition;
	}
	
	public int getInitialPopulationSize() {
		return initialPopulationSize;
	}

	public int getFirstCutPointPosition() {
		return firstCutPointPosition;
	}

	public int getSecondCutPointPosition() {
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

	public String getCsvPath() {
		return csvPath;
	}

	public void setFirstCutPointPosition(int firstCutPointPosition) {
		this.firstCutPointPosition = firstCutPointPosition;
	}

	public void setSecondCutPointPosition(int secondCutPointPosition) {
		this.secondCutPointPosition = secondCutPointPosition;
	}

}
