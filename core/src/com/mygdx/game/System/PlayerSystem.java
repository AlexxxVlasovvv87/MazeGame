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
import com.mygdx.game.Screen.WinScreen;
import com.mygdx.game.GameWorld;

public class PlayerSystem extends EntitySystem implements EntityListener {
    private Entity player;
    private PlayerComponent playerComponent;
    private CharacterComponent characterComponent;
    private ModelComponent modelComponent;
    private final Vector3 tmp = new Vector3();
    private final Camera camera;
    private GameWorld gameWorld;
    private WinScreen winScreen;
    private Vector3 translation;

    public PlayerSystem(GameWorld gameWorld, Camera camera, WinScreen winScreen) {
        this.winScreen = winScreen;
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
        updateMovement();
        checkGameWin();
    }

    private void updateMovement() {
        //characterComponent.characterDirection.set(-1, 0, 0).rot(modelComponent.instance.transform).nor();
        //characterComponent.walkDirection.set(0, 0, 0);
        float y = 0.1f;
        tmp.set(0,0,0);
        if (Gdx.input.isKeyPressed(Input.Keys.S)) tmp.set(tmp.x+y, tmp.y, tmp.z);
        if (Gdx.input.isKeyPressed(Input.Keys.W)) tmp.set(tmp.x-y, tmp.y, tmp.z);
        //if (Gdx.input.isKeyPressed(Input.Keys.UP)) tmp.set(tmp.x, tmp.y+y, tmp.z);
        //if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) tmp.set(tmp.x, tmp.y-y, tmp.z);
        if (Gdx.input.isKeyPressed(Input.Keys.A)) tmp.set(camera.direction).crs(camera.up).scl(-0.1f);
        if (Gdx.input.isKeyPressed(Input.Keys.D)) tmp.set(camera.direction).crs(camera.up).scl(0.1f);

        float degX = Gdx.input.getAccelerometerX()/100;
        float degY = Gdx.input.getAccelerometerY()/100;

        tmp.set(degY, 0, degX);//Comment only this lina and move with keyboards


        //characterComponent.walkDirection.add(tmp);
        characterComponent.characterController.setWalkDirection(tmp);//Move GhostObject
        Matrix4 ghost = new Matrix4();
        translation = new Vector3();
        ghost.set(characterComponent.ghostObject.getWorldTransform());
        ghost.getTranslation(translation);
        modelComponent.instance.transform.set(translation.x, translation.y, translation.z, tmp.x, tmp.y, tmp.z, 0f);

        /*if(Math.abs(translation.x)<1 && Math.abs(translation.y)<1){
            System.out.println("You win");
        }
        System.out.println(tmp);*/

        /*if (Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            characterComponent.characterController.setJumpSpeed(5);
            characterComponent.characterController.jump();
    }*/
    }

    private void checkGameWin() {
        if (Math.abs(translation.x)<1 && Math.abs(translation.z)<1) {
            winScreen.gameWin();
        }
    }
    @Override
    public void entityAdded(Entity entity) {
        player = entity;
        playerComponent = entity.getComponent(PlayerComponent.class);
        characterComponent = entity.getComponent(CharacterComponent.class);
        modelComponent = entity.getComponent(ModelComponent.class);
    }

    @Override
    public void entityRemoved(Entity entity) {
    }
}