package services;

import Dto.UserDto;
import Entity.User.UserRepository;

import javax.annotation.Resource;
import java.util.Optional;

public class LoginService {

    @Resource
    UserRepository userRepository;

    public Optional<UserDto> findAnyUserWithEmailAndPasswordCombination(String email, String password) {
        return userRepository.findUserByEmailAndPassword(email, password).stream().findAny().map(u -> new UserDto(u));
    }

}
