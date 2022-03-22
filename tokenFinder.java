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
     * @param str
     * @return chars
     */
    public ArrayList<String> separate(String str){  ///Metodo que separa cada caracter en el ArrayList
        ArrayList<String> chars = new ArrayList<>();
        if (!str.contains("(")){
            System.out.println("\n Expresión no valida \n");
            return null;
        }
        str = str.replace("(", " (");

        String[] temp = str.split("\\s+");
        
        
        for (int i = 0; i<temp.length; i++){
                chars.add(temp[i]);
        }
        for (int j = 0; j<chars.size(); j++){
            if (chars.get(j).contains("\\s+")){
                chars.remove(j);
                continue;
            } else{
                continue;
            }
        }
  
        return chars;
    }

    

    /**
     * @param l
     * @return listaLisp
     */
    public ArrayList<ArrayList<String>> listaLisp(String s){ ///Metodo que permite aniadir los  caracteres en el ArrayList
        
        ArrayList<String> listaIn = new ArrayList<>();
        if (separate(s) == null){
            return null;
        }
        listaIn = separate(s);

        
        ArrayList<ArrayList<String>> listaFinal = new ArrayList<>();
        Stack<String> stack = new Stack<>();

        

        for (int i =listaIn.size()-1; i>-1; i--){
            ArrayList<String> lista = new ArrayList<>();
            String c = listaIn.get(i);

            if (c.contains("(") && c.contains(")")){
                if (c.length()>1){
                    c = ElParentesis(c);
                    c = ElParentesis1(c);
                    stack.add(c);
                    while (!stack.empty()){
                        lista.add(stack.remove());
                    }
                
                    listaFinal.add(lista);
                    continue;
                    }
                else {
                    while (!stack.empty()){
                        lista.add(stack.remove());
                    }
                
                    listaFinal.add(lista);
                    continue;
                }
            }
            if (c.contains(")")){
                if (c.length()>1){
                    stack.add(ElParentesis1(c));
                    continue;
                    }
                else {
                    continue;
                }
                
            }
            if (c.contains("(")) {
                if (c.length()>1){
                    stack.add(ElParentesis(c));
                    while (!stack.empty()){
                        lista.add(stack.remove());
                    }
                
                    listaFinal.add(lista);
                    continue;
                    }
                else {
                    while (!stack.empty()){
                        lista.add(stack.remove());
                    }
                
                    listaFinal.add(lista);
                    continue;
                }
            } 
            if (c.contains("\\d+")){
                stack.add(c);
                continue;
            }
            if (c.contains("+/-*")){
                stack.add(c);
                continue;
            } else {
                stack.add(c);
                continue;
            }
        }
        return listaFinal;
    }
    public String ElParentesis(String str){///quita el parentesis inicial
        String b = str.replace("(", "");

        return b;

    }
    public String ElParentesis1(String str){ ///quita el parentesis final
        String b = str.replace(")", "");

        return b;

    }
}
