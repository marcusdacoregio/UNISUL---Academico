package br.unisul.model;

import java.util.Map;

import br.unisul.model.domain.Item;

public class KnapsackModel {
	
	public static final int TOTAL_ITEM_QUANTITY = 25;
	private static final String PATH_CSV_ITENS = "docs/tabela_artigos.csv";
	
	private Map<Integer, Item> mapItens;
	
	public KnapsackModel() {
		ItemHelper helper = new ItemHelper();
		
		mapItens = helper.fillMapItens(PATH_CSV_ITENS);
	}
	
	public static void main(String[] args) {
		KnapsackModel model = new KnapsackModel();
	}

}
