package com.petproject.todoappbackend.Repository;

import com.petproject.todoappbackend.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByUserName(String userName);

    Boolean existsByUserNameAndPassword(String userName, String password);
}
