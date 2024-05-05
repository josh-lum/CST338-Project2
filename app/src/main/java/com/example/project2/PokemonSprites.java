package com.example.project2;

import java.util.HashMap;
import java.util.Map;

public class PokemonSprites {
    private static final Map<String, Integer> spriteResourceIds = new HashMap<>();

    // Method to add sprite resource ID for a Pokemon
    public static void addSpriteResource(String pokemonName, int resourceId) {
        spriteResourceIds.put(pokemonName.toLowerCase(), resourceId);
    }

    // Method to get sprite resource ID for a Pokemon and check for null
    public static int getSpriteResourceId(String pokemonName) {
        Integer resourceId = spriteResourceIds.get(pokemonName.toLowerCase());
        return resourceId != null ? resourceId : 0;
    }

    public static void opponentSprites(Opponent opponent){
        for(Pokemon mon : opponent.getPokemonQueue()){
            addSpriteResource(opponent.getCurrentPokemonName(), opponent.getCurrentPokemonSpriteResourceId());
        }
    }
}
