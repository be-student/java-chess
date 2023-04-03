package chess.repository.user;

import java.util.Optional;

public interface UserRepository {

    Optional<UserDto> findByUserName(String userName);

    int saveUser(String userName);
}
