package bog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by zac on 02.04.17.
 */

@RestController
@RequestMapping(value = "/api")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity registration(@RequestBody UserModel body) {
        final IdResponse idResponse = userService.registration(body);
        if (idResponse != null) {
            return ResponseEntity.ok(idResponse);
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("{}");
    }

    @RequestMapping(value = "/data", method = RequestMethod.POST)
    public ResponseEntity setData(@RequestBody UserModel body) {
        if (userService.setData(body)) {
            return ResponseEntity.ok("{}");
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("{}");
    }

    @RequestMapping(value = "/data", method = RequestMethod.GET)
    public ResponseEntity getData(@RequestParam(value = "username") String username,
                                  @RequestParam(value = "password") String password) {
        final DataModel data = userService.getData(username, password);
        if (data != null) {
            return ResponseEntity.ok(data);
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("{}");
    }
}
