package es.urjc.laliga.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Quote {

    private String message;
   

    public Quote() {
    }

    public String getMessage() {
        return message;
    }

    public void setType(String message) {
        this.message = message;
    }

    
    @Override
    public String toString() {
        return "Quote{" +
                "message='" + message + '\''  +
                '}';
    }
}