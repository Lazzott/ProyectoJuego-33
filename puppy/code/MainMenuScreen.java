package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture; // Importar Texture
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class MainMenuScreen implements Screen {

    final juegoPokemon game;
    private SpriteBatch batch;
    private BitmapFont font;
    private OrthographicCamera camera;
    private Texture fondo; // Declarar la textura para el fondo

    public MainMenuScreen(final juegoPokemon game) {
        this.game = game;
        this.batch = game.getBatch();
        this.font = game.getFont();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        // Cargar la textura del fondo
        fondo = new Texture(Gdx.files.internal("assets/fondoInicio.png")); // Especifica la ruta a tu imagen
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        // Dibujar el fondo
        batch.draw(fondo, 0, 0, 800, 480); // Dibuja el fondo en (0,0) con el tamaño de la pantalla

        font.getData().setScale(2, 2);
        font.draw(batch, "Toca en cualquier lugar para comenzar!", 130, 50);

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

