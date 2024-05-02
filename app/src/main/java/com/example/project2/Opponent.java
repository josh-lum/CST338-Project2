package com.example.project2;

import java.util.LinkedList;
import java.util.Queue;

public class Opponent {
    // queue of mons for now
    private Queue<Pokemon> pokemonQueue;

    public Opponent(){
        pokemonQueue = new LinkedList<>();
        pokemonQueue.offer(new Pokemon("Bulbasaur", "Grass", 10, 2));
        pokemonQueue.offer(new Pokemon("Squirtle", "Water", 10, 2));
        pokemonQueue.offer(new Pokemon("Charmander", "Fire", 10, 2));
        pokemonQueue.offer(new Pokemon("Chikorita", "Grass", 10, 2));
        pokemonQueue.offer(new Pokemon("Totodile", "Water", 10, 2));
        pokemonQueue.offer(new Pokemon("Chimchar", "Fire", 10, 2));
        pokemonQueue.offer(new Pokemon("Treecko", "Grass", 10, 2));
        pokemonQueue.offer(new Pokemon("Mudkip", "Water", 10, 2));
        pokemonQueue.offer(new Pokemon("Torchic", "Fire", 10, 2));
    }

    // look at next mon
    public Pokemon getNextPokemon(){
        return pokemonQueue.peek();
    }

    // remove defeated mon
    public void switchToNextPokemon(){
        pokemonQueue.poll();
    }

    public boolean hasMorePokemon(){
        return !pokemonQueue.isEmpty();
    }
}
