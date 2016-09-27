package ru.spb.iac.storager.server.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/security")
public class SecurityController {

    @Autowired
    private SecurityContext securityContext;

    @Autowired
    private SecurityService securityService;

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public UserToken logon(@RequestBody UserCredentials credentials) {
        return securityService.logon(credentials);
    }

    @RequestMapping(path = "/logout", method = RequestMethod.POST)
    public void logout() {
        AuthorizedUser user = securityContext.userAuthorizedWithAnyRole();
        securityService.logout(user.getToken().getId());
    }
}
