package com.chess;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen implements Screen {
    private static final int backX = Gdx.graphics.getWidth()-50, backY = Gdx.graphics.getHeight()-50, backLength = 25;
    Texture back;
    final Chess game;

    Board chessboard = new Board();
    OrthographicCamera camera;
    public GameScreen(final Chess game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false,960, 960);

        back = new Texture("back.png");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.05f,0,0.1f,0.5f);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();

        //render back button
        game.batch.draw(back,backX,backY);
        if(Gdx.input.getX() > backX && Gdx.input.getX() < backX + backLength && Gdx.graphics.getHeight() - Gdx.input.getY() > backY && Gdx.graphics.getHeight() - Gdx.input.getY() < backY + backLength && Gdx.input.isTouched()) {
            game.setScreen(new MainMenuScreen(game));
            dispose();
        }
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
