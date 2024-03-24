package laplacecilinder;

/**
 *
 * @author Sperotto
 */
public class Malha {

    /**
     * numero de pontos ao longo de rho
     */
    public int Ngrid;
    /**
     *  tolerância de cálculo (determina saída do código)
     */
    public double prec;
    /**
     * numero de pontos em rho 
     */
    public int Nx;
    /**
     * numero de pontos em z
     */
    public int Ny;
    /**
     * densidade radial e vertical da malha
     */
    public double den;
//    /**
//     * Número  I (ver eqs. 10)
//     */
//    public int I;
//    /**
//     * Número K  (ver eqs. 10)
//     */
//    public int K;

    /**
     * Le parâmetros externos da malha 
     * @param NN int - numero de pontos na malha
     * @param precisao double - precisão esperada para o cálculo da solução
     */
    public void LeParametrosDaMalha(int NN, double precisao) {
        Ngrid = NN;
        prec = precisao;
    }
}
