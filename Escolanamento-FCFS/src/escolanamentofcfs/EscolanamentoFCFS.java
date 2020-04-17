/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escolanamentofcfs;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Erick
 */
public class EscolanamentoFCFS {
         //---------------------------------------------------------------------------------//
            // Esse Codigo e baseado no  Escalonamento FCFS de sistema operacional.      //
    
    public static void main(String[] args) {
        
     Scanner sc = new Scanner(System.in);
     int armazenar, entrada, tempoAtual, teste = 0;
     
     double tempoExecucao, tempoEspera, turnaround;
     
     ArrayList processo  , tempoChegada, burst, tempoFinal, tempoInicial;
     
     String formato, saida;
     
     DecimalFormat D = new DecimalFormat("0.00");
     
        System.out.println("Quantos processos vai armazenar? ");
        armazenar = sc.nextInt();
        
        while(armazenar != 0) {
            teste++;
            processo = new ArrayList();
            tempoChegada = new ArrayList();
            burst = new ArrayList();
            tempoFinal = new ArrayList();
            tempoInicial = new ArrayList();
            tempoEspera = 0;
            tempoExecucao = 0;
            turnaround = 0;
            
            for(int i = 0; i < armazenar; i++) {
                System.out.println("Qual é o tempo de chegada do p" + (i + 1));
                entrada = sc.nextInt();
                tempoChegada.add(entrada);
                
                System.out.println("Qual é o Tempo do burst do P" + (i + 1));
                entrada = sc.nextInt();
                burst.add(entrada);
                processo.add(i);
        }
            tempoAtual = (int) tempoChegada.get(0);
            
            for(int i = 0; i< armazenar; i++) {
                if((int) tempoChegada.get(i) > tempoAtual){
                    tempoAtual = (int) tempoChegada.get(i);
            }
            tempoInicial.add(tempoAtual);
            tempoAtual += (int) burst.get(i);
            tempoFinal.add(tempoAtual);
            
            }
            for(int i =  0; i < armazenar; i++) {
                tempoExecucao += (int) tempoFinal.get(i) - (int) tempoChegada.get(i);
                tempoEspera   += (int) tempoInicial.get(i) - (int) tempoChegada.get(i);
            }
            System.out.println("Processamento = part" + teste);
            System.out.println("------------- FCSF -------------");
            
            for(int i = 0; i < armazenar; i++) {
                turnaround = (int) tempoFinal.get(i) - (int) tempoChegada.get(i);
                formato = D.format(turnaround);
                saida = "Turnaround P" + i + ":" + formato + "Ms";
                saida = saida.replace(",", ",");
                System.out.println(saida);
                System.out.println("==============================");
            }
            tempoExecucao = tempoExecucao/armazenar;
            tempoEspera = tempoEspera/armazenar;
            
            formato = D.format(tempoExecucao);
            saida = "Tempo medio da Execução :" + formato + "Ms";
            saida = saida.replace(",", ",");
            System.out.println(saida);
            System.out.println("==============================");
            
            formato = D.format(tempoEspera);
            saida = "Tempo Medio da Espera: " + formato + "Ms";
            saida = saida.replace(",", ",");
            System.out.println(saida);
            System.out.println("==============================");
            
            System.out.println("         Grafico De Grant       ");
            for(int i = 0; i < armazenar; i++) {
                System.out.print("P" + processo.get(i) + " ");
            }
            System.out.println();
            System.out.println("Que armazenar mas processos ?");
            armazenar = sc.nextInt();
        }
    }
}
