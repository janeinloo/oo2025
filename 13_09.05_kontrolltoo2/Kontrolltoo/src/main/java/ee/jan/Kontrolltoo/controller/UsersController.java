package ee.jan.Kontrolltoo.controller;

import ee.jan.Kontrolltoo.entity.Users;
import ee.jan.Kontrolltoo.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController

public class UsersController {

    @Autowired
    UsersRepository usersRepository;

    @GetMapping("users")
    public Page<Users> getAllUsers(Pageable pageable) {
        return usersRepository.findAll(pageable);
    }

    @GetMapping("users/{id}")
    public Users getOneUser(@PathVariable Long id) {
        return usersRepository.findById(id).orElse(null);
    }

    @PostMapping("users")
    public Users addUser(@RequestBody Users users) {
        return usersRepository.save(users);
    }
}
