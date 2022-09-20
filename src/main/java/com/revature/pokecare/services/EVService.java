package com.revature.pokecare.services;

import com.revature.pokecare.models.EVs;
import com.revature.pokecare.repositories.EVRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EVService {

    @Autowired
    private final EVRepository evRepository;

    public EVService(EVRepository evRepository) {
        this.evRepository = evRepository;
    }

    public void saveNewEV(EVs evs) {
        evRepository.save(evs);
    }
}
