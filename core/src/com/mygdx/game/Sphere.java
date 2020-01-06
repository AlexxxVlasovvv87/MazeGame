package com.mygdx.game;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.physics.bullet.collision.btSphereShape;

public class Sphere {
    private Instances instance;
    private static GameObject sphere;
    private static Model model;

    public void create(){
        instance = new Instances();
        ModelBuilder mb = new ModelBuilder();
        mb.begin();
        mb.node().id = "sphere";
        mb.part("sphere", GL20.GL_TRIANGLES, VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal, new Material(ColorAttribute.createDiffuse(Color.RED)))
                .sphere(0.5f, 0.5f, 0.5f, 20, 20);
        model=mb.end();
        instance.putConstructors("sphere", new GameObject.Constructor(model,"sphere", new btSphereShape(0.25f), 20));
        instance = new Instances();
        sphere = instance.getConstructors().get("sphere").construct();
        sphere.transform.trn(0f,1.5f,0f);
        sphere.body.proceedToTransform(sphere.transform);
        instance.addInstances(sphere);
    }
}
