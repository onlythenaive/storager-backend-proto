package ru.spb.iac.storager.server.domain.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.ImmutableList;

@Component
@Transactional
@Profile("dev.local")
public class UserBootstrap {

    @Autowired
    private UserRepository userRepository;

    public void run() {
        userRepository.save(ImmutableList.of(
                new User("guest", "guest", "guest@storager.iac.spb.ru", "Гостевой доступ", true, true, "GUEST"),
                new User("user", "user123", "user@storager.iac.spb.ru", "Юзеров Ю.Ю.", true, false, "USER"),
                new User("admin", "admin123", "admin@storager.iac.spb.ru", "Админов А.А.", true, false, "USER ADMIN")
        ));
    }
}
