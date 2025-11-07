package edu.upc.backend.llibreria;

import edu.upc.backend.models.Llibre;

import java.nio.file.StandardWatchEventKinds;
import java.util.LinkedList;
import java.util.Stack;

public class PilaDeLlibres {
    private LinkedList<Stack<Llibre>> _db; // linked list tambe es un deque.
    private static int limitPila = 10;

    public PilaDeLlibres()
    {
        _db = new LinkedList<Stack<Llibre>>();
        _db.add(new Stack<Llibre>());
    }

    public void push(String id, String isbn, String titul, String editorial,
               String publicacio, String edicio, String autor, String tematica)
    {
        Stack<Llibre> pila = _db.getLast();
        if(pila.size() < limitPila)
        {
            pila.add(new Llibre(id,isbn,titul,editorial,publicacio,edicio,autor,tematica));
        }
        else
        {
            // nueva pila
            Stack<Llibre> nPila = new Stack<Llibre>();
            nPila.add(new Llibre(id,isbn,titul,editorial,publicacio,edicio,autor,tematica));
            _db.add(nPila);
        }
    }

    public Llibre pop() throws ArrayIndexOutOfBoundsException
    {
        //if(_db.size() == 0) throw new ArrayIndexOutOfBoundsException("No n'hi han més piles!");
        Stack<Llibre> a = _db.getLast();
        if(a.empty())
        {
            _db.removeLast();
            if(_db.size() == 0) throw new ArrayIndexOutOfBoundsException("No n'hi han més piles!");
            Stack<Llibre> b = _db.getLast();
            Llibre l = b.pop();
            return l;
        }
        else
        {
            Llibre l = a.pop();
            return l;
        }
    }

    public  Integer size()
    {
        int num = 0;
        for(int i = 0; i < _db.size(); i++)
        {
            num += _db.get(i).size();
        }
        return num;
    }
}
