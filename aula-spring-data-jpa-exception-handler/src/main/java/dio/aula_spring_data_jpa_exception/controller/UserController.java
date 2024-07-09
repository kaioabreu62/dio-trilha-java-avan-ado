package dio.aula_spring_data_jpa_exception.controller;

import dio.aula_spring_data_jpa_exception.model.User;
import dio.aula_spring_data_jpa_exception.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository repository;
    @GetMapping
    public List<User> list(){
        return repository.findAll();
    }
    @GetMapping("/{id}")
    public User findId(@PathVariable("id") Integer id){
        return repository.findById(id);
    }
    @GetMapping("/username/{username}")
    public User findByUsername(@PathVariable("username") String username){
        return repository.findByUsername(username);
    }
    @PostMapping
    public void save(@RequestBody User user){
        repository.save(user);
    }
    @PutMapping
    public void update(@RequestBody User user){
        repository.save(user);
    }
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Integer id){
        repository.deleteById(id);
    }
    @DeleteMapping("/username/{username}")
    public void deleteByUsername(@PathVariable("username") String username){
        repository.deleteByUsername(username);
    }
}
