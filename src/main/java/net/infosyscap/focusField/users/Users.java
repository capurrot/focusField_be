package net.infosyscap.focusField.users;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")

public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false, unique = true, length = 20)
    private String username;

    @Column(nullable = false,  length = 50)
    private String name;

    @Column(nullable = false, length = 50)
    private String surname;

    @Column(nullable = false,  unique = true,  length = 100)
    private String email;

    @Column(nullable = false,  length = 20)
    private String password;

    @Column(nullable = false)
    private Role role;
}