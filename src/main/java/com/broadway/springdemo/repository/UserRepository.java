package com.broadway.springdemo.repository;

import com.broadway.springdemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {

User findByUsernameAndPassword(String un, String psw);

//@Query(value = "select * from user where username=: un and password= :psw", nativeQuery = true)
//User userLogin(@Param("un") String un, @Param("psw") String psw);

}
