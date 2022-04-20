package com.jedisebas.webmirror;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    public int addNewUser(User user) {
        Optional<User> userOptional = userRepository.findUserByEmail(user.getEmail());
        Optional<User> userOptional2 = userRepository.findUserByNick(user.getNick());
        if (userOptional.isPresent() || userOptional2.isPresent()) {
            if (userOptional.isPresent() && userOptional2.isPresent()) {
                return 1;
            } else if (userOptional.isPresent()) {
                return 2;
            } else {
                return 3;
            }
        }
        userRepository.save(user);
        return 0;
    }

    public boolean loginUser(User user) {
        Optional<User> userOptional = userRepository.findUserByEmail(user.getEmail());
        Optional<User> userOptional2 = userRepository.findUserByNick(user.getEmail());
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
        } else if (userOptional2.isPresent()) {
            String password = userRepository.findPasswordByNick(user.getEmail());
            if (password.equals(user.getPassword())) {
                LoggedUser.id = userOptional2.get().getId();
                LoggedUser.name = userOptional2.get().getName();
                LoggedUser.lastname = userOptional2.get().getLastname();
                LoggedUser.password = userOptional2.get().getPassword();
                LoggedUser.email = userOptional2.get().getEmail();
                LoggedUser.emailPassword = userOptional2.get().getEmailpassword();
                LoggedUser.nick = userOptional2.get().getNick();
                LoggedUser.isLogged = true;
                return true;
            }
        }
        return false;
    }

    public String getUserNameFromDb(User user) {
        Optional<User> userOptional = userRepository.findUserByEmail(user.getEmail());
        if (userOptional.isPresent()) {
            return userRepository.findNameByEmail(user.getEmail());
        } else {
            return userRepository.findNameByNick(user.getEmail());
        }
    }

    public String getUserLastnameFromDb(String name) {
        return userRepository.findLastnameByName(name);
    }

    public ArrayList<String> getPictures(Long userId) {
        return userRepository.findPicturesByUserId(userId);
    }

    public void deleteAccount() {
        userRepository.deleteUser(LoggedUser.id);
        userRepository.deleteEvent(LoggedUser.id);
        userRepository.deletePicture(LoggedUser.id);
    }

    public void deletePicture(String picture_name) {
        userRepository.deletePictureByName(picture_name);
    }
}
