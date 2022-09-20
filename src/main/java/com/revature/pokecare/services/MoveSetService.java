package com.revature.pokecare.services;

import com.revature.pokecare.models.MoveSet;
import com.revature.pokecare.repositories.MoveSetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MoveSetService {
    @Autowired
    private final MoveSetRepository moveSetRepository;

    public MoveSetService(MoveSetRepository moveSetRepository) {
        this.moveSetRepository = moveSetRepository;
    }

    public void saveMoveSet(MoveSet moveSet) {
        moveSetRepository.save(moveSet);
    }
}
