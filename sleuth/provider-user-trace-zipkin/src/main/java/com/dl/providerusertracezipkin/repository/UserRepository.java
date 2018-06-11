package com.dl.providerusertracezipkin.repository;

import com.dl.providerusertracezipkin.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
