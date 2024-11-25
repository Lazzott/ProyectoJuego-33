package io.github.JUEGOGAMER;

// Fábrica concreta que implementa los métodos para crear los objetos
public class FactoryPokemon implements AbstractFactory {
    @Override
    public Objetos crearGengar(float x, float y) {
        return new Gengar(x, y); // Crea una instancia de Gengar
    }

    @Override
    public Objetos crearPokeball(float x, float y) {
        return new Pokeball(x, y); // Crea una instancia de Pokeball
    }

    @Override
    public Objetos crearJigglypuff(float x, float y) {
        return new Jigglypuff(x, y); // Crea una instancia de Jigglypuff
    }
}



