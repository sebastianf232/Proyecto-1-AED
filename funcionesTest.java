/**
 * Universidad del Valle de Guatemala
 * Algoritmos y Estructuras de Datos
 * Proyecto 1
 * @author Juan Miguel Gonzalez-Campo 21077
 * @author Sebastian Franco 21484
 * @author Gabriela de León 21037
 * Prueba unitaria funciones
 */
import static org.junit.Assert.assertEquals;
import org.junit.Test;

import java.util.ArrayList;


public class funcionesTest {
    private final funciones f = new funciones(); 
    ArrayList<String> a = new ArrayList<>();
    @Test
    public void greater(){
        a.add("Prueba");
        a.add("15");
        a.add("11");
        assertEquals(1,f.greater(a),0.1);
   }

    @Test
    public void lesser(){
        a.add("Prueba");
        a.add("15");
        a.add("11");
        assertEquals(1,f.greater(a),0.1);
    }

    @Test
    public void equal(){
        a.add("Prueba");
        a.add("15");
        a.add("11");
        assertEquals(1,f.greater(a),0.1);
    }
}
