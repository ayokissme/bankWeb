package com.my.bank.dto.responseBody;

import com.my.bank.dto.enums.Country;
import com.my.bank.dto.enums.UserGender;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class CustomerEdited {

    @NotBlank(message = "The field should not be empty")
    private String firstName;

    @NotBlank(message = "The field should not be empty")
    private String lastName;

    @NotBlank(message = "The field should not be empty")
    @Pattern(regexp = "\\d{10,11}"
            + "|^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$"
            + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$"
            + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$",
            message = "Wrong format")
    private String phoneNumber;

    @NotNull(message = "Select gender")
    private UserGender gender;

    @NotNull(message = "Select country")
    private Country country;
}
