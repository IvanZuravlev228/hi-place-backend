package hi.place.model.address;

import hi.place.model.user.User;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Address {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String country;
    private String city;
    private String borough;
    private String road;
    private String houseNumber;
    private String suburb;
    private Double lat;
    private Double lon;
    @ManyToOne
    private User user;
}
