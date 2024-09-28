package hi.place.model;

import hi.place.model.user.User;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class UserServiceImages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private TypeOfService typeOfService;
    @ManyToOne
    private User user;
    private String path;
}
