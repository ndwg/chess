package com.chess;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

public class MainMenuScreen implements Screen {
    private static final int buttonHeight = 25, buttonWidth = 148, buttonX = (Gdx.graphics.getWidth()/2)-(buttonWidth/2),playButtonY = 300, exitButtonY = 260;
    Texture playButtonActive, playButtonInactive, exitButtonActive, exitButtonInactive,title;
    final Chess game;
    OrthographicCamera camera;
    public MainMenuScreen(final Chess game) {
        this.game = game;

        playButtonActive = new Texture("play_button_active.png");
        playButtonInactive = new Texture("play_button_inactive.png");
        exitButtonActive = new Texture("exit_button_active.png");
        exitButtonInactive = new Texture("exit_button_inactive.png");
        title = new Texture("title.png");

        camera = new OrthographicCamera();
        camera.setToOrtho(false,960,960);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0,0,0.4f,1);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(title,(Gdx.graphics.getWidth()/2)-396,500);

        if(Gdx.input.getX() > buttonX && Gdx.input.getX() < buttonX + buttonWidth){
            if(Gdx.graphics.getHeight()-Gdx.input.getY() > playButtonY && Gdx.graphics.getHeight()-Gdx.input.getY() < playButtonY + buttonHeight){
                game.batch.draw(playButtonActive,buttonX,playButtonY);
                if(Gdx.input.isTouched()){
                    game.setScreen(new GameScreen(game));
                    dispose();
                }
            }
            else{
                game.batch.draw(playButtonInactive,buttonX,playButtonY);
            }
            if(Gdx.graphics.getHeight()-Gdx.input.getY() > exitButtonY && Gdx.graphics.getHeight()-Gdx.input.getY() < exitButtonY + buttonHeight){
                game.batch.draw(exitButtonActive,buttonX,exitButtonY);
                if(Gdx.input.isTouched()){
                    Gdx.app.exit();
                }
            }
            else{
                game.batch.draw(exitButtonInactive,buttonX,exitButtonY);
            }
        }
        else{
            game.batch.draw(playButtonInactive,buttonX,playButtonY);
            game.batch.draw(exitButtonInactive,buttonX,exitButtonY);
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
