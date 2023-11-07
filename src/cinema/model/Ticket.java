package cinema.model;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Ticket {

    private String token;
    private Seat ticket;

    public Ticket() {
        this.token = "";
        this.ticket = null;
    }

    public Ticket(Seat ticket) {
        this.token = UUID.randomUUID().toString();
        this.ticket = ticket;
    }

    public Ticket returned() {
        this.token = "";
        return this;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Seat getTicket() {
        return ticket;
    }

    public void setTicket(Seat ticket) {
        this.ticket = ticket;
    }
}
