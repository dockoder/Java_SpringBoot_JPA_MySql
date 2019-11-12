package com.dockoder.demo.service;

import com.dockoder.demo.domain.User;
import com.dockoder.demo.domain.UserRepository;
import com.dockoder.demo.exceptions.EmailExistsException;
import com.dockoder.demo.exceptions.UserNameNotFoundException;
import com.dockoder.demo.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerNewUser(User u) throws EmailExistsException {
        if (emailExists(u.getEmail()))
            throw new EmailExistsException(
                    "The email address: " + u.getEmail() +
                            " is already registered. Please choose another one." );
        return userRepository.save(u);
    }


    public User getUserByName(String name) throws UserNameNotFoundException {

        User u = userRepository.findByName(name);
        if (u==null) throw new UserNameNotFoundException("No user found.");

        return u;
    }

    public User getUserById(long id) throws UserNotFoundException {
        Optional<User> u = userRepository.findById(id);

        if (!u.isPresent()) throw new UserNotFoundException("User not found. ID= " + id);
        return  u.get();
    }

    public List<User> getUsers() throws UsersNotFoundException {
        List<User> list = userRepository.findAll();
        if (list == null || list.isEmpty())
            throw new UsersNotFoundException("User list empty or null");
        return  list;
    }


    private boolean emailExists(String email){
        User user = userRepository.findByEmail(email);
        return (user!=null);
    }

}
