package Entity.Reservation;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by L3m4C on 30/11/2015.
 */
public interface ReservationRepository extends CrudRepository<Reservation, Long> {

    @Query("select reserv from Reservation reserv " +
            "where (:start>=reserv.startTime and :start<reserv.endTime)" +
            "or (:endT>reserv.startTime and :endT<=reserv.endTime)")
    public List<Reservation> findReservationBetweenStartAndEndDate(@Param("start") LocalDateTime start, @Param("endT") LocalDateTime end);


    /*@Query("SELECT p FROM Person p WHERE LOWER(p.lastName) = LOWER(:lastName)")
    public List<Person> find(@Param("lastName") String lastName);*/

}
