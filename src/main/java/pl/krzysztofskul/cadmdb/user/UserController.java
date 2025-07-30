package pl.krzysztofskul.cadmdb.user;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public AppUser register(@RequestParam String username, @RequestParam String password) {
        return userService.register(username, password);
    }
}
