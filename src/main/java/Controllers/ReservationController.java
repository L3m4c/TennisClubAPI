package Controllers;

import Entity.Reservation.Reservation;
import services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RestController
public class ReservationController {

    @Autowired
    ReservationService reservationService;

    @RequestMapping(value = "/reservations", method = RequestMethod.GET)
    @ResponseBody
    public List<Reservation> getAllTicket() {
        return reservationService.selectAll();
    }

    @RequestMapping(value = "/reservation/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Reservation getReservation(@PathVariable long id) {
        return reservationService.select(id);
    }


    @RequestMapping(value = "/reservation", method = RequestMethod.POST)
    @ResponseBody
    public Reservation createReservation(@RequestParam(value="startTime", required = true) LocalDateTime start,
                                         @RequestParam(value="endTime", required = true) LocalDateTime end,
                                         @RequestParam(value="userId", required = true) long userId)

    {
        return reservationService.create(start, end, userId);
    }

    @RequestMapping(value = "/reservation", method = RequestMethod.PUT)
    @ResponseBody
    public Reservation updateReservation(@RequestParam(value="id", required = true) long id,
                                         @RequestParam(value="startTime", required = true) LocalDateTime start,
                                         @RequestParam(value="endTime", required = true) LocalDateTime end,
                                         @RequestParam(value="userId", required = true) long userId)

    {
        return reservationService.update(id, start, end, userId);
    }

    @RequestMapping(value = "/reservation/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public void deleteReservation(@PathVariable long id) {
        reservationService.delete(id);
    }

}