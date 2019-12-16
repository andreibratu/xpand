package com.dei.xpand.security.repository;

import com.dei.xpand.security.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>
{
    User findUserByUsername(String username);
}
