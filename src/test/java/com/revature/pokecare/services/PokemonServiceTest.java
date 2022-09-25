package com.revature.pokecare.services;

import com.revature.pokecare.dtos.requests.NewPokemonRequest;
import com.revature.pokecare.dtos.responses.ViewPokemon;
import com.revature.pokecare.models.Pokemon;
import com.revature.pokecare.repositories.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class PokemonServiceTest {

    private PokemonService pokeService;

    private final PokemonRepository mockPokeRepo = mock(PokemonRepository.class);
    private final EVRepository mockEVRepo = mock(EVRepository.class);
    private final IVRepository mockIVRepo = mock(IVRepository.class);
    private final MoveSetRepository mockMoveSetRepo = mock(MoveSetRepository.class);
    private final UserRepository mockUserRepo = mock(UserRepository.class);
    private Pokemon mockPokemon;
    private NewPokemonRequest mockRequest;

    @Before
    public void setup() {
        pokeService = new PokemonService(mockPokeRepo, mockEVRepo, mockIVRepo, mockMoveSetRepo, mockUserRepo);
        mockPokemon = mock(Pokemon.class);
        mockRequest = mock(NewPokemonRequest.class);
        when(mockRequest.getName()).thenReturn("Pikachu");
        when(mockRequest.getPokedex_id()).thenReturn(25);
        when(mockRequest.getAbility()).thenReturn("Static");
        when(mockRequest.getUser_id()).thenReturn("000000");
    }

    @Test
    public void test_findByID_givenValidRequest() {
        PokemonService spiedServ = Mockito.spy(pokeService);
        String validId = "000000";

        when(mockPokeRepo.findByID(validId)).thenReturn("000000,Static,null,10,Pikachu,Hardy,25,100,10101,20202,30303,40404");

        ViewPokemon test = spiedServ.findByID(validId);
        Assert.assertNotNull(test);

        verify(mockPokeRepo, times(1)).findByID(validId);
    }
}
