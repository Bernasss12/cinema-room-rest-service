package cinema.controller;

import cinema.model.Room;
import cinema.model.Seat;
import cinema.model.Statistics;
import cinema.model.Ticket;
import cinema.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoomController {

    private final RoomRepository repository;

    @Autowired
    public RoomController(RoomRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/seats")
    public Room getRoom() {
        return repository.getRoom();
    }

    @PostMapping(value = "/purchase", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Ticket> purchaseTicket(@RequestBody Seat seat) {
        Ticket ticket = repository.getRoom().purchaseSeat(seat.getRow(), seat.getColumn());
        return ResponseEntity.ok(ticket);
    }

    @PostMapping(value = "/return", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Ticket> returnTicket(@RequestBody Ticket ticket) {
        return ResponseEntity.ok(repository.getRoom().returnTicket(ticket));
    }

    @GetMapping(value = "/stats", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Statistics> getStatistics(@RequestParam(required = false) String password) {
        return ResponseEntity.ok(repository.getRoom().getStatistics(password));
    }

}
