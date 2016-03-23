package br.unisul.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import br.unisul.controller.dto.ParametrosDTO;
import br.unisul.model.domain.Individuo;

public class RecombinaHelper {
	
	private List<Individuo> populacao;
	private List<Individuo> filhos;
	
	private List<Integer> posicoes;
	private ParametrosDTO parametros;
	
	public RecombinaHelper(List<Individuo> populacao, ParametrosDTO parametros) {
		this.populacao = populacao;
		filhos = new ArrayList<Individuo>();
		
		this.parametros = parametros;
		
		posicoes = new ArrayList<Integer>();
		adicionarPosicoesEmListaParaRecombinar();
	}
	
	/**
	 * 
	 * @return Filhos da população que foi recombinada
	 */
	public List<Individuo> recombinar() {
		
		while(posicoes.size() > 0) {

			Individuo pai = getNextIndividuo();
			Individuo mae = getNextIndividuo();
			
			Individuo filho1 = aplicarCutAndCrossfill(pai, mae);
			Individuo filho2 = aplicarCutAndCrossfill(mae, pai);
			
			filhos.add(filho1);
			filhos.add(filho2);
		}
		
		return filhos;
	}
	
	private Individuo aplicarCutAndCrossfill(Individuo pai, Individuo mae) {
		
		int pontoDeCorte = parametros.getPontoCorte();
		
		int[] arrayEsquerdaPai = new int[pontoDeCorte];
		int[] arrayEsquerdaMae = new int[pontoDeCorte];
		
		int[] arrayDireitaPai = new int[pai.array.length - pontoDeCorte];
		int[] arrayDireitaMae = new int[mae.array.length - pontoDeCorte];
		
		for(int i = 0; i < pontoDeCorte; i++) {
			arrayEsquerdaPai[i] = pai.array[i];
			arrayEsquerdaMae[i] = mae.array[i];
		}
		
		int index = 0;
		for(int i = pontoDeCorte; i < pai.array.length; i++) {
			arrayDireitaPai[index] = pai.array[i];
			arrayDireitaMae[index] = mae.array[i];
			index++;
		}
		
		int[] arrayFilho = new int[pai.array.length];
		
		for (int i = 0; i < arrayEsquerdaPai.length; i++) {
			arrayFilho[i] = arrayEsquerdaPai[i];
		}
		
		int contador = 0;
		
		int i = pontoDeCorte;
		while(contador < mae.array.length) {
			
			if(!isValorPresenteEmArray(mae.array[contador], arrayFilho)) {
				arrayFilho[i] = mae.array[contador];
				i++;
			}
			
			contador++;
		}
		
		Individuo individuo = new Individuo();
		individuo.array = arrayFilho;
		
		return individuo;
	}
	
	private Individuo getNextIndividuo() {
		
		if(posicoes.size() == 0) {
			Random gerador = new Random();
			
			return populacao.get(gerador.nextInt(populacao.size()));
		}
		
		int posicao = posicoes.get(0);
		posicoes.remove(0);
		
		return populacao.get(posicao);
	}
	
	private void adicionarPosicoesEmListaParaRecombinar() {
		Collections.shuffle(populacao);
		
		double taxaRecombinacao = ((double) parametros.getTaxaRecombinacao()) / 100;
		double resultado = populacao.size() * taxaRecombinacao;
		
		int quantidadePaisRecombinacao = (int) resultado;
		
		for(int i = 0; i < quantidadePaisRecombinacao; i++){
			
			posicoes.add(populacao.indexOf(populacao.get(i)));
			
		}
		
		Collections.shuffle(posicoes);
	}
	
	private boolean isValorPresenteEmArray(int valor, int[] arrayComparar) {
		for (int j = 0; j < arrayComparar.length; j++) {
			if (valor == arrayComparar[j]) {
				return true;
			}
		}

		return false;
	}

}
