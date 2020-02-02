package com.mygdx.game.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.mygdx.game.Assets;
import com.mygdx.game.Maze;

public class MainMenuScreen implements Screen {
    Maze game;
    Stage stage;
    Image backgroundImage;
    TextButton playButton;

    public MainMenuScreen(Maze game){
        this.game = game;
        stage = new Stage(new FillViewport(Maze.VIRTUAL_WIDTH, Maze.VIRTUAL_HEIGHT));
        setWidgets();
        configureWidgets();
        setListener();
        Gdx.input.setInputProcessor(stage);
    }
    private void setWidgets() {
        backgroundImage = new Image(new Texture(Gdx.files.internal("backgroundMN.png")));
        playButton = new TextButton("Play", Assets.skin);
    }

    private void configureWidgets(){
        backgroundImage.setSize(Maze.VIRTUAL_WIDTH, Maze.VIRTUAL_HEIGHT);
        playButton.setSize(128, 64);
        playButton.setPosition(Maze.VIRTUAL_WIDTH / 2 - playButton.getWidth() / 2, Maze.VIRTUAL_HEIGHT / 2 - 100);
        stage.addActor(backgroundImage);
        stage.addActor(playButton);
    }

    private void setListener(){
        playButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game));
            }
        });
    }

    @Override
    public void render(float delta) {
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height);
    }

    @Override
    public void dispose() {
        stage.dispose();
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
