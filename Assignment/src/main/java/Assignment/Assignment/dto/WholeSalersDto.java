package Assignment.Assignment.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class WholeSalersDto {

    UUID id;

    @NotEmpty(message = "FirstName should not be Null")
    @NotNull(message = "FirstName should not be Null")
    String firstName;

    @NotEmpty(message = "LastName should not be Null")
    @NotNull(message = "LastName should not be Null")
    String lastName;

    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
            flags = Pattern.Flag.CASE_INSENSITIVE,message = "Please enter valid email id")
    String emailId;

    @Digits(message="Number should contain 10 digits only.", fraction = 0, integer = 10)
    @Pattern(regexp = "^[0-9]{10}$", message = "Please enter valid phoneNumber")
    String phoneNo;

    @Pattern(regexp = "^[A-Z]{3}[0-9]{3}$", message = "Please enter valid WholeSalerId")
    String wholeSalerId;

    @NotEmpty(message = "Role Field Should not be Empty")
    String role;

    @Pattern(regexp = "^[A-Z]{2}[0-9]{6}$", message = "Please enter valid LocId")
    String locId;

    public WholeSalersDto(String firstName, String lastName, String emailId, String phoneNo, String wholeSalerId, String role, String locId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId = emailId;
        this.phoneNo = phoneNo;
        this.wholeSalerId = wholeSalerId;
        this.role = role;
        this.locId = locId;
    }
}
