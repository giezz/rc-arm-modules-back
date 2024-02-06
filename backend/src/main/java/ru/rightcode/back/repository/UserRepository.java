package ru.rightcode.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import ru.rightcode.back.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @NonNull
    @Override
    @Query("select u from User u join fetch u.roles")
    List<User> findAll();

    @Query("select u from User u join fetch u.roles where u.username=:username")
    Optional<User> findByUsername(@Param("username") String username);
}
