import java.util.*;

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
    public List<Character> separate(String str){ ///Metodo que separa cada caracter en el ArrayList
        List<Character> chars = new ArrayList<>();
  
        for (char ch : str.toCharArray()) { 
  
            chars.add(ch);
        }
  
        return chars;
    }
    
    /**
     * @param l
     * @return listaLisp
     */
    public List<Character> listaLisp(ArrayList l){ ///Metodo que permite aniade los  caracteres en el ArrayList
        ArrayList<Character> listaIn = new ArrayList<>();
        listaIn = l;
        ArrayList<Character> lista = new ArrayList<>();
        ArrayList<ArrayList<Character>> listaFinal = new ArrayList<>();
        

        for (int i =l.size()-1; i>-1; i--){
            char c = listaIn.get(i);

            if (Character.isLetterOrDigit(c)){
                lista.add(c);
            if (c == ')'){
                lista.add(c);
            }
            if (c == '('){
                lista.add(c);
                listaFinal.add(lista);
                lista.clear();
            }
            else{
                while (!lista.isEmpty() && prec(c) <= prec(lista.get(lista.size()-1))){
                    lista.add(c);
                }

                }
            }

        }

    }
}
