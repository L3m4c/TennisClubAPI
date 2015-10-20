package Controllers;

import Dto.UserDto;
import Services.LoginService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
public class LoginController {

    @Autowired
    LoginService loginService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<String> login(@RequestParam(value = "email", required = true) String email,
                         @RequestParam(value = "password", required = true) String password) throws JsonProcessingException {
        Optional<UserDto> userDto = loginService.findAnyUserWithEmailAndPasswordCombination(email, password);
        if(userDto.isPresent()) {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String userDtoJson = ow.writeValueAsString(userDto.get());
            ResponseEntity<String> resp = new ResponseEntity<>(userDtoJson, HttpStatus.ACCEPTED);
            return resp;
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
