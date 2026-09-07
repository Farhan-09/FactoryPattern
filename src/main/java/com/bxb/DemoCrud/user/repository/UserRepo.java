package com.bxb.DemoCrud.user.repository;

import com.bxb.DemoCrud.user.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User , Long> {

}
