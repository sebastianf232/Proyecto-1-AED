import java.util.*;

import javax.naming.spi.DirStateFactory.Result;

public class tokenFinder {
    public int prec(char ch){
        switch(ch){
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
        } return -1;
    }

    public List<Character> separate(String str){
        List<Character> chars = new ArrayList<>();
  
        for (char ch : str.toCharArray()) {
  
            chars.add(ch);
        }
  
        return chars;
    }
    
    public List<Character> listaLisp(ArrayList l){
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
