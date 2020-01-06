package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.assets.loaders.ModelLoader;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.loader.ObjLoader;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.collision.Collision;
import com.badlogic.gdx.physics.bullet.collision.btBvhTriangleMeshShape;
import com.badlogic.gdx.physics.bullet.collision.btCollisionObject;
import com.badlogic.gdx.physics.bullet.collision.btCollisionShape;

public class MazeStage extends InputAdapter {//Объект должен быть кинематическим
    private static Instances instance;
    private static ModelLoader loader;
    private static Model model;
    private String id;
    final static short GROUND_FLAG = 1 << 8;
    private Vector3 vecX = new Vector3(1, 0, 0);
    private Vector3 vecZ = new Vector3(0, 0, 1);
    private Material material;
    private Material nMat;

    public MazeStage() {
    }

    public void create(){
        instance = new Instances();
        loader = new ObjLoader();
        model = loader.loadModel(Gdx.files.internal("Maze.obj"));
        id = model.nodes.get(0).id;
        btCollisionShape collisionShape = new btBvhTriangleMeshShape(model.meshParts);// как правльно получить шейпы
        instance.putConstructors(id, new GameObject.Constructor(model, id, collisionShape, 0));
        instance = new Instances();
        GameObject labirint = instance.getConstructors().get(id).construct();
        labirint.body.setCollisionFlags(labirint.body.getCollisionFlags() | btCollisionObject.CollisionFlags.CF_KINEMATIC_OBJECT);
        instance.addInstances(labirint);
        labirint.body.setContactCallbackFlag(GROUND_FLAG);
        labirint.body.setContactCallbackFilter(0);
        labirint.body.setActivationState(Collision.DISABLE_DEACTIVATION);
        material = instance.getInstances().get(1).materials.get(0);
        nMat = new Material();
        nMat.set(ColorAttribute.createDiffuse(Color.WHITE));
        material.set(nMat);
    }

    public void moveAxel(GameObject object){
        float degX = Gdx.input.getAccelerometerX()/4;
        float degZ = Gdx.input.getAccelerometerZ()/4;
        vecX = new Vector3(1, 0, 0);
        vecZ = new Vector3(0, 0, 1);
        /*if(Math.abs(degX)>Math.abs(degZ)) {//не работает одновременно одновременно по двум осям
            object.transform.setToRotation(vecX, degX);//!!!RIGHT!!!!
            float Oz = (float) Math.cos(degX);
            float Oy = (float) Math.sin(degX);
            vecZ = new Vector3(0, Oy, Oz);
        }
        else {
            object.transform.setToRotation(vecZ, degZ);//!!!RIGHT!!!!
            float Ox = (float) Math.cos(degZ);
            float Oy = (float) Math.sin(degZ);
            vecX = new Vector3(Ox, Oy, 0);
        }*/
        if(Math.abs(degX)>Math.abs(degZ)) {//не работает одновременно одновременно по двум осям
            object.transform.setToRotation(vecX, degX);//!!!RIGHT!!!!
            //float Oz = (float) Math.cos(degX);
            //float Oy = (float) Math.sin(degX);
            //vecZ = new Vector3(0, Oy, Oz);
            //object.transform.setToRotation(vecZ, degZ);
        }
        else {
            object.transform.setToRotation(vecZ, degZ);//!!!RIGHT!!!!
            //float Ox = (float) Math.cos(degZ);
            //float Oy = (float) Math.sin(degZ);
            //vecX = new Vector3(Ox, Oy, 0);
            //object.transform.setToRotation(vecX, degX);
        }
    }
}
