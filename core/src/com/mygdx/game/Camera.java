package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;

public class Camera {

    private CameraInputController camController;
    private static PerspectiveCamera camera;
    private Maze labirint;

    public PerspectiveCamera getCamera() {
        return camera;
    }
    public CameraInputController getCamController() {
        return camController;
    }

    public void create(){
        labirint = new Maze();
        camera = new PerspectiveCamera(75,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        camera.position.set(12f,20f,0f);
        camera.lookAt(0f,0f,0f);
        camera.near =0.1f;
        camera.far =300f;
        camera.update();

        camController = new CameraInputController(camera);
        Gdx.input.setInputProcessor(camController);
    }
}
