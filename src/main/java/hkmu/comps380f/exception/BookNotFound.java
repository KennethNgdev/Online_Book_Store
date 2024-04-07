package hkmu.comps380f.exception;

public class BookNotFound extends Exception{
    public BookNotFound(Long id){
        super("Book " + id + " does not exist");
    }
}