package com.bxb.DemoCrud.user.repository;

import com.bxb.DemoCrud.user.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User , Long> {

       Optional<User> findByEmail(String email);


       boolean existsByEmail(String email);



}
