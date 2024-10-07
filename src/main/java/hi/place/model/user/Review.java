package hi.place.model.user;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Review {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String feedback;
    private Double point;
    private Long addedDate;
    @ManyToOne(cascade = CascadeType.REMOVE)
    private User user;
    @ManyToOne
    private Client client;
}
