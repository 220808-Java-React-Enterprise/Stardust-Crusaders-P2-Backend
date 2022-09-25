package com.revature.pokecare.services;

import com.revature.pokecare.dtos.requests.NewPokemonRequest;
import com.revature.pokecare.dtos.requests.PokemonIDRequest;
import com.revature.pokecare.dtos.requests.SetEVRequest;
import com.revature.pokecare.dtos.responses.ViewPokemon;
import com.revature.pokecare.models.EVs;
import com.revature.pokecare.models.IVs;
import com.revature.pokecare.models.MoveSet;
import com.revature.pokecare.models.Pokemon;
import com.revature.pokecare.repositories.*;
import com.revature.pokecare.utils.custom_exceptions.InvalidRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.*;

@Service
public class PokemonService {

    @Autowired
    private final PokemonRepository pokemonRepository;
    private final EVRepository evRepository;
    private final IVRepository ivRepository;
    private final MoveSetRepository moveSetRepository;
    private final UserRepository userRepository;

    public PokemonService(PokemonRepository pokemonRepository, EVRepository evRepository, IVRepository ivRepository, MoveSetRepository moveSetRepository, UserRepository userRepository) {
        this.pokemonRepository = pokemonRepository;
        this.evRepository = evRepository;
        this.ivRepository = ivRepository;
        this.moveSetRepository = moveSetRepository;
        this.userRepository = userRepository;
    }

    public ViewPokemon findByID(String id) {
        String s = pokemonRepository.findByID(id);
        String[] arr = s.split(",");
        try {
            return new ViewPokemon(arr[0], arr[1], arr[2], Integer.parseInt(arr[3]), arr[4], arr[5], Integer.parseInt(arr[6]), Integer.parseInt(arr[7]), arr[8], arr[9], arr[10], arr[11]);
        } catch (InputMismatchException e) {
            e.getStackTrace();
            throw new InputMismatchException();
        }
    }

    public List<String> findByUserID(String user_id) {
        return pokemonRepository.findByUserID(user_id);
    }

    public List<ViewPokemon> getByUserId(String user_id) {
        List<ViewPokemon> result = new ArrayList<>();
        List<String> mons = pokemonRepository.findByUserID(user_id);
        System.out.println(mons);
        for (String s : mons) {
            String[] arr = s.split(",");
            try {
                ViewPokemon mon = new ViewPokemon(arr[0], arr[1], arr[2], Integer.parseInt(arr[3]), arr[4], arr[5], Integer.parseInt(arr[6]), Integer.parseInt(arr[7]), arr[8], arr[9], arr[10], arr[11]);
                result.add(mon);
                System.out.println(mon);
            } catch (InputMismatchException e) {
                e.getStackTrace();
                throw new InputMismatchException();
            }
        }
        System.out.println(result);
        return result;
    }


    public Pokemon save(NewPokemonRequest newPokemonRequest) {
        int level = 1;
        int xp_needed = 0;
        Pokemon pokemon = new Pokemon(UUID.randomUUID().toString(), newPokemonRequest.getName(), newPokemonRequest.getPokedex_id(), level, xp_needed, newPokemonRequest.getAbility(), newPokemonRequest.getNature(), null, new EVs(UUID.randomUUID().toString()), new IVs(UUID.randomUUID().toString()), new MoveSet(UUID.randomUUID().toString()), userRepository.findById(newPokemonRequest.getUser_id()).get());
        evRepository.save(pokemon.getEvs());
        ivRepository.save(pokemon.getIvs());
        moveSetRepository.save(pokemon.getMoveset());
        pokemonRepository.save(pokemon);
        return pokemon;
    }

    public void enrollPoke(String pokemon_id) {
        Calendar cal = Calendar.getInstance();
        Timestamp timestamp = new Timestamp(cal.getTimeInMillis());
        pokemonRepository.enrollPoke(timestamp, pokemon_id);
    }

    public void removeFromDaycare(String pokemon_id) {
        pokemonRepository.enrollPoke(null, pokemon_id);
    }

    public int getHP(String id) {
        /*int hpIV = 0;
        int hpEV = evRepository.getEV();
        int level = pokemonRepository.findLevel(id);

        int base = 1;
        int hp = ((2*base + hpIV + (hpEV/4)*level)/100) + level + 10;
        return hp; */
        return 0;
    }

    public void updateXP(String id) {
        Timestamp lastLogin = userRepository.getLastLogin(id);
        Calendar cal = Calendar.getInstance();
        Timestamp currentTime = new Timestamp(cal.getTimeInMillis());
        long diff = (currentTime.getTime() - lastLogin.getTime())/1000/60;
        int xpToAdd = (int) diff * 10;
        String[] pokeIDs = pokemonRepository.getIDs(id);
        for (String p : pokeIDs) {
            if (pokemonRepository.getDateEnrolled(p)!=null) {
                int xp = pokemonRepository.getXP(p);
                int totalXP = xp + xpToAdd;
                if (totalXP/100>0) {
                    int level = pokemonRepository.findLevel(p);
                    int newLevel = level + (totalXP/100);
                    pokemonRepository.setNewLevel(p, Math.min(newLevel, 100));
                }
                int newXP = totalXP%100;
                pokemonRepository.setNewXP(p, newXP);
            }
        }
        userRepository.updateLastLogin(currentTime, id);
    }

    public String createEgg(String p1, String p2, String user_id) {
        Integer[] levels = pokemonRepository.pokeLevelsFromUserID(user_id);
        if (!Arrays.asList(levels).contains(0)) {
            if (pokemonRepository.getDateEnrolled(p1)!=null && pokemonRepository.getDateEnrolled(p2)!=null) {
                final Random random = new Random();
                final int millisInDay = 60 * 1000 * (random.nextInt(1) + 1);
                TimerTask task = new TimerTask() {
                    public void run() {
                        Pokemon pokemon = new Pokemon(UUID.randomUUID().toString(), pokemonRepository.getName(p1), Integer.parseInt(pokemonRepository.getPokedexID(p1)), 0, 0, pokemonRepository.getAbility(p2), pokemonRepository.getNature(p2), null, new EVs(UUID.randomUUID().toString()), new IVs(UUID.randomUUID().toString()), new MoveSet(UUID.randomUUID().toString()), userRepository.findById(user_id).get());
                        evRepository.save(pokemon.getEvs());
                        ivRepository.save(pokemon.getIvs());
                        moveSetRepository.save(pokemon.getMoveset());
                        pokemonRepository.save(pokemon);
                    }
                };
                Timer timer = new Timer("Timer");
                timer.schedule(task, millisInDay);
                return "Egg will be created within two hours!";
            } else {
                return "Both pokemons need to be enrolled in the daycare to create an egg!";
            }
        } else {
            return "User can only have one unhatched egg at a time!";
        }
    }

    public String hatchEgg(String id) {
        if (pokemonRepository.findLevel(id)!=0) {
            return "This pokemon is not in an egg!";
        } else {
            pokemonRepository.setNewLevel(id, 1);
            return "Egg hatched successfully!";
        }
    }

}