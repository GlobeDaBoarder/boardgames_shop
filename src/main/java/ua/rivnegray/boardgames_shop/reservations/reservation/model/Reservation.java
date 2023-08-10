package ua.rivnegray.boardgames_shop.reservations.reservation.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ua.rivnegray.boardgames_shop.model.UserProfile;
import ua.rivnegray.boardgames_shop.reservations.timeslot.model.Timeslot;

@Entity
@Getter
@SuperBuilder(toBuilder = true)
@Table(name = "reservation")
@NoArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "timeslot_id")
    private Timeslot timeslot;

    @ManyToOne
    @JoinColumn(name = "user_profile_id")
    private UserProfile user;

    private String comment;

    public Reservation(UserProfile user, Timeslot timeslot, String comment) {
        this.timeslot = timeslot;
        this.user = user;
        this.comment = comment;
    }

    public Reservation(Timeslot timeslot, String comment) {
        this.timeslot = timeslot;
        this.comment = comment;
    }
}
