package montecarlo;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Sperotto
 */
public class MonteCarlo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

//******************************************************************************
//  Teste de momento, printado em tela.....
//******************************************************************************
//        RandomTest RT = new RandomTest();
//        for (int j = 3; j <= 10; j++) {
//            for (int i = 1; i < 20; i++) {
//                System.out.print("N: "+ Math.pow(5, j)+", Erro do Momento["+i+"]: " + RT.testMoment((long)Math.pow(5, j), i)+ " %\n");
//            }
//        }
        long t0 = 0, tf = 0, t01 = 0, tf1 = 0;
        for (int NN = 3; NN < 7; NN++) {
            t0 = System.currentTimeMillis();
            t01 = System.nanoTime();
            System.out.println("Numero de Passos: " + (int) Math.pow(5, NN));
            //PrintResultFile PC = new PrintResultFile();
            // PC.initFile("C:\\Users\\lev_alunos\\Dropbox\\Mestrado\\1 - Semestre 2012\\CE-294 - Métodos Numéricos e Aplicações em Clusters I - Básico\\Exercicio 5\\Coord" + (int) Math.pow(5, NN) + ".dat");

            ArrayList<ArrayList<Double>> Result = new ArrayList<ArrayList<Double>>();
            for (int k = 0; k < 10; k++) {
                MonteCarloAppl al = new MonteCarloAppl();
                ArrayList<Double> List = new ArrayList<Double>();
                for (int i = 1; i <= 9; i++) {
                    for (int j = 1; j <= 9; j++) {
                        List.add(al.SolveFixedRandomWalk(((double) i), ((double) j), (int) Math.pow(5, NN)));
                        //List.add(al.SolveFloatingRandomWalk(((double) i), ((double) j), (int) Math.pow(5, NN)));
                        //PC.escreveFile(i, j);
                        System.out.print(i + j);
                    }
                }
                Result.add(List);
            }
// PC.endFile();
            System.out.print("\n\n");

            double[] media = new double[Result.get(1).size()];
            double[] variancia = new double[Result.get(1).size()];
            double[] erro = new double[Result.get(1).size()];
            PrintResultFile PM = new PrintResultFile();
            PM.initFile("C:\\Users\\lev_alunos\\Dropbox\\Mestrado\\1 - Semestre 2012\\CE-294 - Métodos Numéricos e Aplicações em Clusters I - Básico\\Exercicio 5\\media" + (int) Math.pow(5, NN) + ".dat");
            for (int i = 0; i < media.length; i++) {
                media[i] = 0.0;
                variancia[i] = 0.0;
                erro[i] = 0.0;
            }
            for (int i = 0; i < media.length; i++) {
                for (int k = 0; k < 10; k++) {

                    media[i] += Result.get(k).get(i);
                }
                media[i] /= 10.0;
                PM.escreveFile(media[i]);
            }
            PM.endFile();
            PrintResultFile PV = new PrintResultFile();
            PV.initFile("C:\\Users\\lev_alunos\\Dropbox\\Mestrado\\1 - Semestre 2012\\CE-294 - Métodos Numéricos e Aplicações em Clusters I - Básico\\Exercicio 5\\variancia" + (int) Math.pow(5, NN) + ".dat");
            for (int i = 0; i < media.length; i++) {
                double soma = 0.0;
                for (int k = 0; k < 10; k++) {
                    soma += Math.pow(Result.get(k).get(i) - (media[i]), 2);
                }
                soma /= 9;
                variancia[i] = Math.sqrt(soma);
                PV.escreveFile(variancia[i]);
            }
            PV.endFile();

            PrintResultFile PE = new PrintResultFile();
            PE.initFile("C:\\Users\\lev_alunos\\Dropbox\\Mestrado\\1 - Semestre 2012\\CE-294 - Métodos Numéricos e Aplicações em Clusters I - Básico\\Exercicio 5\\erro" + (int) Math.pow(5, NN) + ".dat");
            for (int i = 0; i < media.length; i++) {
                erro[i] = (variancia[i] * 2.262) / 10.0;
                PE.escreveFile(erro[i]);
            }
            PE.endFile();
            tf = System.currentTimeMillis();
            tf1 = System.nanoTime();
            System.out.println("Tempo de Execução: " + (tf - t0) + " ou " + (tf1 - t01));

        }
    }
}
