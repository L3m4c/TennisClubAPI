package services;

import dto.UserDto;
import entity.User.User;
import entity.User.UserRepository;

import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Configurable;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Configurable("UserService")
public class UserService {

    @Resource
    UserRepository userRepository;

    public UserDto create(UserDto userDto) {
        User user = new User(userDto);
        user.setPassword(generatePassword());
        userRepository.save(user);
        return new UserDto(user);
    }

    private String generatePassword() {
        return "password";
    }

    public UserDto update(UserDto userDto) {
        User user = userRepository.findOne(userDto.getId());
        userRepository.save(user);
        return new UserDto(user);
    }

    public void delete(long id) {
        userRepository.delete(id);
    }

    public User select(long id) {
        return userRepository.findOne(id);
    }

    public List<UserDto> selectAll() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .map(u -> new UserDto(u))
                .collect(Collectors.toList());
    }

    public List<UserDto> selectAll(List<Long> ids) {
        return StreamSupport.stream(userRepository.findAll(ids).spliterator(), false)
                .map(u -> new UserDto(u))
                .collect(Collectors.toList());
    }
}
