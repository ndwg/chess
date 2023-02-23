package com.chess;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen implements Screen {
    private static final int backX = Gdx.graphics.getWidth()-50, backY = Gdx.graphics.getHeight()-50, backLength = 25;
    private static final int boardLength = 800, boardX = (Gdx.graphics.getWidth()/2)-400, boardY = (Gdx.graphics.getHeight()/2)-400;
    private static final int tileLength = 100, initialTileX = boardX-100, initialTileY = boardY+800;
    Texture backImage, boardImage, whitePawnImage, whiteKnightImage, whiteBishopImage, whiteRookImage, whiteQueenImage, whiteKingImage, blackPawnImage, blackKnightImage, blackBishopImage, blackRookImage, blackQueenImage, blackKingImage;
    final Chess game;
    Board chessboard = new Board();
    OrthographicCamera camera;
    public GameScreen(final Chess game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false,960, 960);

        backImage = new Texture("back.png");
        boardImage = new Texture("board.png");
        whitePawnImage = new Texture("white_pawn.png");
        whiteKnightImage = new Texture("white_knight.png");
        whiteBishopImage = new Texture("white_bishop.png");
        whiteRookImage = new Texture("white_rook.png");
        whiteQueenImage = new Texture("white_queen.png");
        whiteKingImage = new Texture("white_king.png");
        blackPawnImage = new Texture("black_pawn.png");
        blackKnightImage = new Texture("black_knight.png");
        blackBishopImage = new Texture("black_bishop.png");
        blackRookImage = new Texture("black_rook.png");
        blackQueenImage = new Texture("black_queen.png");
        blackKingImage = new Texture("black_king.png");
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
        game.batch.draw(backImage,backX,backY);
        if(Gdx.input.getX() > backX && Gdx.input.getX() < backX + backLength && Gdx.graphics.getHeight() - Gdx.input.getY() > backY && Gdx.graphics.getHeight() - Gdx.input.getY() < backY + backLength && Gdx.input.isTouched()) {
            game.setScreen(new MainMenuScreen(game));
            dispose();
        }

        //render chess board
        game.batch.draw(boardImage,boardX,boardY);

        //render chess pieces
        int tileX = initialTileX, tileY = initialTileY;
        for(int row = 0; row < 8; row++) {
            tileY -= 100;

            for(int col = 0; col < 8; col++) {
                tileX += 100;
                int tileID = chessboard.getTile(row,col);

                if(tileID < 20){
                    if(tileID % 10 == 1){
                        int adjustedTileX = tileX+(tileLength/2)-(whitePawnImage.getWidth()/2);
                        int adjustedTileY = tileY+(tileLength/2)-(whitePawnImage.getHeight()/2);
                        game.batch.draw(whitePawnImage,adjustedTileX,adjustedTileY);
                    }
                    else if(tileID % 10 == 2){
                        int adjustedTileX = tileX+(tileLength/2)-(whiteKnightImage.getWidth()/2);
                        int adjustedTileY = tileY+(tileLength/2)-(whiteKnightImage.getHeight()/2);
                        game.batch.draw(whiteKnightImage,adjustedTileX,adjustedTileY);
                    }
                    else if(tileID % 10 == 3){
                        int adjustedTileX = tileX+(tileLength/2)-(whiteBishopImage.getWidth()/2);
                        int adjustedTileY = tileY+(tileLength/2)-(whiteBishopImage.getHeight()/2);
                        game.batch.draw(whiteBishopImage,adjustedTileX,adjustedTileY);
                    }
                    else if(tileID % 10 == 4){
                        int adjustedTileX = tileX+(tileLength/2)-(whiteRookImage.getWidth()/2);
                        int adjustedTileY = tileY+(tileLength/2)-(whiteRookImage.getHeight()/2);
                        game.batch.draw(whiteRookImage,adjustedTileX,adjustedTileY);
                    }
                    else if(tileID % 10 == 5){
                        int adjustedTileX = tileX+(tileLength/2)-(whiteQueenImage.getWidth()/2);
                        int adjustedTileY = tileY+(tileLength/2)-(whiteQueenImage.getHeight()/2);
                        game.batch.draw(whiteQueenImage,adjustedTileX,adjustedTileY);
                    }
                    else if(tileID % 10 == 6){
                        int adjustedTileX = tileX+(tileLength/2)-(whiteKingImage.getWidth()/2);
                        int adjustedTileY = tileY+(tileLength/2)-(whiteKingImage.getHeight()/2);
                        game.batch.draw(whiteKingImage,adjustedTileX,adjustedTileY);
                    }
                }
                else{
                    if(tileID % 20 == 1){
                        int adjustedTileX = tileX+(tileLength/2)-(blackPawnImage.getWidth()/2);
                        int adjustedTileY = tileY+(tileLength/2)-(blackPawnImage.getHeight()/2);
                        game.batch.draw(blackPawnImage,adjustedTileX,adjustedTileY);
                    }
                    else if(tileID % 20 == 2){
                        int adjustedTileX = tileX+(tileLength/2)-(blackKnightImage.getWidth()/2);
                        int adjustedTileY = tileY+(tileLength/2)-(blackKnightImage.getHeight()/2);
                        game.batch.draw(blackKnightImage,adjustedTileX,adjustedTileY);
                    }
                    else if(tileID % 20 == 3){
                        int adjustedTileX = tileX+(tileLength/2)-(blackBishopImage.getWidth()/2);
                        int adjustedTileY = tileY+(tileLength/2)-(blackBishopImage.getHeight()/2);
                        game.batch.draw(blackBishopImage,adjustedTileX,adjustedTileY);
                    }
                    else if(tileID % 20 == 4){
                        int adjustedTileX = tileX+(tileLength/2)-(blackRookImage.getWidth()/2);
                        int adjustedTileY = tileY+(tileLength/2)-(blackRookImage.getHeight()/2);
                        game.batch.draw(blackRookImage,adjustedTileX,adjustedTileY);
                    }
                    else if(tileID % 20 == 5){
                        int adjustedTileX = tileX+(tileLength/2)-(blackQueenImage.getWidth()/2);
                        int adjustedTileY = tileY+(tileLength/2)-(blackQueenImage.getHeight()/2);
                        game.batch.draw(blackQueenImage,adjustedTileX,adjustedTileY);
                    }
                    else {
                        int adjustedTileX = tileX+(tileLength/2)-(blackKingImage.getWidth()/2);
                        int adjustedTileY = tileY+(tileLength/2)-(blackKingImage.getHeight()/2);
                        game.batch.draw(blackKingImage,adjustedTileX,adjustedTileY);
                    }
                }
            }

            tileX = initialTileX;
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
