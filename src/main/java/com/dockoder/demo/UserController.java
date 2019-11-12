package com.dockoder.demo;

import com.dockoder.demo.domain.User;
import com.dockoder.demo.domain.UserRepository;
import com.dockoder.demo.exceptions.EmailExistsException;
import com.dockoder.demo.exceptions.UserNotFoundException;
import com.dockoder.demo.service.UserService;
import com.dockoder.demo.service.UsersNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    // Returns all user occurrences
    @GetMapping
    public ResponseEntity<List<User>> findAll(){
        List<User> list;
        try{
            list = userService.getUsers();
        } catch (UsersNotFoundException e){
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(list);
    }

    // Returns a user from a id
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") long id){
        User user;
        try {
            user = userService.getUserById(id);
        } catch (UserNotFoundException e){
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(user);
    }

    // Register a new user
    @PostMapping
    public String registerUser(
            @ModelAttribute("user") @Valid User u,
            BindingResult res, WebRequest req, Errors errors){
        User registered = new User();
        if(!res.hasErrors()){
            try {
                registered = userService.registerNewUser(u);
            } catch (EmailExistsException e){
                e.printStackTrace();
                registered = null;
            }
        }
        if(registered==null){
            res.rejectValue("email", "message.regError");
        }
        return "User registered: " + registered.toString();

    }
}
