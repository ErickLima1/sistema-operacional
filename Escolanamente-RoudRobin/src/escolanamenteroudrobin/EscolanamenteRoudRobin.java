/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escolanamenteroudrobin;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Erick
 */
public class EscolanamenteRoudRobin {
    //---------------------------------------------------------------------------------//
   // Esse Codigo e baseado no  Escalonamento Round Robin de sistema operacional.      //
    
    public static void main(String[] args) {
       Scanner sc = new Scanner(System.in);
       int quantum, armazenar, entrada, tempoAtual, execucao,q, tempoFinal[];
       
       int qtdPessoas, burstNovo, tempoExecucao[]; 
       ArrayList chegada, burst, processo, cpuChegada, cpuBurst;
       
       double tempoMedioExecucao, tempoMedioEspera, turnaround;
       
       int contTeste = 0;
       
       String formato, saida, ordem;
       
       DecimalFormat D = new DecimalFormat("0.00");
       
        System.out.println("Quantos processadores vai armazenar ?");
        armazenar = sc.nextInt();
        
        System.out.println("Qual o valor  Quantum ?");
        quantum = sc.nextInt();
        
        while(armazenar != 0) {
            contTeste++;
            processo = new ArrayList();
            chegada = new ArrayList();
            burst = new ArrayList();
            ordem = "";
            qtdPessoas = armazenar;
            tempoFinal = new int [armazenar];
            tempoExecucao = new int [armazenar];
            
            for(int i = 0; i < armazenar; i++) {
                System.out.println("Qual é o tempo de chegada do P" + (i + 1));
                entrada = sc.nextInt();
                chegada.add(entrada);
                        
                System.out.println("Qual é o Tempo do burst do P" + (i + 1));
                entrada = sc.nextInt();
                burst.add(entrada);
            }
            cpuChegada = (ArrayList) chegada.clone();
            cpuBurst = (ArrayList) burst.clone();
            tempoAtual = (int) chegada.get(0);
            processo = new ArrayList();
            processo = new ArrayList();
            
            while(qtdPessoas > 0) {
                for(int i=0; i< armazenar;i++) {
                    if((int)chegada.get(i) != -1 && (int) chegada.get(i) <= tempoAtual) {
                        processo.add(i);
                        chegada.set(i, -1);
                    }
                }
                if(processo.isEmpty()) {
                    tempoAtual++;
                }else{
                    execucao = (int) processo.remove(0);
                    ordem += "p" + (execucao + 1) + " ";
                    q = quantum;
                    
                    while(q > 0 && (int) burst.get(execucao) > 0) {
                        tempoAtual++;
                        q--;
                        burstNovo = (int) burst.get(execucao) - 1; 
                        burst.set(execucao, burstNovo);
                    }
                    if((int) burst.get(execucao) > 0) {
                        for(int i= 0; i < armazenar;i++) {
                           if((int) chegada.get(i) != -1 && (int) chegada.get(i) <= tempoAtual) {
                               processo.add(i);
                               chegada.set(i, -1);
                           } 
                        }
                        processo.add(execucao);
                    }else{
                        tempoFinal[execucao] = tempoAtual;
                        qtdPessoas--;
                }
            }
        }
            tempoMedioExecucao = 0;
            tempoMedioEspera = 0;
            for(int i = 0; i<armazenar; i++) {
                tempoExecucao[i] = tempoFinal[i] - (int) cpuChegada.get(i);
                tempoMedioExecucao += tempoExecucao[i];
                tempoMedioEspera += tempoExecucao[i] - (int) cpuBurst.get(i);
            }
            tempoMedioExecucao = tempoMedioExecucao/armazenar;
            tempoMedioEspera = tempoMedioEspera/armazenar;
            System.out.println("Processamento = part " + contTeste);
            
            for(int i = 0; i < armazenar;i++) {
            turnaround = (int) tempoFinal[i] - (int) cpuChegada.get(i);
            formato = D.format(turnaround);
            saida =  "||TurnaRoud|| p" + i + ":"+ formato + "Ms";
            saida = saida.replace(",", ",");
            System.out.println(saida);
            System.out.println("==============================");
        }
            formato = D.format(tempoMedioExecucao);
            saida = "Tempo Medio de Execução: " + formato + "S";
            saida = saida.replace(",", ",");
            System.out.println(saida);
             System.out.println("==============================");
            
            
            formato = D.format(tempoMedioEspera);
            saida = "Tempo Medio  de Espera: " + formato + "S";
            saida = saida.replace(",", ",");
            System.out.println(saida);
            System.out.println("==============================");
            
            System.out.println(ordem);
            System.out.println();
            System.out.println("Que armazenar mas processos ?");
            armazenar = sc.nextInt();
        }
    }
}
