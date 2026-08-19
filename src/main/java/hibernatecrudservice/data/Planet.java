package hibernatecrudservice.data;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "planet")
@Entity(name = "planet")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Planet {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;
}
