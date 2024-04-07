package hkmu.comps380f.exception;

import java.io.IOException;

public class UserNotFound extends Exception{
    public UserNotFound(long id){
        super("User " + id + " does not exist");
    }
}