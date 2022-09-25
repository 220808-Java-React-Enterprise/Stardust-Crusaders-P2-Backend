package com.revature.pokecare.services;

import com.revature.pokecare.dtos.requests.NewPokemonRequest;
import com.revature.pokecare.models.Pokemon;
import com.revature.pokecare.repositories.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PokemonServiceTest {

    @Autowired
    private PokemonService pokeService;

    private final PokemonRepository mockPokeRepo = mock(PokemonRepository.class);
    private final EVRepository mockEVRepo = mock(EVRepository.class);
    private final IVRepository mockIVRepo = mock(IVRepository.class);
    private final MoveSetRepository mockMoveSetRepo = mock(MoveSetRepository.class);
    private final UserRepository mockUserRepo = mock(UserRepository.class);
    private Pokemon mockPokemon;
    private NewPokemonRequest mockRequest;

    @BeforeEach
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
        Pokemon pokemon = pokeService.save(mockRequest);
        verify(mockPokeRepo, times(1)).save(pokemon);
        Assert.assertNotNull(pokemon);
    }
}
