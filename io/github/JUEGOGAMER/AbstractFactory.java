package io.github.JUEGOGAMER;

// La interfaz AbstractFactory define métodos para crear objetos de diferentes tipos
public interface AbstractFactory {
    Objetos crearGengar(float x, float y);
    Objetos crearPokeball(float x, float y);
    Objetos crearJigglypuff(float x, float y);
}

