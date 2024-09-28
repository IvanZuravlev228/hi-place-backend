package hi.place.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ServiceItem {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    private TypeOfService typeOfService;
}
