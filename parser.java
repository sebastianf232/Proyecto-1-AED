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
    public void parseFull(ArrayList<ArrayList<String>> a, Map<String, ArrayList<ArrayList<String>>> p, Map<String, String> par, Map<String, String> var){
        tokenFinder tf = new tokenFinder();
        funciones fn = new funciones();
        int i = a.size()-1;
        boolean on = true;
        boolean replaced = false;
        while(on){
            ArrayList<String> s = new ArrayList<>();
            s = a.get(i);
            for (int u = 0; u<s.size(); u++){
                if (var.containsKey(s.get(u))){
                    replaceparam(var.get(s.get(u)), s.get(u), a);
                    replaced = true;
                }
                else {
                    continue;
                }
            }
            if (replaced == true){
                parseFull(a, p, par, var);
                break;
            }
            if (p.containsKey(s.get(0))){ ///Evalua si se ingreso alguna funcion ya definida
                ArrayList<ArrayList<String>> evalfun = p.get(s.get(0));
                String evalparam = s.get(1);
                System.out.print("replace: "+ evalparam);

                String param = par.get(s.get(0));
                System.out.println("Param: "+param);

                
                ArrayList<ArrayList<String>> evalinst = new ArrayList<>();
                

                evalinst = replaceparam(evalparam, param, evalfun);
                
                
                par.replace(s.get(0), param, evalparam);
                parseFull(evalinst, p, par, var);
                break;
            }
            if (s.contains("DEFUN") || s.contains("defun")){ //DEFUN
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
            
            if (s.contains("ATOM") || s.contains("atom")){ //ATOM
                if (s.size()==2 && a.size() == 1){
                    System.out.println("TRUE"); // devuelve TRUE al no ser una lista
                    break;
                }
                
                if (s.size()>2){
                    System.out.println("FALSE"); // devuelve FALSE al ser una lista
                    break;
                }   
                if (s.size() == 1){
                    if (a.size() > 1){
                        System.out.println("FALSE"); // devuelve FALSE al ser una lista
                        break;
                    }
                }
            }
            if (s.contains("LIST") || s.contains("list")){ //LIST
                if (s.size()==2){
                    System.out.println("FALSE"); //Devuelve FALSE al no ser una lista
                }
                if (s.size()>2){
                    System.out.println("TRUE"); // // devuelve TRUE al ser una lista
                }
            }
            if (s.contains("QUOTE") || s.contains("'")){ //QUOTE
                String j ="";
                for (int q = 1; q < s.size(); q++){ ///Se devuelve lo que se escribió
                    j+=s.get(q);
                    j+=" ";
                }
                System.out.println(j);
                on = ifZero(i);
                i--;
                continue;    
            }

            if (s.contains(">")){
                int k = fn.greater(s);
                if (k == 1){
                    System.out.println("TRUE");
                }
                if (k == 0){
                    System.out.println("FALSE");
                }
                on = ifZero(i);
                i--;
                continue;
                

            }
            if (s.contains("<")){
                int k = fn.lesser(s);
                if (k == 1){
                    System.out.println("TRUE");
                }
                if (k == 0){
                    System.out.println("FALSE");
                }
                on = ifZero(i);
                i--;
                continue;
                
            }
            if (s.contains("COND") || s.contains("cond")){ ///COND
                for (int f = i; f >= 0; f--){
                    if (a.get(f).contains("t")){
                        on = ifZero(f);
                        f--;
                        System.out.println("Respuesta: "+calc.evaluatePrefix(a.get(f)));
                        
                        break;
                    }
                    else {
                        ArrayList<String> condic = new ArrayList<>();
                        condic = a.get(f);
                        
                        if (parse(condic) == 1){
                            on = ifZero(f);
                            f--;
                            System.out.println("Respuesta: "+calc.evaluatePrefix(a.get(f)));
                            
                            break;
                        }
                        if (parse(condic) == 3){
                            System.out.println("Condicion invalida");
                            break;
                        }
                        else{
                            if (f == i){
                                on = ifZero(f);
                                f--;
                                continue;
                            } else {
                                System.out.println("No se cumple la condición");
                                on = ifZero(f);
                                f--;
                                continue;
                            }
                        }
                    }
                }
                break;



                
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
            if (s.contains("setq") || s.contains("SETQ")){
                String varName;
                String varValue;
                if (s.size() > 2){
                    for (int q = 1; q < s.size(); q++){
                        varName = s.get(q);
                        q++;
                        varValue = s.get(q);
                        var.put(varName, varValue);
                        System.out.println("Variable -"+varName+"- valor: "+varValue);
                    }
                    on = ifZero(i);
                    i--;
                    continue;
                } else {
                    System.out.println("Error de asignacion");
                }
                
            }

            else {
                System.out.println("Respuesta: "+calc.calculate(a));
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
