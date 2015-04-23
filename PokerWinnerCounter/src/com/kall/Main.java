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
			
			// valor correspondente ao nivel da mao do jogador
			int nivelP1 = 0;
			int nivelP2 = 0;
			
			br = new BufferedReader(new FileReader("poker.txt"));
			while ((linha = br.readLine()) != null)
			{
				mao1 = new Mao();
				mao2 = new Mao();
				
				// Separa as cartas dos jogadores
				Funcoes.SepararMaos(linha, mao1, mao2);
				
				// Verifica o nivel da mao de cada um
				nivelP1 = Funcoes.CalcularValorMao(mao1);
				nivelP2 = Funcoes.CalcularValorMao(mao2);
		
				// Verifica quem tem a melhor mao
				if (nivelP1 == nivelP2) {
					mao1.OrdenarVetorDesempate(nivelP1);
					mao2.OrdenarVetorDesempate(nivelP2);
					if (Funcoes.Desempatar(nivelP1, mao1, mao2)) {
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
