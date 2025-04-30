package hi.place.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Visit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String ipAddress;

    private Long userId;

    @Column(nullable = false, updatable = false)
    private Long visitTime;
}
