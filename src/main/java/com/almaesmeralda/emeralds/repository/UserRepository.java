package com.almaesmeralda.emeralds.repository;

import com.almaesmeralda.emeralds.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}