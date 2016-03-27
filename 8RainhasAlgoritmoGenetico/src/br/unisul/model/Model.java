package br.unisul.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import br.unisul.controller.dto.ParametrosDTO;
import br.unisul.model.domain.Geracao;
import br.unisul.model.domain.Individuo;
import br.unisul.util.ArrayUtil;

public class Model {
	
	private static Random random;
	private ParametrosDTO parametros;
	private List<Individuo> solucoes;
	
	public Model(ParametrosDTO parametros) {
		this.parametros = parametros;
		solucoes = new ArrayList<Individuo>();
		if(random == null) {
			random = new Random();
		}
	}
	
	public List<Geracao> processar() {
		List<Individuo> populacao = inicializarPopulacao(parametros
				.getTamanhoPopulacaoInicial());

		List<Geracao> geracoes = new ArrayList<Geracao>();

		for (int i = 0; i < parametros.getNumeroGeracoes(); i++) {
			Geracao geracao = new Geracao();
			geracao.setNumeroGeracao(i + 1);
			geracao.setPopulacao(populacao);
			geracoes.add(geracao);

			System.out.println("Geração " + (i + 1) + " - " + populacao.size());

			avaliarPopulacao(geracao);
			
			List<Individuo> pais = selecionarPais(populacao);
			
			List<Individuo> populacaoNova = recombinarEaplicarMutacao(pais);
			
			populacao = populacaoNova;
		}

		return geracoes;
	}

	private List<Individuo> recombinarEaplicarMutacao(
			List<Individuo> populacao) {

		RecombinaHelper helper = new RecombinaHelper(populacao, parametros);
		
		List<Individuo> filhos = helper.recombinar();
		aplicarMutacao(filhos);
		
		populacao.addAll(filhos);

		return populacao;
	}
	
	private void aplicarMutacao(List<Individuo> populacao) {
		
		for (Individuo individuo : populacao) {
			
			if(vaiOcorrerMutacao()) {
				
				swap(individuo);
				individuo.setMutante(Boolean.TRUE);
				
			}
			
		}
		
	}
	
	private void swap(Individuo individuo) {
		
		int primeiraPosicao = random.nextInt(individuo.array.length);
		int segundaPosicao = random.nextInt(individuo.array.length);
		
		while(segundaPosicao == primeiraPosicao) {
			segundaPosicao = random.nextInt(individuo.array.length);
		}
		
		int valorDaPrimeiraPosicao = individuo.array[primeiraPosicao];
		int valorDaSegundaPosicao = individuo.array[segundaPosicao];
		
		individuo.array[primeiraPosicao] = valorDaSegundaPosicao;
		individuo.array[segundaPosicao] = valorDaPrimeiraPosicao;
	}
	
	private boolean vaiOcorrerMutacao() {
		double taxaMutacao = ((double) parametros.getTaxaMutacao()) / 100.0;
		
		if(Math.random() <= taxaMutacao) {
			return true;
		} else {
			return false;
		}
	}
	
	
	private List<Individuo> inicializarPopulacao(int tamanhoPopulacao) {
		
		List<Individuo> populacao = new ArrayList<Individuo>();
		
		for(int i = 0; i < tamanhoPopulacao; i++) {
			Individuo individuo = new Individuo();
			
			int[] array = {0, 1, 2, 3, 4, 5, 6, 7};
			ArrayUtil.shuffle(array);
			
			individuo.array = array;
			
			populacao.add(individuo);
		}
		
		return populacao;
	}
	
	private void avaliarPopulacao(Geracao geracao) {
		for (Individuo individuo : geracao.getPopulacao()) {
			individuo.qtdColisoes = contarConflitos(individuo.array);
			
			if(individuo.qtdColisoes == 0 && !isArrayPresenteEmListaSolucoes(individuo.array)) {
				individuo.setNumeroGeracao(geracao.getNumeroGeracao());
				solucoes.add(individuo);
			}
		}
	}
	
	private int contarConflitos(int[] array) {
		int conflitos = 0;
		
		for(int posicaoSendoAnalisada = 0; posicaoSendoAnalisada < 8; posicaoSendoAnalisada++) {
			
			int x = 1;
			
			for(int contador = posicaoSendoAnalisada + 1; contador < 8; contador++) {
				
				
				if(array[posicaoSendoAnalisada]+x == array[contador]) {
					conflitos++;
				}
				
				x++;
			}
			
			x = 1;
			
			for(int contador = posicaoSendoAnalisada + 1; contador < 8; contador++) {
				
				if(array[posicaoSendoAnalisada]-x == array[contador]) {
					conflitos++;
				}
				
				x++;
			}
				
		}
		
		return conflitos;
	}
	
	private List<Individuo> selecionarPais(List<Individuo> populacao) {
		
		List<Individuo> paisSelecionados = new ArrayList<Individuo>();
		
		int quantidadePais = populacao.size() / parametros.getDivisorPopulacao();
		
		paisSelecionados.addAll(selecionarIndividuosModoElitista(populacao, quantidadePais));
		
		return paisSelecionados;
	}
	
	@SuppressWarnings("unchecked")
	private List<Individuo> selecionarIndividuosModoElitista(List<Individuo> populacao, int quantidadeIndividuos) {
		
		List<Individuo> individuosSelecionados = new ArrayList<Individuo>();
		
		Map<Integer, Integer> mapPosicoesEcolisoes = new HashMap<Integer, Integer>();
		
		for (Individuo individuo : populacao) {
			
			mapPosicoesEcolisoes.put(populacao.indexOf(individuo), individuo.qtdColisoes);
			
		}
		
		mapPosicoesEcolisoes = ArrayUtil.sortByValues(mapPosicoesEcolisoes);
		
		int index = 0;
		
		for (Integer posicao : mapPosicoesEcolisoes.keySet()) {
			
			if(index == quantidadeIndividuos)
				break;
			
			individuosSelecionados.add(populacao.get(posicao));
			
			index++;
		}
		
		return individuosSelecionados;
	}

    private boolean isArrayPresenteEmListaSolucoes(int[] array) {
		for (Individuo individuo : solucoes) {

			if (Arrays.equals(individuo.array, array)) {
				return true;
			}

		}

		return false;
	}

	public List<Individuo> getSolucoes() {
		return solucoes;
	}

}
