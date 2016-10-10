package ru.spb.iac.storager.server.domain.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/data/users")
public class UserController {

    @Autowired
    private UserService service;

    @RequestMapping(path = "/authenticated", method = RequestMethod.GET)
    public UserInfo getAuthenticated() {
        return service.getAuthenticated();
    }
}
