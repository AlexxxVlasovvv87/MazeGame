package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;


public class Activity{
    private Stage stage;
    private BitmapFont font;
    private Label label;
    private StringBuilder builder;

    public void create(){
        stage = new Stage();
        font = new BitmapFont();
        label = new Label(" ", new Label.LabelStyle(font, Color.WHITE));
        stage.addActor(label);
        builder = new StringBuilder();
    }

    public void render(){
        builder.setLength(0);
        builder.append("!!!!!YOU WIN!!!!!");
        label.setFontScale(3f);
        label.setText(builder);
        stage.draw();
    }

    public void resize(int width, int height){
        stage.getViewport().update(width-40, height, true);
    }
}
