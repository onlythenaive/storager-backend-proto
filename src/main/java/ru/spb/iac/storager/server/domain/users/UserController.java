package ru.spb.iac.storager.server.domain.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ru.spb.iac.storager.server.security.SecurityContext;
import ru.spb.iac.storager.server.security.UserAuthentication;

@RestController
@RequestMapping("/data/users")
public class UserController {

    @Autowired
    private SecurityContext securityContext;

    @Autowired
    private UserService userService;

    @RequestMapping(path = "/current", method = RequestMethod.GET)
    public UserInfo getCurrent() {
        final UserAuthentication authentication = securityContext.userAuthenticated();
        return userService.getByLogin(authentication.getLogin());
    }
}
