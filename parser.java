import java.lang.annotation.Retention;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Flow.Subscriber;

import org.junit.experimental.theories.ParameterSignature;
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
    public void parseFull(ArrayList<ArrayList<String>> a, Map<String, ArrayList<ArrayList<String>>> p, Map<String, ArrayList<String>> par, Map<String, ArrayList<String>> var){
        tokenFinder tf = new tokenFinder();
        funciones fn = new funciones();
        int i = a.size()-1;
        boolean on = true;
        boolean replaced = false;
        while(on){
            ArrayList<String> s = new ArrayList<>();
            s = a.get(i); 

            // Primero busca en la lista S si se encuentra una variable declarada anteriormente
            for (int u = 0; u<s.size(); u++){
                if (var.containsKey(s.get(u))){
                    ArrayList<String> varRep = new ArrayList<>();
                    varRep.add(s.get(u));
                    replaceparam(var.get(s.get(u)), varRep, a);
                    System.out.print(a);
                    replaced = true;
                }
                else {
                    continue;
                }
            }
            // Si encontro una variable en el loop anterior y la reemplazo, parsea de nuevo con la operacion cambiada
            if (replaced == true){
                parseFull(a, p, par, var);
                break;
            }
            if (p.containsKey(s.get(0))){ ///Evalua si se ingreso alguna funcion ya definida
                ArrayList<ArrayList<String>> evalfun = new ArrayList<>();

                Iterator<ArrayList<String>> iterator = p.get(s.get(0)).iterator(); //permite crear un clon de la lista sin cambiar la original
                
                while(iterator.hasNext()){
                    evalfun.add((ArrayList<String>) iterator.next().clone());
                }
                
                ArrayList<String> evalparam = new ArrayList<>();
                for (int r = 1; r<s.size(); r++){
                    evalparam.add(s.get(r));
                }
                
                

                ArrayList<String> param = par.get(s.get(0));
                
                if (evalparam.size() != param.size()){ //Evalua si la cantidad de parametros es correcta
                    System.out.println("Cantidad de parametros equivocada");
                    break;
                    
                }
                
                ArrayList<ArrayList<String>> evalinst = new ArrayList<>();
                
                //Reemplaza los parametros
                evalinst = replaceparam(evalparam, param, evalfun);
                
                
                //Realiza el parse con los parametros cambiados
                parseFull(evalinst, p, par, var);
                
                break;
            }
            if (s.contains("DEFUN") || s.contains("defun")){ //DEFUN permite definir funciones y guardarlas
                if (p.containsKey(s.get(1))){
                    System.out.println("no puede redefinir una funcion");
                    break;
                }
                String name = s.get(1);
                on = ifZero(i);
                i--;
                ArrayList<String> param = a.get(i);

                
                ArrayList<ArrayList<String>> functemp = new ArrayList<>(); 

                
                while (i > 0){
                    
                    on = ifZero(i);
                    i--;
                    functemp.add(a.get(i));
                    
                }
                ArrayList<ArrayList<String>> func = new ArrayList<>();
                for (int r = functemp.size()-1; r >= 0; r--){
                    func.add(functemp.get(r)); //lo que ejecuta la funcion sin parametros y sin nombre
                } 
                
                p.put(name, func); //aniade el nombre de la funcion como key y lo que ejecuta como value
                par.put(name, param); //aniade el nombre de la funcion como key y sus parametros como value
                System.out.println("Función -"+name+"- aniadida. Param: "+ param);
                
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
                if (s.size()==2 && a.size() == 1){
                    System.out.println("False"); // devuelve FALSE al no ser una lista
                    break;
                }
                
                if (s.size()>2){
                    System.out.println("TRUE"); // devuelve TRUE al ser una lista
                    break;
                }   
                if (s.size() == 1){
                    if (a.size() > 1){
                        System.out.println("TRUE"); // devuelve TRUE al ser una lista
                        break;
                    }
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

            if (s.contains(">")){ //llama a funcion greater
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
            if (s.contains("<")){ //llama funcion lesser
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
                    if (a.get(f).contains("t")){ //Si encuentra "t" evalua por default
                        on = ifZero(f);
                        f--;
                        System.out.println("Respuesta: "+calc.evaluatePrefix(a.get(f)));
                        
                        break;
                    }
                    else {
                        ArrayList<String> condic = new ArrayList<>();
                        condic = a.get(f);
                        
                        if (parse(condic) == 1){ //Si la condicion encontrada resulta 1 (verdadera) entonces se opera su par.
                            on = ifZero(f);
                            f--;
                            System.out.println("Respuesta: "+calc.evaluatePrefix(a.get(f)));
                            
                            break;
                        }
                        if (parse(condic) == 3){ //si una condicion no es valida se termina la ejecucion
                            System.out.println("Condicion invalida");
                            break;
                        }
                        else{
                            if (f == i){
                                on = ifZero(f); 
                                f--;
                                continue;
                            } else { //Avisa si no se cumple la condicion y continua el loop
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
            if (s.contains("EQUAL") || s.contains("=")){ ///EQUAL implementa el metodo equal
                int k = fn.equal(s);
                if (k == 1){
                    System.out.println("TRUE");
                }
                if (k == 0){
                    System.out.println("FALSE");
                }
                break;
            }
            if (s.contains("setq") || s.contains("SETQ")){ //Permite declarar variables para ser usadas luego
                String varName;
                ArrayList<String> varValue = new ArrayList<>();
                if (s.size() > 2){
                    for (int q = 1; q < s.size(); q++){
                        varName = s.get(q);
                        q++;
                        varValue.add(s.get(q));
                        var.put(varName, varValue); //añade la variable y su valor (solo valores atoms, no listas) al mapa 
                        System.out.println("Variable -"+varName+"- valor: "+varValue);
                    }
                    on = ifZero(i);
                    i--;
                    continue;
                } else {
                    System.out.println("Error de asignacion");
                    break;
                }
                
            }

            else { //SI no se encuentra ninguno de los casos anteriores se intenta operar la funcion
                System.out.println("Respuesta: "+calc.calculate(a));
                break;
            }
        }
    }

    /**
     * @param s
     * @return 0
     */
    public int parse(ArrayList<String> s){ ///Permite realizar la funcion COND
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
        if (s.contains("EQUAL") || s.contains("=")){
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
     * Permite reemplazar el parametro y las variables
     */
    public ArrayList<ArrayList<String>> replaceparam(ArrayList<String> replace, ArrayList<String> param, ArrayList<ArrayList<String>> eval){
        ArrayList<ArrayList<String>> evalfun = eval;
        
        for (int r = 0; r<param.size(); r++){
            for (int q = 0; q < evalfun.size(); q++){
                if (evalfun.get(q).contains(param.get(r))){
                    for (int w = 0; w<evalfun.get(q).size(); w++){
                        if (evalfun.get(q).get(w).equals(param.get(r))){
                            evalfun.get(q).set(w, replace.get(r));
                            continue;
                        } else {
                            continue;
                        }
                    }
                } else {
                    continue;
                }
                
    
            }
        }
        
       
        
        return evalfun;
    }
    
}
