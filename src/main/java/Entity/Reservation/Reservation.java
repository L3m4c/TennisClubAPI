package Entity.Reservation;

import Entity.User.User;
import Entity.converter.LocalDateTimeAttributeConverter;
import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
public class Reservation implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private User user;

    @Convert(converter = LocalDateTimeAttributeConverter.class)
    private LocalDateTime startTime;

    @Convert(converter = LocalDateTimeAttributeConverter.class)
    private LocalDateTime endTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
}
