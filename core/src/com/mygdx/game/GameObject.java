package com.mygdx.game;

import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.physics.bullet.collision.btCollisionShape;
import com.badlogic.gdx.physics.bullet.dynamics.btRigidBody;
import com.badlogic.gdx.utils.Disposable;

public class GameObject extends ModelInstance implements Disposable {
    public final Vector3 center = new Vector3();
    public final Vector3 dimensions = new Vector3();
    //public float radius;

    private final static BoundingBox bounds = new BoundingBox();

    public GameObject(Model model, String rootNode, boolean mergeTransform) {
        super(model, rootNode, mergeTransform);
        model.calculateBoundingBox(bounds);
        bounds.getCenter(center);
        bounds.getDimensions(dimensions);
        //radius = dimensions.len() / 2f;
    }
    public btRigidBody body;
    public MyMotionState motionState;

    public GameObject (Model model, String node, btRigidBody.btRigidBodyConstructionInfo constructionInfo) {
        super(model, node);
        motionState = new MyMotionState();
        motionState.transform = transform;
        body = new btRigidBody(constructionInfo);
        body.setMotionState(motionState);
    }

    @Override
    public void dispose () {
        body.dispose();
        motionState.dispose();
    }

    static class Constructor implements Disposable {
        public final Model model;
        public final String node;
        public final btCollisionShape shape;
        public final btRigidBody.btRigidBodyConstructionInfo constructionInfo;
        private static Vector3 localInertia = new Vector3();

        public Constructor (Model model, String node, btCollisionShape shape, float mass) {
            this.model = model;
            this.node = node;
            this.shape = shape;
            if (mass > 0f)
                shape.calculateLocalInertia(mass, localInertia);
            else
                localInertia.set(0, 0, 0);
            this.constructionInfo = new btRigidBody.btRigidBodyConstructionInfo(mass, null, shape, localInertia);
        }

        public GameObject construct () {
            return new GameObject(model, node, constructionInfo);
        }

        @Override
        public void dispose () {
            shape.dispose();
            constructionInfo.dispose();
        }
    }
}