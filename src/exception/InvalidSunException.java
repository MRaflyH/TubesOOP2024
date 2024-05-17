package exception;

public class InvalidSunException extends Exception{
    String message;

    public InvalidSunException(String message){
        this.message = message;
    }
    public String getMessage(){
        return String.format("Cannot plant! sun not enough.", message);
        
    }
}
