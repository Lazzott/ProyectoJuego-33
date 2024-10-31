package io.github.JUEGOGAMER;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class Entrenador {
    private Rectangle bucket;
    private Texture bucketImage;
    private Sound sonidoHerido;
    private int vidas = 5;
    private static final int MAX_VIDAS = 5;
    private int puntos = 0;
    private int vely = 400; // Velocidad vertical
    private boolean herido = false;
    private int tiempoHeridoMax = 50;
    private int tiempoHerido;

    public Entrenador(Texture tex, Sound ss) {
        bucketImage = tex;
        sonidoHerido = ss;
    }

    public int getVidas() {
        return vidas;
    }

    public int getPuntos() {
        return puntos;
    }

    public Rectangle getArea() {
        return bucket;
    }

    public void sumarPuntos(int pp) {
        puntos += pp;
    }

    public void crear() {
        bucket = new Rectangle();
        bucket.x = 20; // Coloca el tarro en la parte izquierda de la pantalla
        bucket.y = 240; // Centrado verticalmente
        bucket.width = 64;
        bucket.height = 64;
    }

    public void dañar() {
        vidas--;
        herido = true;
        tiempoHerido = tiempoHeridoMax;
        sonidoHerido.play();
    }

    public void dibujar(SpriteBatch batch) {
        if (!herido)
            batch.draw(bucketImage, bucket.x, bucket.y);
        else {
            batch.draw(bucketImage, bucket.x, bucket.y + MathUtils.random(-5, 5));
            tiempoHerido--;
            if (tiempoHerido <= 0) herido = false;
        }
    }

    public void actualizarMovimiento() {
        // Movimiento solo vertical
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) bucket.y += vely * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) bucket.y -= vely * Gdx.graphics.getDeltaTime();

        // Limitar el movimiento para que no se salga de los bordes superior e inferior
        if (bucket.y < 0) bucket.y = 0;
        if (bucket.y > 480 - 64) bucket.y = 480 - 64; // Supone que la altura de la pantalla es 480
    }

    public void destruir() {
        bucketImage.dispose();
    }

    public boolean estaHerido() {
        return herido;
    }

    public void sumarVida() {
        if (vidas < MAX_VIDAS) { // Solo suma vida si está por debajo del máximo
            vidas++;
        }
    }
}

