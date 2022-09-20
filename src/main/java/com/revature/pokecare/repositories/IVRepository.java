package com.revature.pokecare.repositories;

import com.revature.pokecare.models.IVs;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVRepository extends CrudRepository<IVs, String> {
}
