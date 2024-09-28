package hi.place.model.user;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class  Rating {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String feedback;
    private Double point;
    @ManyToOne(cascade = CascadeType.REMOVE)
    private User user;
    @ManyToOne
    private Client client;
}
