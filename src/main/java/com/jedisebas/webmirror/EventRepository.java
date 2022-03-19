package com.jedisebas.webmirror;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    @Query(value = "SELECT * FROM event WHERE userid=?1 ORDER BY date ASC", nativeQuery = true)
    ArrayList<Event> findEventsByUserId(Long userid);
}
