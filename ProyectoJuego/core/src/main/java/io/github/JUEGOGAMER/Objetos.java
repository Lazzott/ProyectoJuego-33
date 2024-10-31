package io.github.JUEGOGAMER;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class Objetos {
    protected Rectangle posicion;

    public Objetos(float x, float y) {
        posicion = new Rectangle(x, y, 64, 64); // Tamaño por defecto
    }

    public Rectangle getPosicion() {
        return posicion;
    }

    public abstract void aplicarEfecto(Entrenador tarro); // Aplicar el efecto del objeto al colisionar
    public abstract void actualizar(float deltaTime); // Actualizar la posición del objeto
    public abstract void dibujar(SpriteBatch batch); // Dibujar el objeto en pantalla
}

