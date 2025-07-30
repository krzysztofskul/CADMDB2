package pl.krzysztofskul.cadmdb.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AppUserGenerator appUserGenerator;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, AppUserGenerator appUserGenerator) {
        this.appUserGenerator = appUserGenerator;
    	this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AppUser register(String username, String rawPassword) {
        AppUser user = new AppUser();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(rawPassword));
        return userRepository.save(user);
    }

    public void createAndSaveTestUsers() {
    	for (AppUser appUser: appUserGenerator.initListAndReturn()) {
			userRepository.save(appUser);
		}
    }

}
