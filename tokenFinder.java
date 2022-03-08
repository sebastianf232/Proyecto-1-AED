import java.util.*;
import java.lang.StringBuilder;

import javax.naming.spi.DirStateFactory.Result;

/**
 * Universidad del Valle de Guatemala
 * Algoritmos y Estructuras de Datos
 * Proyecto 1
 * @author Juan Miguel Gonzalez-Campo 21077
 * @author Sebastian Franco 21484
 * @author Gabriela de Leon 21037
 * Clase TokenFinder . java
 */

public class tokenFinder {
    /**
     * @param ch
     * @return
     */
    public int prec(char ch){ ///
        switch(ch){
            case '+': ///suma
            case '-': ///resta
                return 1; ///Estan en el mismo orden
            case '*': ///Multiplicacion
            case '/': ///Division
                return 2; ///* & / estan en el mismo orden
            case '^': ///Exponente
                return 3; 
        } return -1;
    }


    /**
     * @param str
     * @return chars
     */
    public ArrayList<String> separate(String str){  ///Metodo que separa cada caracter en el ArrayList
        ArrayList<String> chars = new ArrayList<>();
        ArrayList<Character> espacios = new ArrayList<>();
        
        String[] temp = str.split("\\s+");
        
        for (int i = 0; i<temp.length; i++){
            chars.add(temp[i]);
        }
  
        return chars;
    }

    

    /**
     * @param l
     * @return listaLisp
     */
    public ArrayList<ArrayList<String>> listaLisp(ArrayList l){ ///Metodo que permite aniade los  caracteres en el ArrayList
        ArrayList<String> listaIn = new ArrayList<>();

        listaIn = l;
        ArrayList<ArrayList<String>> listaFinal = new ArrayList<>();
        Stack<String> stack = new Stack<>();
        

        for (int i =listaIn.size()-1; i>-1; i--){
            ArrayList<String> lista = new ArrayList<>();
            String c = listaIn.get(i);

            if (c.contains("\\d+")){
                stack.add(c);
                continue;
            }
            if (c.contains(")")){
                stack.add(ElParentesis1(c));
                continue;
            }
            if (c.contains("(")) {
            
                stack.add(ElParentesis(c));
                while (!stack.empty()){
                    lista.add(stack.remove());
                }
                
                listaFinal.add(lista);
                continue;
            } if (c.contains("+/-*")){
                stack.add(c);
                continue;
            } else {
                stack.add(c);
                continue;
            }
        }
        return listaFinal;
    }
    public String ElParentesis(String str){
        String b = str.replace("(", "");

        return b;

    }
    public String ElParentesis1(String str){
        String b = str.replace(")", "");

        return b;

    }
}
