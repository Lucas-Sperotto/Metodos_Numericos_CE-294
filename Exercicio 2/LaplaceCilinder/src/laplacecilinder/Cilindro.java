package laplacecilinder;

import JSci.maths.matrices.AbstractDoubleMatrix;
import JSci.maths.matrices.DoubleMatrix;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sperotto
 */
public class Cilindro {

    /**
     * Raio Externo do Cilindro
     */
    private double Rext;
    /**
     * Raio Interno do Cilindro
     */
    private double Rint;
    /**
     * Comprimento Externo
     */
    private double Lext;
    /**
     * Comprimento Interno
     */
    private double Lint;
    /**
     * Potencial Externo
     */
    private double Vext;
    /**
     * Potencial Interno
     */
    private double Vint;
    /**
     * Objeto para o tipo de dado Malha
     */
    public Malha Grid;
    /**
     * Matriz Global
     */
    private AbstractDoubleMatrix U;

    /**
     * Construtor Padrão
     */
    public Cilindro() {
          Grid = new Malha();
    }

    /**
     * Le parâmetros externos (potenciais)
     * @param V1
     * @param V2
     */
    public void LePotenciais(double V1, double V2) {
        Vext = V2;
        Vint = V1;
    }

    /**
     * Lê parâmetros geoméricos do cilindro
     * @param R1 double - raio interno
     * @param R2 double - raio externo (R2 > R1)
     * @param L1 double - comprimento interno
     * @param L2 double - comprimento externo
     */
    public void LeGeometria(double R1, double R2, double L1, double L2) {
        Rint = R1;
        Rext = R2;
        Lint = L1;
        Lext = L2;
    }

    /**
     * Gera malha para o potêncial e aplica condiçõoes de fronteira fixas
     */
//    public void GeraMalha() {

//        int k, i, I, K;
//        Grid.del = Rext / (double) Grid.Ngrid;
//        //Densidade da malha calculo de inteiros aproximados para dimensionamento da malha
//        Grid.Nrho = Grid.Ngrid;
//        Grid.Nz = (int) (Lext / (2. * Grid.del));
//        //aloca memória para a matriz do campo de potencial na malha
//        U = new DoubleMatrix(Grid.Nrho, Grid.Nz);
//       
//        //Condições de Neumann para o cilindro externo
//        for (k = 0; k < Grid.Nz; k++) {
//            U.setElement(Grid.Nrho - 1, k, Vext);// U[Grid.Nrho-1][k]=Vext;
//        }
//        //Condiçõoes de Neumann para a tampa externo
//        for (i = 0; i < Grid.Nrho; i++) {
//            U.setElement(Grid.Nz - 1, i, Vext);// U[i][Grid.Nz-1]=Vext;
//        }
//        //Condições para o cilindro interno
//        //Primeiro calcula os números K e I
//        Grid.I = (int) (Rint / Grid.del);
//        Grid.K = (int) (Lint / (2. * Grid.del));
//        I = Grid.I;
//        K = Grid.K;
//        //condiçãao de Neumann para o cilindro interna
//        for (k = 0; k <= K; k++) {
//            U.setElement(I, k, Vint);// U[I][k]=Vint;
//        }
//        //condiçãao de Neumann para a tampa interna
//        for (i = 0; i <= I; i++) {
//            U.setElement(i, K, Vint);//  U[i][K]=Vint;
//        }
//        //Gera valores iniciais para o campo de potêncial baseado nos potencias das armaduras
//        //região A
//        for (i = 0; i <= I; i++) {
//            for (k = K + 1; k < Grid.Nz - 1; k++) {
//                U.setElement(i, k, 0.5 * (Vext + Vint));
//            }
//        }
//        //região B
//        for (i = I + 1; i < Grid.Nrho - 1; i++) {
//            for (k = 0; k < Grid.Nz - 1; k++) {
//                U.setElement(i, k, 0.5 * (Vext + Vint));
//            }
//        }
//        //região interior ao cilindro interno
//        for (i = 0; i < I; i++) {
//            for (k = 0; k < K; k++) {
//                U.setElement(i, k, Vint);
//            }
//        }
//    }
//
//    /**
//     * Calcula Residuo da Equação de Laplace no ponto (i,k) da malha
//     * @param i int - indice i do ponto
//     * @param k int - indice k do ponto
//     * @return double - residuo
//     */
//    public double CalculaResiduo(int i, int k) {
//
//        double gm = 1.0 + 0.5 / (double) i;
//        double gn = 1.0 - 0.5 / (double) i;
//        return U.getElement(i + 1, k) * gm + U.getElement(i - 1, k) * gn - 4.0 * U.getElement(i, k) + U.getElement(i, k + 1) + U.getElement(i, k - 1);
//    }
//
//    /**
//     * Método para a solução da matriz global
//     * @param w double - parâmetro de relaxação de acordo com o potêncial
//     */
//    public void SORLaplace(double w) {
//
//        int i = 0, k = 0;
//        double res = 0.0, resmax = 0.0;
//
//        do {
//            resmax = 0.;
//            // aplica condição de Dirichlet dU/dRho=0 para simetria axial (Eq. 12)
//            for (k = Grid.K + 1; k < Grid.Nz - 1; k++) {
//                U.setElement(0, k, U.getElement(1, k));
//            }
//            // aplica condição de Dirichlet para fronteira dU/dz=0 (Eq. 12)
//            for (i = Grid.I + 1; i < Grid.Nrho - 1; i++) {
//                U.setElement(i, 0, U.getElement(i, 1));
//            }
//            //Iteraçãao para Região A
//            for (i = 1; i <= Grid.I; i++) {
//                for (k = Grid.K + 1; k < Grid.Nz - 1; k++) {
//                    res = CalculaResiduo(i, k);
//                    U.setElement(i, k, U.getElement(i, k) + 0.25 * w * res);
//                    if (Math.abs(res) > resmax) {
//                        resmax = Math.abs(res);
//                    }
//                }
//            }
//            //Iteraçãao para Região B
//            for (i = Grid.I + 1; i < Grid.Nrho - 1; i++) {
//                for (k = 1; k < Grid.Nz - 1; k++) {
//                    res = CalculaResiduo(i, k);
//                    U.setElement(i, k, U.getElement(i, k) + 0.25 * w * res);
//                    if (Math.abs(res) > resmax) {
//                        resmax = Math.abs(res);
//                    }
//                }
//            }
//        } while (Math.abs(resmax) > Grid.prec);
//        System.out.println("Terminou...");
//        EscreveU();
//    }
//
//    public void CalcCampo(){
//        
//        
//        
//    }
//    /**
//     * Escreve Solução em Arquivo
//     */
//    private void EscreveU() {
//
//        File fp;
//        PrintWriter printer;
//        FileWriter wr = null;
//        int i, k;
//        fp = new File("U.dat");
//        if (fp.exists()) {
//            fp.delete();
//        }
//        try {
//            fp.createNewFile();
//        } catch (IOException ex) {
//            Logger.getLogger(Cilindro.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        try {
//            wr = new FileWriter(fp, true);
//        } catch (IOException ex) {
//            Logger.getLogger(Cilindro.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        printer = new PrintWriter(wr, true);
//        for (i = 0; i < Grid.Nrho; i++) {
//            for (k = 0; k < Grid.Nz; k++) {
//                printer.print(U.getElement(i, k));
//                printer.print("\t");
//            }
//            printer.print("\n");
//        }
//        printer.close();
//        try {
//            wr.close();
//        } catch (IOException ex) {
//            Logger.getLogger(Cilindro.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
}
