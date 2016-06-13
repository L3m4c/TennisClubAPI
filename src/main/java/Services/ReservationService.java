package services;

import entity.Reservation.Reservation;
import entity.Reservation.ReservationRepository;
import entity.User.User;
import entity.User.UserRepository;
import enums.RoleEnum;
import exceptions.ModificationNotAllowedException;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ReservationService {
    @Resource
    ReservationRepository reservationRepository;
    @Resource
    UserRepository userRepository;

    public ReservationService() {
    }

    public Reservation select(long id) {
        return (Reservation) this.reservationRepository.findOne(Long.valueOf(id));
    }

    public Reservation create(LocalDateTime start, LocalDateTime end, User author) {
        Reservation reservation = new Reservation();
        reservation.setStartTime(start);
        reservation.setEndTime(end);
        reservation.setUser(author);
        return (Reservation) this.reservationRepository.save(reservation);
    }

    public Reservation update(long id, LocalDateTime start, LocalDateTime end, User author, User currentUser) throws ModificationNotAllowedException {
        this.checkAuthorization(author, currentUser);
        Reservation reservation = (Reservation) this.reservationRepository.findOne(Long.valueOf(id));
        reservation.setStartTime(start);
        reservation.setEndTime(end);
        reservation.setUser(author);
        return (Reservation) this.reservationRepository.save(reservation);
    }

    public void delete(long id, User currentUser) throws ModificationNotAllowedException {
        this.checkAuthorization(((Reservation) this.reservationRepository.findOne(Long.valueOf(id))).getUser(), currentUser);
        this.reservationRepository.delete(Long.valueOf(id));
    }

    private void checkAuthorization(User author, User currentUser) throws ModificationNotAllowedException {
        if (author.getId() != currentUser.getId() && !currentUser.getRole().equals(RoleEnum.ADMIN.getValue())) {
            throw new ModificationNotAllowedException("Your are not allowed to edit this reservation !");
        }
    }

    public List<Reservation> selectAll() {
        return (List) StreamSupport.stream(this.reservationRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public List<Reservation> selectAll(List<Long> id) {
        return (List) StreamSupport.stream(this.reservationRepository.findAll(id).spliterator(), false).collect(Collectors.toList());
    }

    public boolean isInCollisionWithAnotherReservation(Reservation reservation) {
        return !this.reservationRepository.findReservationBetweenStartAndEndDate(reservation.getStartTime(), reservation.getEndTime()).isEmpty();
    }
}
