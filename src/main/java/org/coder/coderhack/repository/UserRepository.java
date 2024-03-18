package org.coder.coderhack.repository;

import org.coder.coderhack.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
