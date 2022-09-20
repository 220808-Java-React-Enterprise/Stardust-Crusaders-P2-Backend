package com.revature.pokecare.repositories;

import com.revature.pokecare.models.MoveSet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoveSetRepository extends CrudRepository<MoveSet, String> {
}
