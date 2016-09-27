package ru.spb.iac.storager.server.data.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ru.spb.iac.storager.server.SecurityContext;

@RestController
@RequestMapping("/data/users")
public class UserController {

    @Autowired
    private SecurityContext securityContext;

    @Autowired
    private UserService userService;

    @RequestMapping(path = "/current", method = RequestMethod.GET)
    public UserInfo getCurrent() {
        securityContext.userAuthorizedWithAnyRole();
        return userService.getByLogin("guest");
    }
}
