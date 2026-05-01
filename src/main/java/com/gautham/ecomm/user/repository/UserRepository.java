package com.gautham.ecomm.user.repository;

import com.gautham.ecomm.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}