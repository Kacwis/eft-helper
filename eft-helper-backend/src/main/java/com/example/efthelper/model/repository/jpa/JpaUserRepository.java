package com.example.efthelper.model.repository.jpa;

import com.example.efthelper.model.User;
import com.example.efthelper.model.repository.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface JpaUserRepository extends JpaRepository<User, Long>, UserRepository {

}
