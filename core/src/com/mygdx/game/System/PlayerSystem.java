package com.mygdx.game.System;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Component.CharacterComponent;
import com.mygdx.game.Component.ModelComponent;
import com.mygdx.game.Component.PlayerComponent;
import com.mygdx.game.GameWorld;
import com.mygdx.game.MotionState;


public class PlayerSystem extends EntitySystem implements EntityListener {
    private Entity player;
    private PlayerComponent playerComponent;
    private CharacterComponent characterComponent;
    private ModelComponent modelComponent;
    private final Vector3 tmp = new Vector3();
    private final Camera camera;
    private GameWorld gameWorld;
    private MotionState motionState;

    public PlayerSystem(GameWorld gameWorld, Camera camera) {
        this.camera = camera;
        this.gameWorld = gameWorld;
    }

    @Override
    public void addedToEngine(Engine engine) {
        engine.addEntityListener(Family.all(PlayerComponent.class).get(), this);
    }

    @Override
    public void update(float delta) {
        if (player == null) return;
        updateMovement(delta);
    }


    private void updateMovement(float delta) {
        //tmp.set(player.getComponent(modelComponent.getClass()).startPosition);
        characterComponent.characterDirection.set(-1, 0, 0).rot(modelComponent.instance.transform).nor();
        characterComponent.walkDirection.set(0, 0, 0);

        float y = 0.1f;
        tmp.set(0,0,0);
        if (Gdx.input.isKeyPressed(Input.Keys.U)) tmp.set(tmp.x+y, tmp.y, tmp.z);
        if (Gdx.input.isKeyPressed(Input.Keys.J)) tmp.set(tmp.x-y, tmp.y, tmp.z);
        if (Gdx.input.isKeyPressed(Input.Keys.W)) tmp.set(tmp.x, tmp.y+y, tmp.z);
        if (Gdx.input.isKeyPressed(Input.Keys.S)) tmp.set(tmp.x, tmp.y-y, tmp.z);
        if (Gdx.input.isKeyPressed(Input.Keys.A)) tmp.set(camera.direction).crs(camera.up).scl(-0.1f);
        if (Gdx.input.isKeyPressed(Input.Keys.D)) tmp.set(camera.direction).crs(camera.up).scl(0.1f);

        characterComponent.walkDirection.add(tmp);
        characterComponent.characterController.setWalkDirection(tmp);//управление. Двигаем ghostShape

        Matrix4 ghost = new Matrix4();
        //Matrix4 startPosition = new Matrix4();
        Vector3 translation = new Vector3();

        ghost.set(characterComponent.ghostObject.getWorldTransform());
        ghost.getTranslation(translation);
        /*motionState.getWorldTransform(startPosition);
        startPosition.getTranslation(translation);
        translation.set(translation.x+tmp.x,translation.y+tmp.y,translation.z+tmp.z);

        lastPosition.set(translation.x, translation.y, translation.z, 0,0,0,0 );
        motionState.setWorldTransform(lastPosition);*/

        //player.getComponent(BulletComponent.class).motionState.

////////////hjghjkvjhk
        //characterComponent.ghostObject.setWorldTransform(lastPosition);
        //lastPosition.getTranslation(translation);
///////////bhklbhjbk

        //modelComponent.instance.transform.set(translation.x, translation.y, translation.z, 0, 0, 0, 0);
        player.getComponent(ModelComponent.class).instance.transform.setToTranslation(translation.x, translation.y, translation.z);
        System.out.println(translation);

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            characterComponent.characterController.setJumpSpeed(5);
            characterComponent.characterController.jump();
        }
    }

    @Override
    public void entityAdded(Entity entity) {
        player = entity;
        playerComponent = entity.getComponent(PlayerComponent.class);
        characterComponent = entity.getComponent(CharacterComponent.class);
        modelComponent = entity.getComponent(ModelComponent.class);

        motionState = new MotionState(modelComponent.instance.transform);

    }

    @Override
    public void entityRemoved(Entity entity) {
    }
}