package com.adincuff.breedingregister.Horse;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HorseRepository extends MongoRepository<Horse, String> {
    List<Horse> findByUserId(String id);
}
