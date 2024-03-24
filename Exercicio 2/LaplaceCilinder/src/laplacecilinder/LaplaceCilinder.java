/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package laplacecilinder;

/**
 *
 * @author lev_alunos
 */
public class LaplaceCilinder {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Placa ex;
        ex = new Placa();
        ex.LeGeometria(0.0, 0.0, 8.0, 8.0);
        ex.LePotenciais(75.0, 100.0, 50.0, 50.0);
        ex.Grid.LeParametrosDaMalha(9, 1.E-10);

      //  System.out.println("Parâmetro ideal: " + (2.0 / (1 + Math.sin(Math.PI / (ex.Grid.Ngrid + 2.0)))));
        System.out.println("Parâmetro ideal: " + (2.0 / (1 + Math.sin(Math.PI / (ex.Grid.Ngrid)))));
       //     for (double i = 1.0; i < 2.0; i += 0.001) {
        ex.GeraMalha();
       //   System.out.print(i + "\t");
           ex.SORLaplace((2.0 / (1 + Math.sin(Math.PI / (ex.Grid.Ngrid)))));
        //    System.out.println(2.0 / (1 + Math.sin(Math.PI / ex.Grid.Ngrid)));
      //  ex.SORLaplace((2.0 / (1 + Math.sin(Math.PI / (ex.Grid.Ngrid )))));
      //  System.out.print("\n");
        //  }
    }
}
