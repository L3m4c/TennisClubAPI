package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.LoginService;

import java.util.Optional;

@RestController
public class LoginController {

    @Autowired
    LoginService loginService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<String> login(@RequestBody EmailPassword emailPassword) throws JsonProcessingException {
        Optional<UserDto> userDto = loginService.findAnyUserWithEmailAndPasswordCombination(emailPassword.getEmail(), emailPassword.getPassword());
        if (userDto.isPresent()) {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String userDtoJson = ow.writeValueAsString(userDto.get());
            ResponseEntity<String> resp = new ResponseEntity<>(userDtoJson, HttpStatus.ACCEPTED);
            return resp;
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}

class EmailPassword {
    private String email;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}