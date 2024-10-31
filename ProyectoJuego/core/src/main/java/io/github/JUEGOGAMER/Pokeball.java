package io.github.JUEGOGAMER;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Pokeball extends Objetos {
    private static final Texture textura = new Texture("pokeball64.png");

    public Pokeball(float x, float y) {
        super(x, y);
    }

    @Override
    public void aplicarEfecto(Entrenador tarro) {
        tarro.sumarPuntos(10);
    }

    @Override
    public void actualizar(float deltaTime) {
        posicion.x -= 200 * deltaTime;
    }

    @Override
    public void dibujar(SpriteBatch batch) {
        batch.draw(textura, posicion.x, posicion.y);
    }
}
