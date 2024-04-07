package fr.uga.l3miage.spring.tp3.exceptions.rest;

public class ChangingSessionStatusRestException extends RuntimeException{

    public ChangingSessionStatusRestException(String message){
        super(message);
    }
}
