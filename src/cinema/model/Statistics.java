package cinema.model;

import java.util.List;

public class Statistics {
    int income;

    int available;
    int purchased;

    public Statistics(Room room) {
        List<Seat> seats = room.getSeats();
        List<Seat> purchasedSeats = room.getPurchasedSeats();

        income = purchasedSeats.stream()
                .map(Seat::getPrice)
                .reduce(0, Integer::sum);
        purchased = purchasedSeats.size();
        available = seats.size() - purchased;
    }

    public int getIncome() {
        return income;
    }

    public int getAvailable() {
        return available;
    }

    public int getPurchased() {
        return purchased;
    }
}
