package com.example.project2;

import java.util.LinkedList;
import java.util.Queue;

public class Opponent{
    // queue of mons for now
    public Queue<Pokemon> pokemonQueue;
    public int currentMonIndex;

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
        currentMonIndex = 0;
    }

    // look at next mon
    public Pokemon getNextPokemon(){
        return pokemonQueue.peek();
    }

    // remove defeated mon
    public void switchToNextPokemon(){
        pokemonQueue.poll();
        currentMonIndex++;
    }

    public boolean hasMorePokemon(){
        return !pokemonQueue.isEmpty();
    }

    public int getCurrentMonIndex(){
        return currentMonIndex;
    }

    public Queue<Pokemon> getPokemonQueue() {
        return pokemonQueue;
    }

    public String getCurrentPokemonName() {
        if(pokemonQueue.peek() != null) {
            return pokemonQueue.peek().getName();
        }else{
            return null;
        }
    }

    public int getCurrentPokemonSpriteResourceId() {
        return pokemonQueue.peek().getSpriteResourceId();
    }
}
