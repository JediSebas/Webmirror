package com.jedisebas.webmirror;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.email = ?1")
    Optional<User> findUserByEmail(String email);

    @Query("SELECT password from User u WHERE u.email = ?1")
    String findPasswordByEmail(String email);

    @Query("SELECT name from User u WHERE u.email = ?1")
    String findNameByEmail(String email);

    @Query("SELECT lastname from User u WHERE u.name = ?1")
    String findLastnameByName(String name);

    @Query(value = "SELECT name FROM pictures WHERE userid = ?1", nativeQuery = true)
    ArrayList<String> findPicturesByUserId(Long userId);
}
