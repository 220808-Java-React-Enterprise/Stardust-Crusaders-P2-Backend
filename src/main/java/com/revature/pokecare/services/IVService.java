package com.revature.pokecare.services;

import com.revature.pokecare.models.IVs;
import com.revature.pokecare.repositories.IVRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IVService {

    @Autowired
    private final IVRepository ivRepository;

    public IVService(IVRepository ivRepository) {
        this.ivRepository = ivRepository;
    }

    public void saveNewIV(IVs ivs) {
        ivRepository.save(ivs);
    }
}
