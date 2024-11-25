package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture; // Importar la clase Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameOverScreen implements Screen {
    private final juegoPokemon game;
    private SpriteBatch batch;
    private BitmapFont font;
    private OrthographicCamera camera;
    private Texture fondo; // Atributo para la textura de fondo

    public GameOverScreen(final juegoPokemon game) {
        this.game = game;
        this.batch = game.getBatch();
        this.font = game.getFont();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        // Cargar la textura del fondo
        fondo = new Texture(Gdx.files.internal("pueblo-lavanda-lavender-town.jpg")); // Asegúrate de que el archivo esté en la carpeta assets
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        // Dibujar la textura de fondo
        batch.draw(fondo, 0, 0, 800, 480); // Dibuja el fondo ocupando toda la pantalla
        font.draw(batch, "GAME OVER", 100, 200);
        font.draw(batch, "Toca en cualquier lado para reiniciar.", 100, 100);
        batch.end();

        if (Gdx.input.isTouched()) {
            game.setScreen(new GameScreen(game));
            dispose();
        }
    }

    @Override
    public void show() {
        // TODO Auto-generated method stub

    }

    @Override
    public void resize(int width, int height) {
        // TODO Auto-generated method stub

    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub

    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub

    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub

    }

    @Override
    public void dispose() {
        fondo.dispose(); // Asegúrate de liberar la textura al final
        // TODO Auto-generated method stub

    }
}
