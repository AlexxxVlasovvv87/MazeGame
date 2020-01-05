package com.mygdx.game;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.collision.btCollisionDispatcher;
import com.badlogic.gdx.physics.bullet.collision.btCollisionObject;
import com.badlogic.gdx.physics.bullet.collision.btDbvtBroadphase;
import com.badlogic.gdx.physics.bullet.collision.btDefaultCollisionConfiguration;
import com.badlogic.gdx.physics.bullet.dynamics.btDiscreteDynamicsWorld;
import com.badlogic.gdx.physics.bullet.dynamics.btSequentialImpulseConstraintSolver;

public class DynamicWorld {
    final static short GROUND_FLAG = 1 << 8;
    final static short ALL_FLAG = -1;
    private static btDefaultCollisionConfiguration collisionConfig;
    private static btCollisionDispatcher dispatcher;
    private static btDbvtBroadphase broadphase;
    private static btSequentialImpulseConstraintSolver constraintSolver;

    public btDiscreteDynamicsWorld getDynamicsWorld() {
        return dynamicsWorld;
    }

    private static btDiscreteDynamicsWorld dynamicsWorld;
    private static Instances instances;

    public void create(){
        instances = new Instances();
        collisionConfig = new btDefaultCollisionConfiguration();
        dispatcher = new btCollisionDispatcher(collisionConfig);
        broadphase = new btDbvtBroadphase();
        constraintSolver = new btSequentialImpulseConstraintSolver();
        dynamicsWorld = new btDiscreteDynamicsWorld(dispatcher, broadphase, constraintSolver, collisionConfig);

        dynamicsWorld.setGravity(new Vector3(0, -20f, 0));
        dynamicsWorld.addRigidBody(instances.getInstances().get(0).body, GROUND_FLAG, ALL_FLAG);

        GameObject object = instances.getInstances().get(1);
        object.body.setCollisionFlags(object.body.getCollisionFlags() | btCollisionObject.CollisionFlags.CF_KINEMATIC_OBJECT);
        dynamicsWorld.addRigidBody(object.body);
        object.body.setContactCallbackFlag(GROUND_FLAG);
        object.body.setContactCallbackFilter(1);
    }

    public void dispose(){
        dispatcher.dispose();
        broadphase.dispose();
        constraintSolver.dispose();
        dynamicsWorld.dispose();
        collisionConfig.dispose();
    }
}
