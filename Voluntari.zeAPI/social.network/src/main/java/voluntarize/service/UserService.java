package voluntarize.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import voluntarize.entity.User;
import voluntarize.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository _userRepository;

    public User create(User user){
        _userRepository.save(user);
        return user;
    }
}
