package com.example.TibaCare.Controller;

import com.example.TibaCare.users.Users;
import com.example.TibaCare.users.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/user")
public class UsersController {
    private final UsersService usersService;
    @Autowired

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping
    public List<Users> getUser(){
        return usersService.getuser();
    }

    @PostMapping(path = "addUser")
    public void registerNewUser(@RequestBody Users users){
        usersService.addNewUser(users);
    }

    @DeleteMapping(path = "{userId}")
    public void deletUser(@PathVariable("userId") Long userId){
        usersService.deleteuser(userId);
    }
    @PutMapping(path = "{userId}")
    public void updateUser(
            @PathVariable("userId") Long userId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email){
        usersService.updateUser(userId,name,email);
    }
}
