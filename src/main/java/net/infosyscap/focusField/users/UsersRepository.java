package net.infosyscap.focusField.users;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {
    Users findByUsernameAndPassword(String username, String password);
}
