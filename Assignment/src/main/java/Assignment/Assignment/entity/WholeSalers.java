package Assignment.Assignment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name="wholesaler")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WholeSalers {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @Column(name="first_name")
    String firstName;

    @Column(name="last_name")
    String lastName;

    @Column(name="email_id")
    String emailId;

    @Column(name="phone_no")
    String phoneNo;

    @Column(name="wholesaler_id")
    String wholeSalerId;

    @Column(name="roles")
    String role;

    @Column(name="loc_id")
    String locId;

}
