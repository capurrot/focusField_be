package net.infosyscap.focusField.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "app_users")
@NoArgsConstructor
@Data
public class AppUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = true)
    @ToString.Exclude
    @JsonIgnore
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = true)
    private String cognome;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled = true;

    @Column(nullable = false)
    private boolean googleAccount = false;

    private String googleId;
    private String pictureUrl;

    private String verificationCode;
    private boolean verified;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.name()))
                .collect(Collectors.toList());
    }

    public AppUser(String username, String password, String email, String nome, String cognome,
                   Collection<? extends GrantedAuthority> authorities) {
        this(username, password, email, nome, cognome, true, true, true, true, authorities);
    }

    public AppUser(String username, String password, String email, String nome, String cognome,
                   boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked,
                   Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.nome = nome;
        this.cognome = cognome;
        this.enabled = enabled;
        this.accountNonExpired = accountNonExpired;
        this.credentialsNonExpired = credentialsNonExpired;
        this.accountNonLocked = accountNonLocked;
    }
}
