package ru.spb.iac.storager.server.data.users;

import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserBootstrap {

    @Autowired
    private UserRepository userRepository;

    public void run() {
        userRepository.save(ImmutableList.of(
                new User("guest", "guest@storager.iac.spb.ru", "Гостевой доступ", true, true, "GUEST"),
                new User("user", "user@storager.iac.spb.ru", "Юзеров Ю.Ю.", true, false, "USER"),
                new User("admin", "admin@storager.iac.spb.ru", "Админов А.А.", true, false, "USER ADMIN"),
                new User("root", "root@storager.iac.spb.ru", "Доступ корневого уровня", true, true, "ROOT")
        ));
    }
}
