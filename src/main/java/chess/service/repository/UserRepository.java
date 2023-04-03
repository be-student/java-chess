package chess.service.repository;

import chess.repository.user.UserDto;
import java.util.Optional;

public interface UserRepository {

    Optional<UserDto> findByUserName(String userName);

    int saveUser(String userName);
}
