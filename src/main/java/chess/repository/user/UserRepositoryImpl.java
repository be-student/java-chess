package chess.repository.user;

import chess.service.repository.UserRepository;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {

    private final UserDao userDao;

    public UserRepositoryImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Optional<UserDto> findByUserName(String userName) {
        return userDao.findById(userName);
    }

    @Override
    public int saveUser(String userName) {
        return userDao.save(userName);
    }
}
