package hi.place.model;

import hi.place.model.user.User;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Column(length = 400)
    private String description;
    private String photoUrl;
    private Long startDate;
    private Long endDate;
    private Integer discount;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    private TypeOfService typeOfService;
}
