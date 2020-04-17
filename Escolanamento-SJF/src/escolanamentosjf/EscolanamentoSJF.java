/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escolanamentosjf;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Erick
 */
public class EscolanamentoSJF {

     //---------------------------------------------------------------------------------//
            // Esse Codigo e baseado no  Escalonamento SJF de sistema operacional.      //
    
    public static void main(String[] args) {
         Scanner sc = new Scanner(System.in);
         int armazenar, entrada;
         ArrayList processo, chegada, cpuChegada = new ArrayList(), burst;
         int[] tempoFinal = new int[1], tempoInicial = new int[1];
         int processoAtualId;
         String ordemExecucao = "", formato, saida;
         double tempoEspera, tempoExecucao, turnaround;
         int contTeste = 0;
         DecimalFormat D = new DecimalFormat("0.00");
         
         System.out.println("Quantos processos vai armazenar? ");
         armazenar = sc.nextInt();
         
         while(armazenar != 0) {
             contTeste++; 
             ordemExecucao = " ";
             processo = new ArrayList();
             chegada  = new ArrayList();
             burst  = new ArrayList();
             tempoFinal = new int[armazenar];
             tempoInicial = new int[armazenar];
             
             for(int i = 0; i<armazenar;i++) {
                 System.out.println("Qual é o tempo de chegada do P" + (i + 1));
                 entrada = sc.nextInt();
                 chegada.add(entrada);
                       
                 System.out.println("Qual é o tempo de burst de P" + (i + 1));
                 entrada = sc.nextInt();
                 burst.add(entrada);
           }
             cpuChegada =(ArrayList) burst.clone();
             int execucao;
             int qtdProcesso = armazenar;
             int tempoAtual = (int) chegada.get(0);
             while(qtdProcesso > 0 ) {
                 processo = new ArrayList();
                 for(int i = 0; i < armazenar; i++) {
                     if((int) chegada.get(i) != -1 && (int) chegada.get(i) <= tempoAtual) {
                         processo.add(i);
                    }
                 }
                 if(processo.isEmpty()) {
                     tempoAtual++;
                 }else{
                     execucao = (int) processo.get(0);
                     for(int i = 0; i < processo.size();i++) {
                         processoAtualId = (int) processo.get(i);
                         if ((int) burst.get(processoAtualId) < (int) burst.get(execucao)){
                               execucao = (int) processo.get(i);
                         }
                     }
                     tempoInicial[execucao] = tempoAtual;
                     tempoAtual += (int) burst.get(execucao);
                     tempoFinal[execucao] = tempoAtual;
                     chegada.set(execucao, -1);
                     ordemExecucao += "P" + (execucao + 1) + " ";
                     qtdProcesso--;
                 }
             }
             tempoExecucao = 0;
             tempoEspera = 0;
             for(int i = 0; i < armazenar;i++) {
                 tempoExecucao += tempoFinal[i] - (int) cpuChegada.get(i);
                 tempoEspera += tempoInicial[i] - (int) cpuChegada.get(i);
             }
             System.out.println("Processamento = Part " + contTeste);
             
                for(int i = 0; i <armazenar; i++) {
                    turnaround = (int) tempoFinal[i] - (int) cpuChegada.get(i);
                    formato = D.format(turnaround);
                    saida = "||Turnaround|| P" + i + ":" + formato + "Ms";
                    saida = saida.replace(".", ",");
                    System.out.println(saida);
            }   
                tempoExecucao = tempoExecucao/armazenar;
                tempoEspera = tempoEspera/armazenar;
                
                formato  = D.format(tempoExecucao);
                saida = "Tempo Medio De Execução: " + formato + "S";
                saida = saida.replace(".", ",");
                System.out.println(saida);
                
                formato = D.format(tempoEspera);
                saida = "Tempo Medio de Espera: " + formato + "S";
                saida = saida.replace(".", ",");
                System.out.println(saida);
                
                System.out.println(ordemExecucao);
                System.out.println();
                System.out.println("Que armazenar mas processos ?");
                armazenar = sc.nextInt();
                
        }
         
    }
    
}
