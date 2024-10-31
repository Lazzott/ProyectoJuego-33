package io.github.JUEGOGAMER;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Gengar extends Objetos {
    private static final Texture textura = new Texture("gastly64.png");

    public Gengar(float x, float y) {
        super(x, y);
    }

    @Override
    public void aplicarEfecto(Entrenador tarro) {
        tarro.dañar();
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
