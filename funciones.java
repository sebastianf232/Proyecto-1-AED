import java.util.ArrayList;
import java.util.concurrent.locks.Condition;

/**
 * Universidad del Valle de Guatemala
 * Algoritmos y Estructuras de Datos
 * Proyecto 1
 * @author Juan Miguel Gonzalez-Campo 21077
 * @author Sebastian Franco 21484
 * @author Gabriela de Leon 21037
 * Clase Funciones
 */

public class funciones {

    /**
     * Implementa el operador > de lisp
     * 
     * @param a
     * @return 1 si es verdadero, 0 si es falso y 3 si es invalida la operacion
     */
    public int greater(ArrayList<String> a){ ///Mayor
        if (a.size() > 3){

            System.out.println("Operación invalida");
            return 3;
        }
        else{
            Double primero, segundo;
            primero = Double.parseDouble(a.get(1));
            segundo = Double.parseDouble(a.get(2));
            if (primero > segundo){
                return 1;
            }
            else {
                return 0;
            }
        }  
    }

    /**
     * Implementa < de lisp
     * @param a
     * @return 1 si es verdadero, 0 si es falso y 3 si es invalida la operacion
     */
    public int lesser(ArrayList<String> a){ ///Menor
        if (a.size() > 3){

            System.out.println("Operación invalida");
            return 3;
        }
        else{
            Double primero, segundo;
            primero = Double.parseDouble(a.get(1));
            segundo = Double.parseDouble(a.get(2));
            System.out.println(primero+ " < "+ segundo);
            if (primero < segundo){
                return 1;
            }
            else {
                return 0;
            }
        }  
    }

    /**
     * Implementa EQUAl o = de lisp
     * @param a
     * @return 1 si es verdadero, 0 si es falso y 3 si es invalida la operacion
     */
    public int equal(ArrayList<String> a){ ///Igual
        if (a.size() > 3){

            System.out.println("Operación invalida");
            return 3;
        }
        else{
            Double primero, segundo;
            primero = Double.parseDouble(a.get(1));
            segundo = Double.parseDouble(a.get(2));
            if (primero.equals(segundo)){
                return 1;
            }
            else {
                return 0;
            }
        }  
    }
}
