package montecarlo;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * Classe Responsavel pela Criação e Gravação de dados em arquivo
 * @author Sperotto
 */
public class PrintResultFile extends PrintFile {

    /**
     *
     * @param Projeto 
     * @param num
     */
    public void initFile(String DirName) {
        try {
            Arquivo = new File(DirName);

            if (Arquivo.exists()) {
                Arquivo.delete();
            }
            Arquivo.createNewFile();

            wr = new FileWriter(Arquivo, true);
            printer = new PrintWriter(wr, true);  
        } catch (Exception e) {
            System.out.println(e);
        }
    }



    /**
     * Método responsavel por escrever os dados no arquivo
     * @param val
     * @param val1 
     * @param val3
     * @param val2
     * @param val4
     * @param val5  
     */
    public final void escreveFile(int val, int val2) {
        try {
            printer.print(val);
            printer.print("   ");
            printer.print(val2);
            printer.print("\n");
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public final void escreveFile(double val) {
        try {
            printer.print(val);
            printer.print("\n");
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    /**
     * Método que fecha o arquivo e finaliza gravação
     */
    public final void endFile() {
        try {
            printer.close();
            wr.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
