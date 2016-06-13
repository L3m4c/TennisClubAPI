package controllers;

import dto.ErrorDto;
import dto.ReservationDto;
import entity.Reservation.Reservation;
import entity.User.User;
import entity.User.UserRepository;
import exceptions.ModificationNotAllowedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import services.ReservationService;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ReservationController {
    @Autowired
    ReservationService reservationService;
    @Resource
    UserRepository userRepository;

    @RequestMapping(
            value = {"/reservations"},
            method = {RequestMethod.GET}
    )
    @ResponseBody
    public List<ReservationDto> getAllReservations() {
        return (List) this.reservationService.selectAll().stream().map((reservation) -> {
            return new ReservationDto(reservation);
        }).collect(Collectors.toList());
    }

    @RequestMapping(
            value = {"/reservation/{id}"},
            method = {RequestMethod.GET}
    )
    @ResponseBody
    public ReservationDto getReservation(@PathVariable long id) {
        return new ReservationDto(this.reservationService.select(id));
    }

    @RequestMapping(
            value = {"/reservation"},
            method = {RequestMethod.POST}
    )
    @ResponseBody
    public ReservationDto createReservation(@RequestBody ReservationDto reservationDto) {
        Reservation reservation = this.reservationService.create(LocalDateTime.ofEpochSecond(reservationDto.getStartTime().longValue() / 1000L, 0, ZoneOffset.UTC),
                LocalDateTime.ofEpochSecond(reservationDto.getEndTime().longValue() / 1000L, 0, ZoneOffset.UTC),
                new User(reservationDto.getUser()));
        return new ReservationDto(reservation);
    }

    @RequestMapping(
            value = {"/reservation"},
            method = {RequestMethod.PUT}
    )
    @ResponseBody
    public ResponseEntity updateReservation(@RequestBody ReservationDto reservationDto, @AuthenticationPrincipal UserDetails userDetails) {
        User currentUser = (User) this.userRepository.findUserByEmail(userDetails.getUsername()).get(0);

        try {
            Reservation e = this.reservationService.update(reservationDto.getId().longValue(),
                    LocalDateTime.ofEpochSecond(reservationDto.getStartTime().longValue() / 1000L, 0, ZoneOffset.UTC),
                    LocalDateTime.ofEpochSecond(reservationDto.getEndTime().longValue() / 1000L, 0, ZoneOffset.UTC),
                    new User(reservationDto.getUser()), currentUser);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ReservationDto(e));
        } catch (ModificationNotAllowedException var5) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorDto(var5.getMessage()));
        }
    }

    @RequestMapping(
            value = {"/reservation/{id}"},
            method = {RequestMethod.DELETE}
    )
    @ResponseBody
    public ResponseEntity deleteReservation(@PathVariable long id, @AuthenticationPrincipal UserDetails userDetails) {
        User currentUser = this.userRepository.findUserByEmail(userDetails.getUsername()).get(0);

        try {
            this.reservationService.delete(id, currentUser);
            return ResponseEntity.status(HttpStatus.CREATED).body("Deleted.");
        } catch (ModificationNotAllowedException var6) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorDto(var6.getMessage()));
        }
    }
}