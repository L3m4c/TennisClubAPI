package dto;

import entity.Reservation.Reservation;

import java.io.Serializable;
import java.time.ZoneOffset;

public class ReservationDto implements Serializable {

    private Long id;
    private Long startTime;
    private Long endTime;
    private UserDto user;

    public ReservationDto() {
    }

    public ReservationDto(Reservation reservation) {
        this.id = reservation.getId();
        this.startTime = reservation.getStartTime().toInstant(ZoneOffset.UTC).toEpochMilli();
        this.endTime = reservation.getEndTime().toInstant(ZoneOffset.UTC).toEpochMilli();
        this.user = new UserDto(reservation.getUser());
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }
}
