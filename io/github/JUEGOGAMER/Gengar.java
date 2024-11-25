package io.github.JUEGOGAMER;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Gengar extends Objetos implements EfectoColision {
    private static final Texture textura = new Texture("gastly64.png");

    public Gengar(float x, float y) {
        super(x, y);
    }

    // Implementación del metodo aplicarEfecto de la interfaz EfectoColision
    @Override
    public void aplicarEfecto(Entrenador tarro) {
        tarro.dañar(); // Reduce la vida del entrenador cuando colisiona con un Gengar
    }

    @Override
    public void actualizar(float deltaTime) {
        posicion.x -= 200 * deltaTime; // Mueve el objeto a la izquierda
    }

    @Override
    public void dibujar(SpriteBatch batch) {
        batch.draw(textura, posicion.x, posicion.y); // Dibuja el objeto en la pantalla
    }
}

