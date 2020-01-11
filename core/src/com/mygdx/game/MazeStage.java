package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.assets.loaders.ModelLoader;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.loader.ObjLoader;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.collision.Collision;
import com.badlogic.gdx.physics.bullet.collision.btBvhTriangleMeshShape;
import com.badlogic.gdx.physics.bullet.collision.btCollisionObject;
import com.badlogic.gdx.physics.bullet.collision.btCollisionShape;
import com.badlogic.gdx.physics.bullet.linearmath.btMotionState;


public class MazeStage extends InputAdapter {//Объект должен быть кинематическим
    private static Instances instance;
    private static ModelLoader loader;
    private static Model model;
    private String id;
    final static short GROUND_FLAG = 1 << 8;
    private Vector3 vecX = new Vector3(10f, 0, 0);
    private Vector3 vecZ = new Vector3(0, 0, 10);
    private Material material;
    private Material nMat;
    private float degX=0;
    private float degZ=0;
    private float deltaX=degX;
    private float deltaZ=degZ;
    private Matrix4 matrix = new Matrix4();
    private Quaternion quaternion;

    public MazeStage() {
    }

    public void create(){
        quaternion = new Quaternion();
        instance = new Instances();
        loader = new ObjLoader();
        model = loader.loadModel(Gdx.files.internal("lab8.obj"));
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
        degX = Gdx.input.getAccelerometerX();
        degZ = Gdx.input.getAccelerometerZ();
        object.transform.setFromEulerAngles(1, degX, degZ);
    }
}
