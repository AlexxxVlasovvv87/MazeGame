package com.mygdx.game.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.mygdx.game.GameWorld;
import com.mygdx.game.Maze;

public class GameScreen implements Screen {
    Maze game;
    GameWorld gameWorld;
    WinScreen winScreen;


    public GameScreen(Maze game) {
        this.game = game;
        winScreen = new WinScreen(game);
        gameWorld = new GameWorld(winScreen);
        Gdx.input.setInputProcessor(winScreen.stage);
    }

    @Override
    public void render(float delta) {
        gameWorld.render(delta);
        winScreen.update(delta);
        winScreen.render();
    }

    @Override
    public void dispose() {
        gameWorld.dispose();
        winScreen.dispose();
    }

    @Override
    public void resize(int width, int height) {
        gameWorld.resize(width, height);
        winScreen.resize(width, height);
    }

    @Override
    public void show() {
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
}