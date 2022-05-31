package com.my.bank.dto;

import com.my.bank.dto.enums.Country;
import com.my.bank.dto.enums.UserGender;
import com.my.bank.dto.enums.UserRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.AUTO;

@Entity
@Table(name = "customer")
@Getter
@Setter
@NoArgsConstructor
public class CustomerDto implements UserDetails {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long customerId;

    @Column
    @NotBlank(message = "The field should not be empty")
    private String firstName;

    @Column
    @NotBlank(message = "The field should not be empty")
    private String lastName;

    @Column
    @NotBlank(message = "The field should not be empty")
    @Pattern(regexp = "\\d{10,11}"
            + "|^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$"
            + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$"
            + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$",
            message = "Wrong format")
    private String phoneNumber;

    @Column
    @Size(min = 4, message = "The minimum length is 4 characters")
    private String password;

    @Column
    @NotNull(message = "Select country")
    @Enumerated(STRING)
    private Country country;

    @Column
    @NotNull(message = "Select gender")
    @Enumerated(STRING)
    private UserGender gender;

    @ElementCollection(targetClass = UserRole.class, fetch = EAGER)
    @CollectionTable(name = "roles", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(STRING)
    private Set<UserRole> roles;

    @Column
    private boolean enabled = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return phoneNumber;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
