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
    static tokenFinder tokenFinder = new tokenFinder();
    static ArrayList<ArrayList<String>> lista = new ArrayList<>();
    static ArrayList<String> listaA = new ArrayList<>();
    static ArrayList<String> listatemp = new ArrayList<>();

    public static void main(String args[]) {
        listaA =tokenFinder.separate("((+ (fibonacci (- n 1))(fibonacci (- n 2))))))");
        for (int j = 0; j < listaA.size(); j++){
            System.out.println(listaA.get(j));
        }

        String str = "((+ (fibonacci (- n 1))(fibonacci (- n 2))))))";
        lista = tokenFinder.listaLisp(str);
        System.out.println("Listas");
        
        
        for (int i = 0; i < lista.size(); i++) {
            listatemp = lista.get(i);
            System.out.println("Lista "+(i+1));
            for (int a = 0; a < listatemp.size(); a++){
                System.out.println(listatemp.get(a) + " ");
            }
        }
        
        
    }
}
