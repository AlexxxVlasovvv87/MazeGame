package com.mygdx.game.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.mygdx.game.Maze;

public class WinScreen extends Actor{
    public Stage stage;
    private Maze game;
    Image backgroundImage;

    public WinScreen(Maze game){
        this.game = game;
        stage = new Stage(new FillViewport(Maze.VIRTUAL_WIDTH, Maze.VIRTUAL_HEIGHT));
        setWidgets();
        configureWidgets();
    }

    private void setWidgets() {
        backgroundImage = new Image(new Texture(Gdx.files.internal("gameWin.png")));
    }

    public void configureWidgets() {
        setSize(280, 100);
        setPosition(Maze.VIRTUAL_WIDTH / 2 - 280 / 2, Maze.VIRTUAL_HEIGHT / 2);
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(0, 0);
        backgroundImage.setPosition(x, y+32);
    }

    @Override
    public void setSize(float width, float height) {
        super.setSize(Maze.VIRTUAL_WIDTH, Maze.VIRTUAL_HEIGHT);
        backgroundImage.setSize(width, height);
    }

    public void gameWin(){
        stage.addActor(backgroundImage);
        stage.unfocus(stage.getKeyboardFocus());
    }
    public void update(float delta){
        stage.act(delta);
    }
    public void render(){
        stage.draw();
    }
    public void resize(int width, int height){
        stage.getViewport().update(width, height);
    }

    public void dispose(){
        stage.dispose();
    }
}
