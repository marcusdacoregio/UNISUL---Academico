package br.unisul.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import br.unisul.controller.dto.ParametrosDTO;
import br.unisul.model.domain.Individuo;

public class Model {
	
	private static Random random;
	private ParametrosDTO parametros;
	
	public Model(ParametrosDTO parametros) {
		this.parametros = parametros;
	}
	
	public void obterMelhorSolucao() {
		List<Individuo> populacao = inicializarPopulacao(parametros.getTamanhoPopulacaoInicial());
		
		for(int i = 0; i < parametros.getNumeroGeracoes(); i++) {
			
			avaliarPopulacao(populacao);
			populacao = selecionarPais(populacao);
			recombinarEaplicarMutacao(populacao);
		}
	}
	
	private void recombinarEaplicarMutacao(List<Individuo> populacaoPais) {
		
		List<Individuo> filhosGerados = new ArrayList<Individuo>();
		
		int quantidadeIndividuosParaRecombinar = populacaoPais.size() * (parametros.getTaxaRecombinacao() / 100);
		
		List<Integer> posicoesAleatorias = gerarPosicoesAleatoriasDeArray(populacaoPais, quantidadeIndividuosParaRecombinar);
		
		List<Integer> posicoesJaReproduzidas = new ArrayList<Integer>();
		
		for (Integer posicaoPai : posicoesAleatorias) {
			
			posicoesJaReproduzidas.add(posicaoPai);
			
			int posicaoMae = escolherNumeroRandomEmLista(posicoesAleatorias, posicoesJaReproduzidas);
			
			posicoesJaReproduzidas.add(posicaoMae);
			
			
			
		}
		
	}
	
	private void aplicarCutAndCrossfill(Individuo pai, Individuo mae) {
		
		int pontoDeCorte = obterPontoDeCorte(pai.array.length);
		
		//FALTA TERMINAR AQUI
		
	}
	
	private int obterPontoDeCorte(int tamanhoArray) {
		
		if(random == null)
			random = new Random();
		
		if(parametros.isPontoCorteAleatorio()) {
			return random.nextInt(tamanhoArray-2) + 1;
		} else {
			if(parametros.getPontoCorte() > tamanhoArray-1) {
				throw new IllegalArgumentException("O ponto de corte deve estar entre 1 e " + (tamanhoArray-1));
			} else {
				return parametros.getPontoCorte();
			}
		}
		
	}
	
	private List<Integer> gerarPosicoesAleatoriasDeArray(List<Individuo> populacao, int quantidadePosicoes) {
		
		Set<Integer> indexIndividuos = new HashSet<Integer>();
		
		while(indexIndividuos.size() < quantidadePosicoes) {
			
			if(random == null){
				random = new Random();
			}
			
			indexIndividuos.add(random.nextInt(populacao.size()));
			
		}
		
		return new ArrayList<Integer>(indexIndividuos);
	}
	
	private Integer escolherNumeroRandomEmLista(List<Integer> lista, List<Integer> numerosJaEscolhidos) {
		if(random == null)
			random = new Random();
		
		Integer numeroRandom = null;
		
		do{
			
			numeroRandom = lista.indexOf(random.nextInt(lista.size()));
			
		} while(numerosJaEscolhidos.contains(numeroRandom));
		
		
		return numeroRandom;
	}
	
	private List<Individuo> inicializarPopulacao(int tamanhoPopulacao) {
		
		List<Individuo> populacao = new ArrayList<Individuo>();
		
		for(int i = 0; i < tamanhoPopulacao; i++) {
			Individuo individuo = new Individuo();
			
			int[] array = {0, 1, 2, 3, 4, 5, 6, 7};
			shuffle(array);
			
			individuo.array = array;
			
			populacao.add(individuo);
		}
		
		return populacao;
	}
	
	private void avaliarPopulacao(List<Individuo> populacao) {
		
		for (Individuo individuo : populacao) {
			individuo.qtdColisoes = contarConflitos(individuo.array);
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
		
		int quantidadePais = populacao.size() / parametros.getDivisorPopulacao();
		
		switch (parametros.getTipoSelecaoPais()) {
			case ELITISTA:
				return populacao = selecionarPaisModoElitista(populacao, quantidadePais);
				
			case ROLETA:
				
				return null;
		}
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	private List<Individuo> selecionarPaisModoElitista(List<Individuo> populacao, int quantidadePais) {
		
		List<Individuo> paisSelecionados = new ArrayList<Individuo>();
		
		Map<Integer, Integer> mapPosicoesEcolisoes = new HashMap<Integer, Integer>();
		
		for (Individuo individuo : populacao) {
			
			mapPosicoesEcolisoes.put(populacao.indexOf(individuo), individuo.qtdColisoes);
			
		}
		
		mapPosicoesEcolisoes = sortByValues(mapPosicoesEcolisoes);
		
		int index = 0;
		
		for (Integer posicao : mapPosicoesEcolisoes.keySet()) {
			
			if(index == quantidadePais)
				break;
			
			paisSelecionados.add(populacao.get(posicao));
			
			index++;
		}
		
		return paisSelecionados;
	}

    /**
     * Code from method java.util.Collections.shuffle();
     */
    private void shuffle(int[] array) {
        if (random == null) random = new Random();
        int count = array.length;
        for (int i = count; i > 1; i--) {
            swap(array, i - 1, random.nextInt(i));
        }
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	private HashMap sortByValues(Map map) {
        List list = new LinkedList(map.entrySet());
        // Defined Custom Comparator here
        Collections.sort(list, new Comparator() {
             public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry) (o1)).getValue())
                   .compareTo(((Map.Entry) (o2)).getValue());
             }
        });

        // Here I am copying the sorted list in HashMap
        // using LinkedHashMap to preserve the insertion order
        HashMap sortedHashMap = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext();) {
               Map.Entry entry = (Map.Entry) it.next();
               sortedHashMap.put(entry.getKey(), entry.getValue());
        } 
        
        return sortedHashMap;
   }
    
    private void exibirPopulacao(List<Individuo> populacao) {
    	
    	for (Individuo individuo : populacao) {
			
    		for (int i : individuo.array) {
				System.out.print(i + ", ");
			}
    		
    		System.out.println("Colisões = " + individuo.qtdColisoes);
		}
    	
    }

}
