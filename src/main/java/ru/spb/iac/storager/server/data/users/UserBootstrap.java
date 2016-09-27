package ru.spb.iac.storager.server.data.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.ImmutableList;

@Component
public class UserBootstrap {

    @Autowired
    private UserRepository userRepository;

    public void run() {
        userRepository.save(ImmutableList.of(
                User.of("guest", "guest", "guest@storager.iac.spb.ru", "Гостевой доступ", true, false, "GUEST"),
                User.of("user", "user123", "user@storager.iac.spb.ru", "Юзеров Ю.Ю.", true, false, "USER"),
                User.of("admin", "admin123", "admin@storager.iac.spb.ru", "Админов А.А.", true, false, "USER ADMIN")
        ));
    }
}
