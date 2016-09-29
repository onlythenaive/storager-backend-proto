package ru.spb.iac.storager.server.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/security")
public class SecurityController {

    @Autowired
    private SecurityContext securityContext;

    @Autowired
    private SecurityService securityService;

    @RequestMapping(path = "/logon", method = RequestMethod.POST)
    public UserAuthenticationInfo logon(@RequestBody UserCredentialData credentials) {
        return securityService.authenticateByCredentials(credentials).toAuthenticationInfo();
    }

    @RequestMapping(path = "/logout", method = RequestMethod.POST)
    public void logout(@RequestParam(name = "all", defaultValue = "false") boolean all) {
        final UserAuthentication authentication = securityContext.getUserAuthentication();
        if (authentication != null) {
            if (all) {
                securityService.deauthenticateByLogin(authentication.getLogin());
            } else {
                if (authentication.hasToken()) {
                    securityService.deauthenticateByToken(authentication.getToken().getId());
                }
            }
        }
    }
}
