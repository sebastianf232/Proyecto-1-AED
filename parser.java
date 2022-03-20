import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Map;

public class parser {
    tokenFinder tf = new tokenFinder();
    funciones fn = new funciones();
    calculator calc = new calculator();

    public void parseFull(ArrayList<ArrayList<String>> a, Map<String, ArrayList<ArrayList<String>>> p){
        tokenFinder tf = new tokenFinder();
        funciones fn = new funciones();
        int i = a.size()-1;
        boolean on = true;
        while(on){
            ArrayList<String> s = new ArrayList<>();
            s = a.get(i);
            if (p.containsKey(s.get(0))){
                ArrayList<ArrayList<String>> evalfun = p.get(s.get(0));
                String evalparam = s.get(1);

                

            }
            if (s.contains("DEFUN")){
                String name = s.get(1);
                ifZero(i);
                i--;
                ArrayList<String> param = a.get(i);

                ArrayList<ArrayList<String>> func = new ArrayList<>();

                func.add(param);
                while (i >= 0){
                    
                    ifZero(i);
                    i--;
                    func.add(a.get(i));
                    
                }
                p.put(name, func);
                break;



            }
            if (s.contains("ATOM")){
              
            }
            if (s.contains("LIST")){
                
            }
            if (s.contains("QUOTE") || s.contains("'")){
                
            }
            if (s.contains(">")){
                int k = fn.greater(s);
                if (k == 1){
                    System.out.println("TRUE");
                }
                if (k == 0){
                    System.out.println("FALSE");
                }
                
                i--;
                

            }
            if (s.contains("<")){
                int k = fn.lesser(s);
                if (k == 1){
                    System.out.println("TRUE");
                }
                if (k == 0){
                    System.out.println("FALSE");
                }
                i--;
                
            }
            if (s.contains("COND")){
                if(a.get(i-1).size() < 2){
                    ifZero(i);
                    i--;
                    ArrayList<String> condic = new ArrayList<>();
                    condic = a.get(i-1);
                    ifZero(i);
                    i--;
                    if (parse(condic) == 1){
                        ifZero(i);
                        i--;
                        System.out.println(calc.evaluatePrefix(a.get(i)));
                        
                        break;
                    }
                    else{
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
            if (s.contains("EQUAL")){
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
                System.out.println(calc.evaluatePrefix(s));
                break;
            }
        }
    }
    public int parse(ArrayList<String> s){
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
    public void ifZero(int i){
        if (i == 0){
            System.out.println("Expresión invalida");
        }
        
    }
    
}
