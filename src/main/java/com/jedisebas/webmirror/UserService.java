package com.jedisebas.webmirror;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    public final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public boolean addNewUser(User user) {
        Optional<User> userOptional = userRepository.findUserByEmail(user.getEmail());
        if (userOptional.isPresent()) {
            return false;
        }
        userRepository.save(user);
        return true;
    }

    public boolean loginUser(User user) {
        Optional<User> userOptional = userRepository.findUserByEmail(user.getEmail());
        if (userOptional.isPresent()) {
            String password = userRepository.findPasswordByEmail(user.getEmail());
            if (password.equals(user.getPassword())) {
                LoggedUser.id = userOptional.get().getId();
                LoggedUser.name = userOptional.get().getName();
                LoggedUser.lastname = userOptional.get().getLastname();
                LoggedUser.password = userOptional.get().getPassword();
                LoggedUser.email = userOptional.get().getEmail();
                LoggedUser.emailPassword = userOptional.get().getEmailpassword();
                LoggedUser.nick = userOptional.get().getNick();
                LoggedUser.isLogged = true;
                return true;
            }
        }
        return false;
    }

    public String getUserNameFromDb(User user) {
        return userRepository.findNameByEmail(user.getEmail());
    }

    public String getUserLastnameFromDb(String name) {
        return userRepository.findLastnameByName(name);
    }

    public ArrayList<String> getPictures(Long userId) {
        return userRepository.findPicturesByUserId(userId);
    }
}
