package chess.service.user;

import chess.repository.user.UserDto;
import chess.repository.user.UserRepositoryImpl;
import java.util.Optional;

public class LoginService {

    private final UserRepositoryImpl userRepository;

    public LoginService(UserRepositoryImpl userRepository) {
        this.userRepository = userRepository;
    }

    public int login(String userName) {
        Optional<UserDto> user = userRepository.findByUserName(userName);
        return user.map(UserDto::getUserId)
                .orElseGet(() -> userRepository.saveUser(userName));
    }
}
