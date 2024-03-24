package montecarlo;

import java.util.Random;

/**
 * Classe que implementa métodos para o teste de momento e frequancia
 * @author Sperotto
 */
public class RandomTest {

    private Random rand;

    public double testMoment(long N, int k) {
        rand = new Random();
        double erro = 0.0;
        double soma = 0.0;
        double ref = (1.0 / (((double) k) + 1.0));

        for (long i = 0; i < N; i++) {
            soma += Math.pow(rand.nextDouble(), k);
        }
        soma /= ((double) N);
        erro = (Math.abs(ref - soma) / ref) * 100.0;
        return erro;
    }

    public double testFrequency(long N, long n) {

        double soma = 0.0;
        double div = ((double) N) / ((double) n);
        for (long i = 0; i < n; i++) {
            soma += Math.pow((rand.nextDouble() - div), 2);
        }
        soma *= ((double) n) / ((double) N);
        return soma;
    }
}
