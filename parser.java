import java.lang.annotation.Retention;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Map;

import org.junit.runners.parameterized.ParametersRunnerFactory;

/**
 * Universidad del Valle de Guatemala
 * Algoritmos y Estructuras de Datos
 * Proyecto 1
 * @author Juan Miguel Gonzalez-Campo 21077
 * @author Sebastian Franco 21484
 * @author Gabriela de Leon 21037
 * Clase Parser
 */

public class parser {
    tokenFinder tf = new tokenFinder();
    funciones fn = new funciones();
    calculator calc = new calculator();

    /**
     * @param a
     * @param p
     * @param par
     * Permite parsear segun lo que se indica.
     */
    public void parseFull(ArrayList<ArrayList<String>> a, Map<String, ArrayList<ArrayList<String>>> p, Map<String, String> par){
        tokenFinder tf = new tokenFinder();
        funciones fn = new funciones();
        int i = a.size()-1;
        boolean on = true;
        while(on){
            ArrayList<String> s = new ArrayList<>();
            s = a.get(i);
            if (p.containsKey(s.get(0))){ ///Evalua si se ingreso alguna operacion
                ArrayList<ArrayList<String>> evalfun = p.get(s.get(0));
                String evalparam = s.get(1);
                System.out.print("replace: "+ evalparam);

                String param = par.get(s.get(0));
                System.out.println("Param: "+param);

                
                ArrayList<ArrayList<String>> evalinst = new ArrayList<>();
                

                evalinst = replaceparam(evalparam, param, evalfun);
                
                
                par.replace(s.get(0), param, evalparam);
                parseFull(evalinst, p, par);
                break;
            }
            if (s.contains("DEFUN")){ //DEFUN
                if (p.containsKey(s.get(1))){
                    System.out.println("no puede redefinir funcion");
                    break;
                }
                String name = s.get(1);
                on = ifZero(i);
                i--;
                ArrayList<String> param = a.get(i);

                String finalParam = param.get(0);
                ArrayList<ArrayList<String>> functemp = new ArrayList<>();

                
                while (i > 0){
                    
                    on = ifZero(i);
                    i--;
                    functemp.add(a.get(i));
                    
                }
                ArrayList<ArrayList<String>> func = new ArrayList<>();
                for (int r = functemp.size()-1; r >= 0; r--){
                    func.add(functemp.get(r));
                }
                
                p.put(name, func);
                par.put(name, finalParam);
                System.out.println("Función -"+name+"- aniadida. Param: "+ finalParam);
                
                break;



            }
            if (s.contains("ATOM")){ //ATOM
                
            }
            if (s.contains("LIST")){ //LIST
                
            }
            if (s.contains("QUOTE") || s.contains("'")){ //QUOTE
                
            }
            if (s.contains(">")){
                int k = fn.greater(s);
                if (k == 1){
                    System.out.println("TRUE");
                }
                if (k == 0){
                    System.out.println("FALSE");
                }
                
                break;
                

            }
            if (s.contains("<")){
                int k = fn.lesser(s);
                if (k == 1){
                    System.out.println("TRUE");
                }
                if (k == 0){
                    System.out.println("FALSE");
                }
                break;
                
            }
            if (s.contains("COND")){ ///COND
                if(a.get(i-1).size() < 2){
                    on = ifZero(i);
                    i--;
                    ArrayList<String> condic = new ArrayList<>();
                    condic = a.get(i-1);
                    on = ifZero(i);
                    i--;
                    if (parse(condic) == 1){
                        on = ifZero(i);
                        i--;
                        System.out.println("Respuesta: "+calc.evaluatePrefix(a.get(i)));
                        
                        break;
                    }
                    else{
                        System.out.println("No se cumple la condución");
                        break;
                    }
                } else {
                    ifZero(i);
                    i--;
                    ArrayList<String> condic = new ArrayList<>();
                    condic = a.get(i);
                    ifZero(i);
                    i--;
                    if (parse(condic) == 1){
                        
                        System.out.println(calc.evaluatePrefix(a.get(i)));
                        break;
                    }
                    else{
                        break;
                    }
                }
            }
            if (s.contains("EQUAL")){ ///EQUAL
                int k = fn.equal(s);
                if (k == 1){
                    System.out.println("TRUE");
                }
                if (k == 0){
                    System.out.println("FALSE");
                }
                break;
            }
            else {
                System.out.println("Respuesta: "+calc.evaluatePrefix(s));
                break;
            }
        }
    }

    /**
     * @param s
     * @return 0
     */
    public int parse(ArrayList<String> s){ ///Permite que el programa no creashee
        if (s.contains("DEFUN")){
            return 0;
        }
        if (s.contains("ATOM")){
            return 0;
        }
        if (s.contains("LIST")){
            return 0;
        }
        if (s.contains("QUOTE") || s.contains("'")){
            return 0;
        }
        if (s.contains(">")){
            
            
            int a = fn.greater(s);
            return a;



        }
        if (s.contains("<")){

            int a = fn.lesser(s);
            return a;
        }
        if (s.contains("COND")){
            return 0;
        }
        if (s.contains("EQUAL")){
            int a = fn.equal(s);
            return a;
        }
        else {
            return 0;
        }
    }

    /**
     * @param i
     * @return boolean
     */
    public Boolean ifZero(int i){ ///Indica que la expresion no es valida
        if (i == 0){
            System.out.println("Expresión invalida");
            return false;
        }
        return true;
        
    }

    /**
     * 
     * @param replace
     * @param param
     * @param eval
     * @return
     * Permite reemplazar el parametro
     */
    public ArrayList<ArrayList<String>> replaceparam(String replace, String param, ArrayList<ArrayList<String>> eval){
        ArrayList<ArrayList<String>> evalfun = eval;
        for (int q = 0; q < evalfun.size(); q++){
            if (evalfun.get(q).contains(param)){
                
                for (int w = 0; w<evalfun.get(q).size()-1; w++){
                    if (evalfun.get(q).get(w).equals(param)){
                        evalfun.get(q).set(w, replace);
                    }
                }
            }

        }
        
        return evalfun;
    }
    
}
