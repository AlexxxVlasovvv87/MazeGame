package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.mygdx.game.Screen.MainMenuScreen;

public class Maze extends ApplicationAdapter {
	public static final float VIRTUAL_WIDTH = 540;
	public static final float VIRTUAL_HEIGHT = 960;
	Screen screen;


	@Override
	public void create() {
		new Assets();
		//Gdx.input.setCatchBackKey(true);
		setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT | GL30.GL_DEPTH_BUFFER_BIT);
		screen.render(Gdx.graphics.getDeltaTime());
	}

	@Override
	public void resize(int width, int height) {
		screen.resize(width, height);
	}


	public void setScreen(Screen screen) {
		if (this.screen != null) {
			this.screen.hide();
			this.screen.dispose();
		}
		this.screen = screen;
		if (this.screen != null) {
			this.screen.show();
			this.screen.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		}
	}

	@Override
	public void dispose() {
		Assets.dispose();
	}
}