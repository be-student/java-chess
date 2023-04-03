package chess.repository;

import chess.repository.user.UserDto;
import chess.service.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryUserRepository implements UserRepository {

    private final List<String> users = new ArrayList<>();

    @Override
    public Optional<UserDto> findByUserName(String userName) {
        int id = users.indexOf(userName);
        if (id == -1) {
            return Optional.empty();
        }
        return Optional.of(new UserDto(id));
    }

    @Override
    public int saveUser(String userName) {
        users.add(userName);
        return users.size() - 1;
    }
}
