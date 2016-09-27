package ru.spb.iac.storager.server.data.users;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByLogin(String login);
}
