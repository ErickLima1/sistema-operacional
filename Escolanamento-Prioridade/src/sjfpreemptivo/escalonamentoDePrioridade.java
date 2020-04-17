/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sjfpreemptivo;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Erick
 */
public class escalonamentoDePrioridade {
          //---------------------------------------------------------------------------------//
            // Esse Codigo e baseado no  Escalonamento SJF de sistema operacional.      //
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int armazenar, entrada, tempoAtual, execucao, processoAtualId, qtdProcesso, conteTeste = 0;
        ArrayList entrada1, burst, prioridade, processo, cpuEntrada;
        int[] tempoFinal, tempoInicial;
        String ordemExecucao, formato, saida;
        DecimalFormat D = new DecimalFormat("0.00");
        
        System.out.println("Quantos Processo vai Armazenar ");
        armazenar = sc.nextInt();
        
        while(armazenar != 0) {
            conteTeste++;
            processo = new ArrayList();
            entrada1 = new ArrayList();
            burst = new ArrayList();
            prioridade = new ArrayList();
            
            for(int i = 0; i < armazenar; i++) {
                System.out.println("Entrada do processo P" + (i +1));
                entrada = sc.nextInt();
                entrada1.add(entrada);
                
                System.out.println("Qual é o tempo de burst de P" + (i + 1));     
                entrada = sc.nextInt();
                burst.add(entrada);
                
                System.out.println("Qual é a prioridade do P" + (i + 1));
                entrada = sc.nextInt();
                prioridade.add(entrada);
            }
            tempoInicial = new int[armazenar];
            tempoFinal = new int[armazenar];
            cpuEntrada =  (ArrayList) entrada1.clone();
            tempoAtual = (int) entrada1.get(0);
            ordemExecucao = "";
            
            qtdProcesso = armazenar;
            while(qtdProcesso > 0) {
                processo = new ArrayList();
                for(int i = 0; i < armazenar; i++) {
                    if((int) entrada1.get(i) != -1 && (int) entrada1.get(i) <= tempoAtual) {
                        processo.add(i);
                    }
                }
                if(processo.isEmpty()) {
                    tempoAtual++;
                }else{
                    execucao = (int) processo.get(0);
                    for(int i = 0; i < processo.size(); i ++) {
                        processoAtualId = (int) processo.get(i);
                        if((int) prioridade.get(processoAtualId) < (int)prioridade.get(execucao)) {
                            execucao = (int) processo.get(i);
                        }
                    }
                    tempoInicial[execucao] = tempoAtual;
                    tempoAtual += (int) burst.get(execucao);
                    tempoFinal[execucao] = tempoAtual;
                    entrada1.set(execucao, -1);
                    ordemExecucao += "P" + (execucao + 1) + " ";
                    qtdProcesso--;
                }
            }
            double tempoExecucao = 0, tempoEspera = 0;
            for(int i = 0; i< armazenar; i ++) {
                tempoExecucao += tempoFinal[i] - (int) cpuEntrada.get(i);
                tempoEspera += tempoInicial[i] - (int) cpuEntrada.get(i);
            }
            tempoExecucao = tempoExecucao/ armazenar;
            tempoEspera = tempoEspera/ armazenar;
            System.out.println("Processamento - Part" + conteTeste);
            
            formato = D.format(tempoExecucao);
            saida = "Tempo Medio de Execução:" + formato + "S";
            saida = saida.replace(".", ",");
            System.out.println(saida);
            
            formato = D.format(tempoEspera);
            saida = "Tempo Medio de Espera:" + formato + "S";
            saida = saida.replace(".", ",");
            System.out.println(saida);
            
            System.out.println(ordemExecucao);
            System.out.println();
            System.out.println("Que armazenar mas processos ?");
            armazenar = sc.nextInt(); 
        }
        
    }
    
}
