package br.unisul.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.unisul.model.domain.Individuo;

public class Model {
	
	private static Random random;
	
	public List<Individuo> inicializarPopulacao(int tamanhoPopulacao) {
		
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
	
	public void avaliarPopulacao(List<Individuo> populacao) {
		
		for (Individuo individuo : populacao) {
			individuo.qtdColisoes = contarConflitos(individuo.array);
			
			for(int i = 0; i < individuo.array.length; i++) {
				System.out.print(individuo.array[i] + ", ");
			}
			
			System.out.println("Colisoes = " + individuo.qtdColisoes);
		}
		
	}
	
	private int contarConflitos(int[] array) {
		//0, 1, 2, 3, 4, 5, 6, 7
		//2, 5, 7, 1, 3, 6, 0, 4
		
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

}
