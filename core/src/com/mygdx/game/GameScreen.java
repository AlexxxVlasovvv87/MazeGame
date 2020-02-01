package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

public class GameScreen implements Screen {
    Maze game;
    GameWorld gameWorld;


    public GameScreen(Maze game) {
        this.game = game;
        gameWorld = new GameWorld();
        //Gdx.input.setCursorCatched(true);
    }

    @Override
    public void render(float delta) {
        gameWorld.render(delta);
    }

    @Override
    public void dispose() {
        gameWorld.dispose();
    }

    @Override
    public void resize(int width, int height) {
        gameWorld.resize(width, height);
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