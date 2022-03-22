/**
 * Universidad del Valle de Guatemala
 * Algoritmos y Estructuras de Datos
 * Proyecto 1
 * @author Juan Miguel Gonzalez-Campo 21077
 * @author Sebastian Franco 21484
 * @author Gabriela de León 21037
 * Prueba unitaria calculator
 */
import static org.junit.Assert.assertEquals;
import org.junit.Test;

import java.util.ArrayList;

public class calculatorTest {
    private final tokenFinder tf = new tokenFinder();
    private final calculator calc = new calculator();
    
    @Test
    public void evalPre(){
        
        ArrayList<String> op1 = new ArrayList<>();
        op1.add("+");
        op1.add("10");
        op1.add("10");
        assertEquals(calc.evaluatePrefix(op1), 20, 0.01);
    }

    @Test
    public void calculate(){
        String op = "(+ (* 2 2)(/ 5 10))";
        assertEquals(calc.calculate(tf.listaLisp(op)), 4.5, 0.01);
    }
    @Test
    public void isOperand(){
        assertEquals(calc.isOperand("/"), false);
        assertEquals(calc.isOperand("+"), false);
        assertEquals(calc.isOperand("-"), false);
        assertEquals(calc.isOperand("*"), false);
        assertEquals(calc.isOperand("1"), true);
        assertEquals(calc.isOperand("2"), true);
        assertEquals(calc.isOperand("3"), true);
    }
}
