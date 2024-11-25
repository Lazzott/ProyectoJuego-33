package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import io.github.JUEGOGAMER.Lluvia;
import io.github.JUEGOGAMER.Entrenador;

public class GameScreen implements Screen {
    final juegoPokemon game;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private BitmapFont font;
    private Entrenador ash;
    private Lluvia lluvia;
    private Texture fondo; // Añadir la textura de fondo

    public GameScreen(final juegoPokemon game) {
        this.game = game;
        this.batch = game.getBatch();
        this.font = game.getFont();

        // Cargar sonidos
        Sound hurtSound = Gdx.audio.newSound(Gdx.files.internal("hurt.ogg"));
        ash = new Entrenador(new Texture(Gdx.files.internal("entrenador64.png")), hurtSound);

        // Cargar texturas para gotas
        Texture gota = new Texture(Gdx.files.internal("pokeball64.png"));
        Texture gotaMala = new Texture(Gdx.files.internal("gastly64.png"));
        Sound dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
        Music rainMusic = Gdx.audio.newMusic(Gdx.files.internal("y2mate.com - Cancion del pueblo lavanda.mp3"));

        // Crear lluvia
        lluvia = new Lluvia(gota, gotaMala, dropSound, rainMusic);

        // Cargar la textura del fondo
        fondo = new Texture(Gdx.files.internal("fondoJuego.png")); // Cambia "fondo.png" al nombre de tu archivo de fondo

        // Configurar la cámara
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        batch = new SpriteBatch();

        // Creación del tarro y lluvia
        ash.crear();
        lluvia.crear();
    }

    @Override
    public void render(float delta) {
        // Limpiar la pantalla
        ScreenUtils.clear(0, 0, 0.2f, 1);
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        // Dibujar el fondo
        batch.draw(fondo, 0, 0, camera.viewportWidth, camera.viewportHeight); // Dibuja el fondo cubriendo toda la pantalla

        // Dibujar textos
        font.draw(batch, "Puntos totales: " + ash.getPuntos(), 5, 475);
        font.draw(batch, "Vidas : " + ash.getVidas(), 670, 475);
        font.draw(batch, "HighScore : " + game.getHigherScore(), camera.viewportWidth / 2 - 50, 475);

        if (!ash.estaHerido()) {
            // Movimiento del tarro desde teclado
            ash.actualizarMovimiento();
            // Actualizar la lluvia
            if (!lluvia.actualizarMovimiento(ash)) {
                // Actualizar HighScore
                if (game.getHigherScore() < ash.getPuntos())
                    game.setHigherScore(ash.getPuntos());
                // Ir a la pantalla de Game Over
                game.setScreen(new GameOverScreen(game));
                dispose();
            }
        }

        // Dibujar el tarro y la lluvia
        ash.dibujar(batch);
        lluvia.actualizarDibujoLluvia(batch);

        batch.end();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
        lluvia.continuar();
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
        lluvia.pausar();
        game.setScreen(new PausaScreen(game, this));
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        ash.destruir();
        lluvia.destruir();
        fondo.dispose(); // Asegúrate de liberar la textura del fondo
    }
}



