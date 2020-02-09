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
    Image[] backgroundImage = new Image[50];
    int variant = (int)(Math.random()*50);

    public WinScreen(Maze game){
        this.game = game;
        stage = new Stage(new FillViewport(Maze.VIRTUAL_WIDTH, Maze.VIRTUAL_HEIGHT));
        setWidgets();
        configureWidgets();
    }

    private void setWidgets() {
        backgroundImage[0] = new Image(new Texture(Gdx.files.internal("Win1.png")));
        backgroundImage[1] = new Image(new Texture(Gdx.files.internal("Win2.png")));
        backgroundImage[2] = new Image(new Texture(Gdx.files.internal("Win3.png")));
        backgroundImage[3] = new Image(new Texture(Gdx.files.internal("Win4.png")));
        backgroundImage[4] = new Image(new Texture(Gdx.files.internal("Win5.png")));
        backgroundImage[5] = new Image(new Texture(Gdx.files.internal("Win6.png")));
        backgroundImage[6] = new Image(new Texture(Gdx.files.internal("Win7.png")));
        backgroundImage[7] = new Image(new Texture(Gdx.files.internal("Win8.png")));
        backgroundImage[8] = new Image(new Texture(Gdx.files.internal("Win9.png")));
        backgroundImage[9] = new Image(new Texture(Gdx.files.internal("Win10.png")));
        backgroundImage[10] = new Image(new Texture(Gdx.files.internal("Win11.png")));
        backgroundImage[11] = new Image(new Texture(Gdx.files.internal("Win12.png")));
        backgroundImage[12] = new Image(new Texture(Gdx.files.internal("Win13.png")));
        backgroundImage[13] = new Image(new Texture(Gdx.files.internal("Win14.png")));
        backgroundImage[14] = new Image(new Texture(Gdx.files.internal("Win15.png")));
        backgroundImage[15] = new Image(new Texture(Gdx.files.internal("Win16.png")));
        backgroundImage[16] = new Image(new Texture(Gdx.files.internal("Win17.png")));
        backgroundImage[17] = new Image(new Texture(Gdx.files.internal("Win18.png")));
        backgroundImage[18] = new Image(new Texture(Gdx.files.internal("Win19.png")));
        backgroundImage[19] = new Image(new Texture(Gdx.files.internal("Win20.png")));
        backgroundImage[20] = new Image(new Texture(Gdx.files.internal("Win21.png")));
        backgroundImage[21] = new Image(new Texture(Gdx.files.internal("Win22.png")));
        backgroundImage[22] = new Image(new Texture(Gdx.files.internal("Win23.png")));
        backgroundImage[23] = new Image(new Texture(Gdx.files.internal("Win24.png")));
        backgroundImage[24] = new Image(new Texture(Gdx.files.internal("Win25.png")));
        backgroundImage[25] = new Image(new Texture(Gdx.files.internal("Win26.png")));
        backgroundImage[26] = new Image(new Texture(Gdx.files.internal("Win27.png")));
        backgroundImage[27] = new Image(new Texture(Gdx.files.internal("Win28.png")));
        backgroundImage[28] = new Image(new Texture(Gdx.files.internal("Win29.png")));
        backgroundImage[29] = new Image(new Texture(Gdx.files.internal("Win30.png")));
        backgroundImage[30] = new Image(new Texture(Gdx.files.internal("Win31.png")));
        backgroundImage[31] = new Image(new Texture(Gdx.files.internal("Win32.png")));
        backgroundImage[32] = new Image(new Texture(Gdx.files.internal("Win33.png")));
        backgroundImage[33] = new Image(new Texture(Gdx.files.internal("Win34.png")));
        backgroundImage[34] = new Image(new Texture(Gdx.files.internal("Win35.png")));
        backgroundImage[35] = new Image(new Texture(Gdx.files.internal("Win36.png")));
        backgroundImage[36] = new Image(new Texture(Gdx.files.internal("Win37.png")));
        backgroundImage[37] = new Image(new Texture(Gdx.files.internal("Win38.png")));
        backgroundImage[38] = new Image(new Texture(Gdx.files.internal("Win39.png")));
        backgroundImage[39] = new Image(new Texture(Gdx.files.internal("Win40.png")));
        backgroundImage[40] = new Image(new Texture(Gdx.files.internal("Win41.png")));
        backgroundImage[41] = new Image(new Texture(Gdx.files.internal("Win42.png")));
        backgroundImage[42] = new Image(new Texture(Gdx.files.internal("Win43.png")));
        backgroundImage[43] = new Image(new Texture(Gdx.files.internal("Win44.png")));
        backgroundImage[44] = new Image(new Texture(Gdx.files.internal("Win45.png")));
        backgroundImage[45] = new Image(new Texture(Gdx.files.internal("Win46.png")));
        backgroundImage[46] = new Image(new Texture(Gdx.files.internal("Win47.png")));
        backgroundImage[47] = new Image(new Texture(Gdx.files.internal("Win48.png")));
        backgroundImage[48] = new Image(new Texture(Gdx.files.internal("Win49.png")));
        backgroundImage[49] = new Image(new Texture(Gdx.files.internal("Win50.png")));
    }

    public void configureWidgets() {
        setSize(450, 450);
        setPosition(Maze.VIRTUAL_WIDTH / 2 - backgroundImage[variant].getWidth() / 2, Maze.VIRTUAL_HEIGHT / 2-250);
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(0,0);
        backgroundImage[variant].setPosition(x, y);
    }

    @Override
    public void setSize(float width, float height) {
        super.setSize(Maze.VIRTUAL_WIDTH, Maze.VIRTUAL_HEIGHT);
        backgroundImage[variant].setSize(width, height);
    }

    public void gameWin(){
        stage.addActor(backgroundImage[variant]);
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
