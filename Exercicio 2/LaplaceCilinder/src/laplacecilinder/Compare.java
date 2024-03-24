package laplacecilinder;

/**
 * Classe estatica responsavel pelos métodos de comparação entre valores double
 * @author Sperotto
 */
public class Compare {

    private static final double epsilon = 1.0e-5;

    /**
     * 
     * @param a
     * @param b
     * @return
     */
    public static boolean maior(double a, double b) {
        double delta;

        delta = Math.abs(a - b);
        if (delta < epsilon) {
            return false;
        } else if (a > b) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * 
     * @param a
     * @param b
     * @return
     */
    public static boolean maiorIgual(double a, double b) {
        double delta;

        delta = Math.abs(a - b);
        if (delta < epsilon) {
            return true;
        } else if (a > b) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 
     * @param a
     * @param b
     * @return
     */
    public static boolean menor(double a, double b) {
        double delta;

        delta = Math.abs(a - b);
        if (delta < epsilon) {
            return false;
        } else if (a < b) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * 
     * @param a
     * @param b
     * @return
     */
    public static boolean menorIgual(double a, double b) {
        double delta;

        delta = Math.abs(a - b);
        if (delta < epsilon) {
            return true;
        } else if (a < b) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 
     * @param a
     * @param b
     * @return
     */
    public static boolean igual(double a, double b) {
        double delta;

        delta = Math.abs(a - b);
        if (delta < epsilon) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 
     * @param a
     * @param b
     * @return
     */
    public static boolean diferente(double a, double b) {
        double delta;

        delta = Math.abs(a - b);
        if (delta > epsilon) {
            return true;
        } else {
            return false;
        }
    }
}
