package com.kall;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {

	public static void main(String[] args) {
		
		// variaveis para leitura e escrita do arquivo de cartas e de resposta
		BufferedReader br;
		PrintWriter writer = null;
		String linha;
		
		// Contadores do numero de vitorias de cada jogador
		int contadorP1 = 0;
		int contadorP2 = 0;

		try 
		{
			// colecao de cartas de cada jogador
			Mao mao1;
			Mao mao2;
			
			/*
			 * Valore correspondente ao nivel da mao do jogador
			 * 1 - High card
			 * 2 - One Pair
			 * 3 - Two Pairs
			 * 4 - Three of a Kind
			 * 5 - Straight
			 * 6 - Flush
			 * 7 - Full House
			 * 8 - Four of a Kind
			 * 9 - Straight Flush
			 * 10 - Royal Flush
			 */
			int nivelP1 = 0;
			int nivelP2 = 0;
			
			br = new BufferedReader(new FileReader("poker.txt"));
			while ((linha = br.readLine()) != null)
			{
				mao1 = new Mao();
				mao2 = new Mao();
				
				// Separa as cartas dos jogadores
				String []vLinha = linha.split(" ");
				mao1.DistribuirCartas(vLinha, 1);
				mao2.DistribuirCartas(vLinha, 2);
				
				// Verifica o nivel da mao de cada um
				nivelP1 = mao1.CalcularValorMao();
				nivelP2 = mao2.CalcularValorMao();
		
				// Verifica quem tem a melhor mao
				if (nivelP1 == nivelP2) {
					// Empate
					mao1.OrdenarVetorDesempate();
					mao2.OrdenarVetorDesempate();
					if (mao1.Desempatar(mao2)) {
						contadorP1++;
					} else {
						contadorP2++;
					}
				} else {
					if (nivelP1 > nivelP2) {
						contadorP1++;
					} else {
						contadorP2++;
					}
				}
			}
			
			writer = new PrintWriter("resultado.txt", "UTF-8");
			writer.println("Número de vitorias do jogador 1: " + contadorP1);
			
		} catch (IOException e) {
			System.out.println("catch!" + e);
		}
		finally {
			if (writer != null) {
				writer.close();
			}
		}
	}
	
}
