package montecarlo;

import java.util.Random;

/**
 * Classe Com a implementação dos métodos de monte carlo, tamanhos da placa no fonte...
 * @author Sperotto
 */
public class MonteCarloAppl {
    
    public double SolveFixedRandomWalk(double xo, double yo, int nrun) {
        
        double delta = 0.05; //step size
        double A = 10.0, B = 10.0;
        int v1 = 100, v2 = 50, v3 = 50, v4 = 75;
        double sum = 0;
        
        Random rand = new Random();
        
        for (int k = 0; k < nrun; k++) {
            double i = xo;
            double j = yo;
            while (true) {
                double r = rand.nextDouble(); // random number between 0 and 1

                if (Compare.maiorIgual(r, 0.0) & Compare.menor(r, 0.25)) {
                    i += delta;
                }
                if (Compare.maiorIgual(r, 0.25) & Compare.menor(r, 0.5)) {
                    i -= delta;
                }
                if (Compare.maiorIgual(r, 0.5) & Compare.menor(r, 0.75)) {
                    j += delta;
                }
                if (Compare.maiorIgual(r, 0.75) & Compare.menor(r, 1.0)) {
                    j -= delta;
                }
                // check if (i,j) is on the boundary
                if (Compare.menorIgual(i, 0.0)) {
                    sum += v4;
                    break;
                }
                if (Compare.maiorIgual(i, A)) {
                    sum += v2;
                    break;
                }
                if (Compare.menorIgual(j, 0.0)) {
                    sum += v1;
                    break;
                }
                if (Compare.maiorIgual(j, B)) {
                    sum += v3;
                    break;
                }
            } //end while
        }// end for
        double v = sum / nrun;
        return v;
    }
    
    public double SolveFloatingRandomWalk(double xo, double yo, int nrun) {
        
        double A = 10.0, B = 10.0;
        int v1 = 100, v2 = 50, v3 = 50, v4 = 75;
        
        double vv;        
        Random rand = new Random();
        double sum = 0.0;
        for (int k = 1; k <= nrun; k++) {
            double x = xo;
            double y = yo;
            while (true) {
                double u = rand.nextDouble(); // generate a random no. and move to the next point
                double phi = 2.0 * Math.PI * u;
                // find the shortest distance r;
                double rx = 0.0;
                double ry = 0.0;
                double r = 0.0;
                rx = Math.min(x - 0, A - x);
                ry = Math.min(y - 0, B - y);                
                r = Math.min(rx, ry);
                x += r * Math.cos(phi);
                y += r * Math.sin(phi);

                // check if (x,y) is on the boundary
                if (Compare.maiorIgual(x, A)) {
                    sum += v2;
                    break;
                }
                if (Compare.maiorIgual(y, B)) {
                    sum += v3;
                    break;
                }
                if (Compare.menorIgual(x, 0.0)) {
                    sum += v4;
                    break;
                }
                if (Compare.menorIgual(y, 0.0)) {
                    sum += v1;
                    break;
                }
            }//end %while
        }//end % nrun
        vv = sum / nrun;
        return vv;
    }
    
    public void SolveExodusMethod() {
//        // This program uses the Exodus method to solve Laplace's equation
//        double a = 1.5, b = 1, h = 0.05;
//        double xo = 0.8, yo = 0.2;
//        double imax = a / h;
//        double jmax = b / h;
//        double io = xo / h;
//        double jo = yo / h;
//        double N = Math.pow(10, 6); // total no. of particles injected
//        int nmax = 500;
////double P = zeros(imax+1,jmax+1);
//        double[][] P = new double[(int) imax + 1][(int) jmax + 1];
//        P[(int) io + 1][(int) jo + 1] = N;
//        double sum1 = 0, v1 = 10; // bottom side
//        double sum2 = 0, v2 = 100; // right side
//        double sum3 = 0, v3 = 40; // top side
//        double sum4 = 0, v4 = 0; // left side
//        for (int n = 1; n <= nmax; n++) {
//// scan the free nodes
//            for (int i = 2; i <= imax; i++) {
//                for (int j = 2; j <= jmax; j++) {
//                    P[i + 1][j] += 0.25 * P[i][j];
//                    P[i - 1][j] += 0.25 * P[i][j];
//                    P[i][j + 1] += 0.25 * P[i][j];
////source code incomplete and incorrect.....
//                }
//            }
//        }
    }
}
