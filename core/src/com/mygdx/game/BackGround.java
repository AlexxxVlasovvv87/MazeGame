package com.mygdx.game;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;

public class BackGround {
    private boolean loading;
    private AssetManager assetManager;
    private ModelInstance space;


    public void create() {
        assetManager = new AssetManager();
        assetManager.load("spacesphere.obj", Model.class);
        loading = true;
    }
    public void doneLoading() {
        space = new ModelInstance(assetManager.get("spacesphere.obj", Model.class));
        loading = false;
    }

    public boolean isLoading() {
        return loading;
    }
    public ModelInstance getSpace() {
        return space;
    }
    public AssetManager getAssetManager() {
        return assetManager;
    }

    public void dispose(ModelInstance model){
    }
}
