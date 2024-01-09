package com.routinise.repository;

import com.routinise.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    int countByPhone(String phone);
}
