
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
import java.text.BreakIterator;
import java.util.*;
import java.util.regex.Pattern;

import javax.lang.model.util.ElementScanner6;

public class calculator {
    public static final Pattern DOUBLE = Pattern.compile("\\d");

    /**
     * @param c
     * @return boolean
     */
    public Boolean isOperand(String c) {
        if (DOUBLE.matcher(c).find()) {
            return true;
        } else{
            return false;
        }
    }
 
    /**
     * @param a
     * @return continue, 0
     */
    public Double evaluatePrefix(ArrayList<String> a)
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
                        return 0.0;
                        
                    }
                }
            }
     
            return Stack.peek();

        } catch (Exception e){
            System.out.println("Error de evaluacion");
            return 0.0;
        }
    }
    public Double calculate(ArrayList<ArrayList<String>> a){
        int i = 0;
        Stack<String> temp = new Stack<>();
        ArrayList<String> temp1 = new ArrayList<>();
        boolean on = true;
        String res = "0.0";
        Double res1 = 0.0;
        if (a.size() >= 1){
            while (on){
                ArrayList<String> s = a.get(i);
                if ((s.contains("+") || s.contains("-") || s.contains("*") ||s.contains("/")) && (s.size() > 1) ){
                    res = evaluatePrefix(s).toString();
                    System.out.println("res : "+res);
                    if (ifZero(i, a)){
                        temp.add(res);
                        
                        i++;
                        continue;
                    } else {
                        res1 = Double.parseDouble(res);
                        break;
                    }
                    
                }
                if ((s.contains("+") || s.contains("-") || s.contains("*") ||s.contains("/")) && (s.size() == 1)){
                    temp1.add(s.get(0));
                    temp1.add(temp.remove());
                    temp1.add(temp.remove());
                    res = evaluatePrefix(temp1).toString();
                    System.out.println("res : "+res);
                    if (ifZero(i, a)){
                        temp.add(res);

                        i++;
                        continue;
                    } else {
                        res1 = Double.parseDouble(res);
                        break;
                        
                    }
                } if (s.size() == 1) {
                    temp.add(s.get(0));
                    if (ifZero(i, a)){
                        i++;
                        continue;
                    } else {
                        System.out.println("Invalida");
                        res1 = Double.parseDouble(res);
                        break;
                        
                    }
                } else {
                    System.out.println("Expresion no valida");
                    res1 = Double.parseDouble(res);
                    break;
                }
            }
            return res1;
        }
        else {
            System.out.println("Operacion no valida");
            return 0.0;
        }           
    
    }      
    public boolean ifZero(int i, ArrayList a){
        if (i == a.size()-1){
            return false;

        }
        else {
            return true;
        }
    }
}
 

