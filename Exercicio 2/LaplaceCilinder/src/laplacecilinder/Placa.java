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
public class Placa {

    /**
     * Raio Externo do Cilindro
     */
    private double X0;
    /**
     * Raio Interno do Cilindro
     */
    private double Y0;
    /**
     * Comprimento Externo
     */
    private double Xf;
    /**
     * Comprimento Interno
     */
    private double Yf;
    /**
     * Objeto para o tipo de dado Malha
     */
    public Malha Grid;
    /**
     * Matriz Global
     */
    private AbstractDoubleMatrix U;
    private double T1,
            T2,
            T3,
            T4;

    /**
     * Construtor Padrão
     */
    public Placa() {
        Grid = new Malha();
    }

    /**
     * Le parâmetros externos (potenciais)
     * @param V1
     * @param V2
     */
    public void LePotenciais(double T1, double T2, double T3, double T4) {
        this.T1 = T1;
        this.T2 = T2;
        this.T3 = T3;
        this.T4 = T4;
    }

    /**
     * Lê parâmetros geoméricos do cilindro
     * @param R1 double - raio interno
     * @param R2 double - raio externo (R2 > R1)
     * @param L1 double - comprimento interno
     * @param L2 double - comprimento externo
     */
    public void LeGeometria(double X0, double Y0, double Xf, double Yf) {
        this.X0 = X0;
        this.Y0 = Y0;
        this.Xf = Xf;
        this.Yf = Yf;
    }

    /**
     * Gera malha para o potêncial e aplica condiçõoes de fronteira fixas
     */
    public void GeraMalha() {

        int k, i;
        Grid.den = (Xf - X0) / (((double) Grid.Ngrid) - 1.0);
        //   System.out.println(Grid.den);
        //Densidade da malha calculo de inteiros aproximados para dimensionamento da malha
        Grid.Nx = Grid.Ngrid + 2;
        Grid.Ny = Grid.Ngrid + 2;
        //aloca memória para a matriz do campo de potencial na malha
        U = new DoubleMatrix(Grid.Nx, Grid.Ny);

        // Inicializa matriz com valor zero
        for (i = 0; i < Grid.Nx; i++) {
            for (k = 0; k < Grid.Ny; k++) {
                 //U.setElement(i, k, 0.0);
                U.setElement(i, k, (T1 + T2 + T3 + T4) / 4.0);
            }
        }

        //Imposição das condições de contorno de Dirichlet nas bordas da placa
        i = 0;
        for (k = 0; k < Grid.Ny; k++) {
            U.setElement(i, k, T1);
        }

        i = Grid.Nx - 1;
        for (k = 0; k < Grid.Ny; k++) {
            U.setElement(i, k, T3);
        }

        k = 0;
        for (i = 0; i < Grid.Nx; i++) {
            U.setElement(i, k, T2);
        }

        k = Grid.Ny - 1;
        for (i = 0; i < Grid.Nx; i++) {
            U.setElement(i, k, T4);
        }
    }

    /**
     * Calcula Residuo da Equação de Laplace no ponto (i,k) da malha
     * @param i int - indice i do ponto
     * @param k int - indice k do ponto
     * @return double - residuo
     */
    public double CalculaResiduo(int i, int k) {

        double hx = Grid.den * Grid.den;
        double hy = Grid.den * Grid.den;
        return (((((U.getElement(i + 1, k) + U.getElement(i - 1, k)) * hy) + ((U.getElement(i, k + 1) + U.getElement(i, k - 1)) * hx)) / (2.0 * (hx + hy))) - U.getElement(i, k));
    }

    /**
     * Método para a solução da matriz global
     * @param w double - parâmetro de relaxação de acordo com o potêncial
     */
    public void SORLaplace(double w) {

        int i = 0, k = 0;
        int iter = 1;
        double res = 0.0, resmax = 0.0;
        //EscreveU("0");
        do {
            resmax = res;
            //Iteraçãao para Região A
            for (i = 1; i < Grid.Nx - 1; i++) {
                for (k = 1; k < Grid.Ny - 1; k++) {
                    res = CalculaResiduo(i, k);
                    U.setElement(i, k, U.getElement(i, k) + w * res);
                    if (Math.abs(res) > resmax) {
                        resmax = Math.abs(res);
                    }
                }
            }

            //   System.err.println(U.getElement((int) (Grid.Nx / 2.0), (int) (Grid.Ny / 2.0)));
            //  Integer str = new Integer(iter);
            //   EscreveU2(str.toString());
            iter++;

        } while (Math.abs(resmax) > Grid.prec);
        System.out.print(iter);
        // convergiu();
        //   System.out.println("Terminou com " + iter + " iterações.");
          EscreveU("finalparacomparacao");
    }

    private void convergiu() {
        int i, k;
        for (i = 1; i < Grid.Nx - 1; i++) {
            for (k = 1; k < Grid.Ny - 1; k++) {

                if (Compare.diferente(U.getElement(i, k), 10.0)) {
                    System.err.println("Não Convergiu");
                }
            }
        }
    }

    /**
     * Escreve Solução em Arquivo
     */
    public void EscreveU(String str) {

        File fp;
        PrintWriter printer;
        FileWriter wr = null;
        int i, k;
        fp = new File("U_" + str + ".dat");
        if (fp.exists()) {
            fp.delete();
        }
        try {
            fp.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(Cilindro.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            wr = new FileWriter(fp, true);
        } catch (IOException ex) {
            Logger.getLogger(Cilindro.class.getName()).log(Level.SEVERE, null, ex);
        }
        printer = new PrintWriter(wr, true);
        for (i = 0; i < Grid.Nx; i++) {
            for (k = 0; k < Grid.Ny; k++) {
                printer.print(i);
                printer.print("\t");
                printer.print(k);
                printer.print("\t");
                printer.print(U.getElement(i, k));
                printer.print("\n");
            }
            // printer.print("\n");
        }
        printer.close();
        try {
            wr.close();
        } catch (IOException ex) {
            Logger.getLogger(Cilindro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Escreve Solução em Arquivo
     */
    private void EscreveU2(String str) {

        File fp;
        PrintWriter printer;
        FileWriter wr = null;
        int i, k;
        fp = new File("U_" + str + ".dat");
        if (fp.exists()) {
            fp.delete();
        }
        try {
            fp.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(Cilindro.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            wr = new FileWriter(fp, true);
        } catch (IOException ex) {
            Logger.getLogger(Cilindro.class.getName()).log(Level.SEVERE, null, ex);
        }
        printer = new PrintWriter(wr, true);
        for (i = 0; i < Grid.Nx; i++) {
            for (k = 0; k < Grid.Ny; k++) {
                printer.print(U.getElement(i, k));
                printer.print("\n");
            }
            // printer.print("\n");
        }
        printer.close();
        try {
            wr.close();
        } catch (IOException ex) {
            Logger.getLogger(Cilindro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Escreve Solução em Arquivo
     */
    private void EscreveU(String str, double val) {

        File fp;
        PrintWriter printer;
        FileWriter wr = null;
        int i, k;
        fp = new File("Uij_" + str + ".dat");
        if (fp.exists()) {
            fp.delete();
        }
        try {
            fp.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(Cilindro.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            wr = new FileWriter(fp, true);
        } catch (IOException ex) {
            Logger.getLogger(Cilindro.class.getName()).log(Level.SEVERE, null, ex);
        }
        printer = new PrintWriter(wr, true);
        for (i = 0; i < Grid.Nx; i++) {
            for (k = 0; k < Grid.Ny; k++) {
                printer.print(val);
                printer.print("\t");
            }
            printer.print("\n");
        }
        printer.close();
        try {
            wr.close();
        } catch (IOException ex) {
            Logger.getLogger(Cilindro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
