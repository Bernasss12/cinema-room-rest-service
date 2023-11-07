package cinema.repository;

import cinema.model.Room;
import org.springframework.stereotype.Repository;

@Repository
public class RoomRepository {

    public static final int ROWS = 9;
    public static final int COLUMNS = 9;
    public static final Room ROOM = new Room(9, 9);

    public Room getRoom() {
        return ROOM;
    }
}
