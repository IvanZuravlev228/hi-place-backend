package hi.place.model.user;

import hi.place.model.MainTypeOfService;
import hi.place.model.ServiceItem;
import hi.place.model.TypeOfService;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Price {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private ServiceItem serviceItem;
    @ManyToOne(fetch = FetchType.LAZY)
    private TypeOfService typeOfService;
    @ManyToOne(fetch = FetchType.LAZY)
    private MainTypeOfService mainTypeOfService;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    private Double price;
    private String timeUnit;
}
