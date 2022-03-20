/**
 * Universidad del Valle de Guatemala
 * Algoritmos y Estructuras de Datos
 * Proyecto 1
 * @author Juan Miguel Gonzalez-Campo 21077
 * @author Sebastian Franco 21484
 * @author Gabriela de León 21037
 * Prueba unitaria Stack
 */
import org.junit.Assert.*;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StackTest {
    private final Stack<Double> s = new Stack<>();
    //double sc;
    @Test
    void add() {
        s.add(5.0);
        s.add(4.0);
    }

    @Test
    void remove() {
        s.add(5.0);
        s.add(4.0);
        assertEquals(4 , s.remove(), 0.1);
    }

    @Test
    void peek(){
        s.add(5.0);
        s.add(4.0);
        assertEquals(4,s.peek(),0.1);
    }

    @Test
    void size(){
        s.add(5.0);
        s.add(4.0);
        assertEquals(2,s.size(),0.1);
    }

    @Test
    void empty(){

    }
}
