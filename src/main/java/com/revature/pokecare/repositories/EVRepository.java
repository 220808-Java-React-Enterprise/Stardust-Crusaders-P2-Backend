package com.revature.pokecare.repositories;

import com.revature.pokecare.models.EVs;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EVRepository extends CrudRepository<EVs, String> {
}
