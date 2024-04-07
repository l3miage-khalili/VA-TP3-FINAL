package fr.uga.l3miage.spring.tp3.exceptions.technical;

public class NotFoundSessionException extends Exception{
    public NotFoundSessionException(String message){
        super(message);
    }
}
