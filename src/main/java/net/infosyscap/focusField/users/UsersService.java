package net.infosyscap.focusField.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    public Users findByUsernameAndPassword(String username, String password) {
        return usersRepository.findByUsernameAndPassword(username, password);
    }
}
