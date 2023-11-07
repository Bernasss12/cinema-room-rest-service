package cinema.util;

public class RoomPricingModel {

    public static int getSeatPrice(int row) {
        return row <= 4 ? 10 : 8;
    }

}
