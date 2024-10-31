package io.github.JUEGOGAMER;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class Lluvia {
    private Array<Objetos> objetosLluvia;
    private Sound dropSound;
    private Music rainMusic;
    private static final int MAX_OBJETOS = 5; // Límite de objetos en pantalla
    private static final float VELOCIDAD = 300; // Velocidad de movimiento de los objetos

    public Lluvia(Texture gota, Texture gotaMala, Sound ss, Music mm) {
        rainMusic = mm;
        dropSound = ss;
    }

    public void crear() {
        objetosLluvia = new Array<>();
        rainMusic.setLooping(true);
        rainMusic.play();
    }

    private void crearGotaDeLluvia() {
        // Verifica si se puede generar un nuevo objeto
        if (objetosLluvia.size >= MAX_OBJETOS) return; // Si ya hay 5 objetos, no crear más

        float x, y;
        boolean superposicion;

        do {
            x = MathUtils.random(800, 1600);
            y = MathUtils.random(0, 480 - 64);
            superposicion = false;

            // Verificar si el nuevo objeto choca con alguno existente
            for (Objetos objetoExistente : objetosLluvia) {
                if (objetoExistente.getPosicion().overlaps(new Rectangle(x, y, 64, 64))) {
                    superposicion = true;
                    break; // Sale del ciclo si hay superposición
                }
            }
        } while (superposicion); // Repetir si hay superposición

        // Crear el objeto
        Objetos objeto;
        int tipo = MathUtils.random(1, 10); // Cambiado a 1-10 para aumentar la probabilidad de Pokeball
        if (tipo <= 5) {
            objeto = new Gengar(x, y); // 50% de probabilidad para Pokeball
        } else if (tipo <= 9) {
            objeto = new Pokeball(x, y); // 40% de probabilidad para Jigglypuff
        } else {
            objeto = new Jigglypuff(x, y); // 10% de probabilidad para Gengar
        }

        objetosLluvia.add(objeto); // Agregar el nuevo objeto a la lista
    }

    public boolean actualizarMovimiento(Entrenador tarro) {
        crearGotaDeLluvia(); // Intentar crear una nueva gota de lluvia

        for (int i = 0; i < objetosLluvia.size; i++) {
            Objetos objeto = objetosLluvia.get(i);
            objeto.actualizar(Gdx.graphics.getDeltaTime());

            // Mover los objetos a la izquierda a la velocidad especificada
            objeto.getPosicion().x -= VELOCIDAD * Gdx.graphics.getDeltaTime();

            // Eliminar objetos fuera de la pantalla
            if (objeto.getPosicion().x + 64 < 0) {
                objetosLluvia.removeIndex(i);
                continue;
            }

            // Verificar colisión con el tarro
            if (objeto.getPosicion().overlaps(tarro.getArea())) {
                objeto.aplicarEfecto(tarro);
                dropSound.play();
                objetosLluvia.removeIndex(i);

                // Si el entrenador se queda sin vidas, finaliza el juego
                if (tarro.getVidas() <= 0)
                    return false; // Game over
            }
        }
        return true;
    }

    public void actualizarDibujoLluvia(SpriteBatch batch) {
        for (Objetos objeto : objetosLluvia) {
            objeto.dibujar(batch);
        }
    }

    public void destruir() {
        dropSound.dispose();
        rainMusic.dispose();
    }

    public void pausar() {
        rainMusic.stop();
    }

    public void continuar() {
        rainMusic.play();
    }
}

