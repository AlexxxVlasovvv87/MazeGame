package com.mygdx.game.Component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;

public class ModelComponent implements Component {
    public Vector3 startPosition;
    public Model model;
    public ModelInstance instance;

    public ModelComponent(Model model, float x, float y, float z) {
        this.model = model;
        this.instance = new ModelInstance(model, new Matrix4().setToTranslation(x, y, z));
        startPosition = new Vector3(x, y, z);
    }
}