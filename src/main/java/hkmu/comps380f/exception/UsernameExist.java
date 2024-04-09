package hkmu.comps380f.exception;

public class UsernameExist extends Exception{
    public UsernameExist(String username){
        super("Username: " + username + " already used!");
    }
}
