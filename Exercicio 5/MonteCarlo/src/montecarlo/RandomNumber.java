package montecarlo;

/**
 *
 * @author lev_alunos
 */
public class RandomNumber {

    private static  long Seed = System.nanoTime();
    private static final long a = (long)Math.pow(2., 15.)+3;
    private static final long c = 0;//(long)(Math.random()*10);
    private static final long m = (long)Math.pow(2., 31.)-1;

    
    
    public static double getRand() {
        long rand = 0;
        System.out.println(Seed +" "+ m);
        rand = (a * Seed + c) % m;
        RandomNumber.Seed = rand;
        return (rand/m);
    }
}
