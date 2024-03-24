package montecarlo;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * Classe Responsavel pela Criação e Gravação de dados em arquivo
 * @author Sperotto
 */
public abstract class PrintFile {

    /**
     *
     */
    protected File Arquivo;
    /**
     *
     */
    protected PrintWriter printer;
    /**
     *
     */
    protected FileWriter wr;

    public PrintFile() {
    }

    public void initFile(String DirName) {
    }

    /**
     *
     * @param val0 
     * @param val1 
     * @param val2  
     */
    public void escreveFile(double val0, double val1, double val2) {
    }

    /**
     * 
     * @param val
     */
    public void escreveFile(int val) {
    }

    /**
     * 
     * @param val
     * @param val2
     */
    public void escreveFile(int val, int val2) {
    }

    /**
     * 
     * @param val
     */
    public void escreveFile(double val) {
    }

    //public abstract void escreveFile(double val0, double val1, double val2, double val3, double val4);
    /**
     *
     */
    public abstract void endFile();
}
