package com.example.TibaCare.users;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UsersService {
    private final UsersRepository usersRepository;

    @Autowired

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<Users> getuser(){
        return usersRepository.findAll();
    }

    public void addNewUser(Users users) {
        Optional<Users> usersOptional = usersRepository.findUsersByEmail(users.getEmail());
        if(usersOptional.isPresent()){
            throw new IllegalStateException("email taken");
        }
        Optional<Users> usersOptional1 = usersRepository.findUsersBynational_identity_card(users.getNational_identity_card());
        if (usersOptional.isPresent()){
            throw new IllegalStateException("national_identity_card taken");
        }
        usersRepository.save(users);
    }

    public void deleteuser(Long userId) {
      boolean exists =  usersRepository.existsById(userId);
    if (!exists){
        throw new IllegalStateException("user with id " + userId + "does not exists");
    }
    usersRepository.deleteById(userId);
    }
    @Transactional
    public void updateUser(Long userId , String name , String email){
        Users users = usersRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("User with id " + userId +
                        "does not exists"));
        if (name != null && name.length() > 0 && !Objects.equals(users.getName(),name)){
            users.setName(name);
        }
        if (email != null && email.length() > 0 && !Objects.equals(users.getEmail(),email)){
            Optional<Users> usersOptional = usersRepository.findUsersByEmail(email);
            if (usersOptional.isPresent()){
                throw new IllegalStateException("email taken");
            }
            users.setEmail(email);
        }
    }
}
