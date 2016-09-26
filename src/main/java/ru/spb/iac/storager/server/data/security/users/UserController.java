package ru.spb.iac.storager.server.data.security.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/data/security/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public List<UserInfo> getAll() {
        return userService.getAll();
    }

    @RequestMapping(path = "/current", method = RequestMethod.GET)
    public UserInfo getCurrent() {
        return userService.getByLogin("guest");
    }
}
