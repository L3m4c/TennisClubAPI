package Services;

import Entity.PresentationUser.PresentationUser;
import Entity.Reservation.Reservation;
import Entity.Reservation.ReservationRepository;
import Entity.User.User;
import Entity.User.UserRepository;
import javax.annotation.Resource;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by L3m4C on 30/11/2015.
 */
public class ReservationService {

    @Resource
    ReservationRepository reservationRepository;
    @Resource
    UserRepository userRepository;

    public Reservation select(long id) {
        return reservationRepository.findOne(id);
    }

    public Reservation create(Date start, Date end, long authorId) {
        Reservation reservation = new Reservation();
        reservation.setStartTime(start);
        reservation.setEndTime(end);
        reservation.setUser(userRepository.findOne(authorId));
        return reservationRepository.save(reservation);
    }

    public Reservation update(long id, Date start, Date end, long authorId) {
        Reservation reservation = reservationRepository.findOne(id);
        reservation.setStartTime(start);
        reservation.setEndTime(end);
        reservation.setUser(userRepository.findOne(authorId));
        return reservationRepository.save(reservation);
    }

    public void delete(long id) {
        reservationRepository.delete(id);
    }

    public List<Reservation> selectAll() {
        return StreamSupport.stream(reservationRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }
    public List<Reservation> selectAll(List<Long> id) {
        return StreamSupport.stream(reservationRepository.findAll(id).spliterator(), false)
                .collect(Collectors.toList());
    }

}
