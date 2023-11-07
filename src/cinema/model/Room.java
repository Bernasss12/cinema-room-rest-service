package cinema.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cinema.exception.InvalidSeatException;
import cinema.exception.UnavailableSeatException;
import cinema.exception.WrongPasswordException;
import cinema.exception.WrongTokenException;
import cinema.util.RoomPricingModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class Room {

    private final String password = "super_secret";
    private int rows;
    private int columns;
    private List<Seat> seats;

    @JsonIgnore
    private final Map<Seat, Ticket> purchasedSeats;

    public Room(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.seats = new ArrayList<>();
        this.purchasedSeats = new HashMap<>();

        for (int row = 1; row <= rows; row++) {
            for (int col = 1; col <= columns; col++) {
                seats.add(new Seat(row, col, RoomPricingModel.getSeatPrice(row)));
            }
        }
    }

    /**
     * Checks whether the seat is available.
     *
     * @param row    where seat is. [1-rows]
     * @param column where seat is. [1-columns]
     * @return true if available
     */
    private boolean checkAvailability(int row, int column) throws InvalidSeatException {
        Seat seat = getSeat(row, column);
        return !purchasedSeats.containsKey(seat);
    }

    private boolean checkPassword(String attempted) {
        return this.password.equals(attempted);
    }

    public Ticket purchaseSeat(int row, int columns) throws InvalidSeatException, UnavailableSeatException {
        if (!checkAvailability(row, columns)) throw new UnavailableSeatException();
        Seat seat = getSeat(row, columns);
        purchasedSeats.put(seat, new Ticket(seat));
        return purchasedSeats.get(seat);
    }

    public Ticket returnTicket(Ticket ticket) {
        Ticket returningTicket = purchasedSeats.values()
                .stream()
                .filter(currentTicket -> currentTicket.getToken().equals(ticket.getToken()))
                .findFirst()
                .orElseThrow(WrongTokenException::new);

        purchasedSeats.remove(returningTicket.getTicket());

        return returningTicket.returned();
    }

    /**
     * Gets the seat at given position.
     *
     * @param row    where seat is. [0-rows[
     * @param column where seat is. [0-columns[
     * @return the seat in the given position.
     * @throws InvalidSeatException if position is outside the bounds of the room.
     */
    private Seat getSeat(int row, int column) throws InvalidSeatException {
        return seats.stream()
                .filter(seat -> seat.getRow() == row && seat.getColumn() == column)
                .findAny()
                .orElseThrow(InvalidSeatException::new);
    }

    public Statistics getStatistics(String password) throws WrongPasswordException {
        if (!checkPassword(password)) throw new WrongPasswordException();
        return new Statistics(this);
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    @JsonIgnore
    public List<Seat> getPurchasedSeats() {
        return purchasedSeats.keySet().stream().toList();
    }

}
