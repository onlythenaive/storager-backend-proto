package ru.spb.iac.storager.server.domain.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.ImmutableSet;

import static ru.spb.iac.storager.server.security.SecurityRoles.ADMIN;
import static ru.spb.iac.storager.server.security.SecurityRoles.GUEST;
import static ru.spb.iac.storager.server.security.SecurityRoles.USER;

@Component
@Profile("dev.local")
public class UserBootstrap {

    @Autowired
    private UserService service;

    @Transactional
    public void run() {
        service.bootstrap(guest(true, true));
        service.bootstrap(properties("user", "Юзеров Ю.Ю.", true, USER));
        service.bootstrap(properties("admin", "Админов А.А.", true, USER, ADMIN));
    }

    private UserProperties properties(final String login,
                                      final String fullname,
                                      final boolean enabled,
                                      final String... roles) {
        final UserProperties properties = new UserProperties();
        properties.setLogin(login);
        properties.setSecret(login + "123");
        properties.setEmail(login + "@storager.iac.spb.ru");
        properties.setFullname(fullname);
        properties.setEnabled(enabled);
        properties.setRoles(ImmutableSet.copyOf(roles));
        return properties;
    }

    private UserProperties guest(final boolean enabled,
                                 final boolean root) {
        final UserProperties properties = new UserProperties();
        properties.setLogin("guest");
        properties.setSecret("*");
        properties.setEmail("support@storager.iac.spb.ru");
        properties.setFullname("Гостевой доступ");
        properties.setEnabled(enabled);
        properties.setRoot(root);
        properties.setRoles(ImmutableSet.of(GUEST));
        return properties;
    }
}
