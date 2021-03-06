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
    static Map<String, ArrayList<ArrayList<String>>> defuns = new HashMap<>(); //guarda funciones
    static Map<String, ArrayList<String>> funcparams = new HashMap<>(); // guarda funciones y su parametro
    static Map<String, ArrayList<String>> vars = new HashMap<>(); // guarda variables 
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        funciones fn = new funciones();
        parser ps = new parser();
        boolean on = true;
        
        while (on){
            
            System.out.println("Ingrese la expresion LISP  o ingrese END para salir");
            String oper = scan.nextLine();
            if (oper.equalsIgnoreCase("END")){
                break;
            }
            if (tokenFinder.listaLisp(oper) == null){
                continue;
            }
            lista = tokenFinder.listaLisp(oper);
            
            
            System.out.println("\n");
            ps.parseFull(lista, defuns, funcparams, vars);
            System.out.println("\n");
        }
    }
}
