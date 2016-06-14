package services;

import dto.UserDto;
import entity.Reservation.Reservation;
import entity.Reservation.ReservationRepository;
import entity.User.User;
import entity.User.UserRepository;
import main.Application;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.time.LocalDateTime;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
public class ReservationServiceTest {

    private User user;

    @Autowired
    private ReservationService reservationService;
    @Resource
    private ReservationRepository reservationRepository;
    @Resource
    private UserRepository userRepository;

    final private LocalDateTime startTime = LocalDateTime.of(2016, 10, 10, 10, 00, 00);
    private Reservation reservation;

    @Before
    public void before() {

        reservationRepository.deleteAll();
        userRepository.deleteAll();

        UserDto userDto = new UserDto();
        userDto.setEmail("test@test.test");
        userDto.setName("test");
        userDto.setRole("test");
        userDto.setSurname("test");
        userDto.setRole("admin");
        user = userRepository.save(new User(userDto));

        reservation = new Reservation();
        reservation.setUser(user);
        LocalDateTime endTime = startTime.plusHours(2L);
        reservation.setStartTime(startTime);
        reservation.setEndTime(endTime);

        reservationRepository.save(reservation);
    }

    @Test
    public void isInCollisionWithAnotherReservationShouldReturnTrueWhenReservationAlreadyExist() {
        Reservation reservation = new Reservation();
        reservation.setUser(user);
        LocalDateTime endTime = startTime.plusHours(2L);
        reservation.setStartTime(startTime);
        reservation.setEndTime(endTime);
        assertTrue(reservationService.isInCollisionWithAnotherReservation(reservation));
    }

    @Test
    public void isInCollisionWithAnotherReservationShouldReturnTrueWhenReservationEndTimeIsBetweenExistingReservation() {
        Reservation reservation = new Reservation();
        reservation.setUser(user);
        LocalDateTime endTime = startTime.plusHours(1L);
        reservation.setStartTime(startTime.minusHours(1L));
        reservation.setEndTime(endTime);
        assertTrue(reservationService.isInCollisionWithAnotherReservation(reservation));
    }

    @Test
    public void isInCollisionWithAnotherReservationShouldReturnTrueWhenReservationStartTimeIsBetweenExistingReservation() {
        Reservation reservation = new Reservation();
        reservation.setUser(user);
        LocalDateTime endTime = startTime.plusHours(3L);
        reservation.setStartTime(startTime.plusHours(1L));
        reservation.setEndTime(endTime);
        assertTrue(reservationService.isInCollisionWithAnotherReservation(reservation));
    }

    @Test
    public void isInCollisionWithAnotherReservationShouldReturnFlaseWhenReservationIsJustAfterAnother() {
        Reservation reservation = new Reservation();
        reservation.setUser(user);
        LocalDateTime endTime = startTime.plusHours(4L);
        reservation.setStartTime(startTime.plusHours(2L));
        reservation.setEndTime(endTime);
        assertFalse(reservationService.isInCollisionWithAnotherReservation(reservation));
    }

    @Test
    public void isInCollisionWithAnotherReservationShouldReturnFlaseWhenReservationIsJustBeforeAnother() {
        Reservation reservation = new Reservation();
        reservation.setUser(user);
        LocalDateTime endTime = startTime;
        reservation.setStartTime(startTime.minusHours(2L));
        reservation.setEndTime(endTime);
        assertFalse(reservationService.isInCollisionWithAnotherReservation(reservation));
    }

    @Test
    public void shouldReturnTrueAnywayIfItsTheSameReservation() {
        assertFalse(reservationService.isInCollisionWithAnotherReservation(reservation));
    }
}
