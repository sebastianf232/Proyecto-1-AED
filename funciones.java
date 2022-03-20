import java.util.ArrayList;
import java.util.concurrent.locks.Condition;

public class funciones {


    public void defun(){
        
    }
    public void COND(ArrayList<String> a){
        
    }
    public int greater(ArrayList<String> a){
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
    public int lesser(ArrayList<String> a){
        if (a.size() > 3){

            System.out.println("Operación invalida");
            return 3;
        }
        else{
            Double primero, segundo;
            primero = Double.parseDouble(a.get(1));
            segundo = Double.parseDouble(a.get(2));
            if (primero < segundo){
                return 1;
            }
            else {
                return 0;
            }
        }  
    }
    public int equal(ArrayList<String> a){
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
