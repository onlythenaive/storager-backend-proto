package ru.spb.iac.storager.server.domain.users;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByLogin(String login);
}
