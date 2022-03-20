
/**
 * Universidad del Valle de Guatemala
 * Algoritmos y Estructuras de Datos
 * Proyecto 1
 * @author Juan Miguel Gonzalez-Campo 21077
 * @author Sebastian Franco 21484
 * @author Gabriela de Leon 21037
 * Clase Calculator
 */

import java.util.ArrayList;


import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

import javax.lang.model.util.ElementScanner6;

public class calculator {
    public static final Pattern DOUBLE = Pattern.compile("\\d");

    public Boolean isOperand(String c) {
        if (DOUBLE.matcher(c).find()) {
            return true;
        } else{
            return false;
        }
    }
 
    public double evaluatePrefix(ArrayList<String> a)
    {
        Stack<Double> Stack = new Stack<Double>();
        try {
            for (int j = a.size() - 1; j >= 0; j--) {
 
        
                if (isOperand(a.get(j)))
                    Stack.add(Double.parseDouble(a.get(j)));
     
                else {
     
                    
                    double o1 = Stack.peek();
                    Stack.remove();
                    double o2 = Stack.peek();
                    Stack.remove();
     
                    if (a.get(j).equals("+")){
                        Stack.add(o1 + o2);
                        continue;
                    }
                    if (a.get(j).equals("-")){
                        Stack.add(o1 - o2);
                        continue;
                    }
                    if (a.get(j).equals("*")){
                        Stack.add(o1 * o2);
                        continue;
                    }
                    if (a.get(j).equals("/")){
                        Stack.add(o1 / o2);
                        continue;
                    }
                    else {
                        System.out.println("Caracter invalido en expresion");
                        return 0;
                        
                    }
                }
            }
     
            return Stack.peek();

        } catch (Exception e){
            System.out.println("Error de evaluacion");
            return 0;
        }
    }        
 

}
 

