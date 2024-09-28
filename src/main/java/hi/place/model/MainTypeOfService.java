package hi.place.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "main_type_of_service")
public class MainTypeOfService {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String name;
}
