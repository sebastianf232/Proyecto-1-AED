
/**
 * Universidad del Valle de Guatemala
 * Algoritmos y Estructuras de Datos
 * Proyecto 1
 * @author Juan Miguel Gonzalez-Campo 21077
 * @author Sebastian Franco 21484
 * @author Gabriela de Leon 21037
 * Clase Main
 */



import java.util.*;


public class Main{
    static tokenFinder conv = new tokenFinder();
    static ArrayList<ArrayList<String>> lista = new ArrayList<>();
    static ArrayList<String> listaA = new ArrayList<>();
    static ArrayList<String> listatemp = new ArrayList<>();

    public static void main(String args[]) {
        listaA = conv.separate("(* (- x 32) (/ 5 9))");
        for (int i = 0; i < listaA.size(); i++) {
            System.out.println(listaA.get(i));
        }
        lista = conv.listaLisp(conv.separate("(* (- x 32) (/ 5 9))"));
        System.out.println("Listas");
        
        
        for (int i = 0; i < lista.size(); i++) {
            listatemp = lista.get(i);
            System.out.println("Lista "+i);
            for (int a = 0; a < listatemp.size(); a++){
                System.out.println(listatemp.get(a) + " ");
            }
        }
        
        
    }
}
