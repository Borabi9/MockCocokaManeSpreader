package com.example.mock_cocoka_mane_spreader.repository;

import com.example.mock_cocoka_mane_spreader.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
