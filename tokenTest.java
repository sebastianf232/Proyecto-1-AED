/**
 * Universidad del Valle de Guatemala
 * Algoritmos y Estructuras de Datos
 * Proyecto 1
 * @author Juan Miguel Gonzalez-Campo 21077
 * @author Sebastian Franco 21484
 * @author Gabriela de León 21037
 * Prueba unitaria tokenFinder
 */
import static org.junit.Assert.assertEquals;
import org.junit.Test;

import java.util.ArrayList;


public class tokenTest {
    tokenFinder tf = new tokenFinder();

    @Test
    public void separate(){
        String s = "(defun h(x y) (+ x y))";
        ArrayList<String> res = new ArrayList<>();
        res.add("");
        res.add("(defun");
        res.add("h");
        res.add("(x");
        res.add("y)");
        res.add("(+");
        res.add("x");
        res.add("y))");
        assertEquals(res, tf.separate(s));
    }
    @Test
    public void listaLisp(){
        String s = "(defun h(x y) (+ x y))";
        ArrayList<ArrayList<String>> res = new ArrayList<>();
        ArrayList<String> temp = new ArrayList<>();
        ArrayList<String> temp1 = new ArrayList<>();
        ArrayList<String> temp2 = new ArrayList<>();
        temp.add("+");
        temp.add("x");
        temp.add("y");
        res.add(temp);
        temp1.add("x");
        temp1.add("y");
        res.add(temp1);
        temp2.add("defun");
        temp2.add("h");
        res.add(temp2);
        assertEquals(res, tf.listaLisp(s));
    }
    
}
