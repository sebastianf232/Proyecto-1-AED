import java.util.Vector;

/**
 * Universidad del Valle de Guatemala
 * Algoritmos y Estructuras de Datos
 * Hoja de Trabajo 4
 * @author Juan Miguel Gonzalez-Campo 21077
 * @author Sebastian Franco 21484
 * @author Dimitrio Badani 20092
 * Clase Stack Pilas Vector
 */
public class Stack<E>{
    Vector<E> v = new Vector<E>();

    public void add(E item) {
        v.addElement(item);
    }

    public boolean empty(){
        return size() == 0;
    } 

    public E remove() {
        if (v.size()>0){
            return v.remove(v.size()-1);
        }
        return null;
    }

    public E peek() {
        if (v.size()>0){
            return v.get(v.size()-1);
        }
        return null;
    }

    public int size() {
        return v.size();
    }
}
