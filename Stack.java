import java.util.Vector;
/**
 * Universidad del Valle de Guatemala
 * Algoritmos y Estructuras de Datos
 * Proyecto 1
 * @author Juan Miguel Gonzalez-Campo 21077
 * @author Sebastian Franco 21484
 * @author Gabriela de León 21037
 * Clase Stack Pilas Vector
 */


public class Stack<E>{
    Vector<E> v = new Vector<E>();

    /**
     * @param item
     */
    public void add(E item) { ///Metodo del Stack que permite aniadir elemento al vector
        v.addElement(item);
    }

    /**
     * @return boolean 
     */
    public boolean empty(){ /// Metodo del Stack que permita saber si el vector está vacio
        return size() == 0;
    } 

    public E remove() { ///Metodo del Stack que permite quitar un elemento del vector, para luego poderlo aniadir en otro lugar.
        if (v.size()>0){
            return v.remove(v.size()-1);
        }
        return null;
    }

    public E peek() { ///Metodo del Stack que permite obtener el elemento que se quito.
        if (v.size()>0){
            return v.get(v.size()-1);
        }
        return null;
    }

    public int size() {  ///Metodo que permite ver el tamanio del vector
        return v.size();
    }
}
