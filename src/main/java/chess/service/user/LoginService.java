package chess.service.user;

import chess.repository.user.UserDto;
import chess.service.repository.UserRepository;
import java.util.Optional;

public class LoginService {

    private final UserRepository userRepository;

    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public int login(String userName) {
        Optional<UserDto> user = userRepository.findByUserName(userName);
        return user.map(UserDto::getUserId)
                .orElseGet(() -> userRepository.saveUser(userName));
    }
}
