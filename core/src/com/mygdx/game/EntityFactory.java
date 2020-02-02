package com.mygdx.game;


import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.physics.bullet.collision.btBroadphaseProxy;
import com.badlogic.gdx.physics.bullet.collision.btBvhTriangleMeshShape;
import com.badlogic.gdx.physics.bullet.collision.btCollisionObject;
import com.badlogic.gdx.physics.bullet.collision.btCollisionShape;
import com.badlogic.gdx.physics.bullet.collision.btPairCachingGhostObject;
import com.badlogic.gdx.physics.bullet.collision.btSphereShape;
import com.badlogic.gdx.physics.bullet.dynamics.btKinematicCharacterController;
import com.badlogic.gdx.physics.bullet.dynamics.btRigidBody;
import com.mygdx.game.Component.BulletComponent;
import com.mygdx.game.Component.CharacterComponent;
import com.mygdx.game.Component.ModelComponent;
import com.mygdx.game.Component.PlayerComponent;
import com.mygdx.game.System.BulletSystem;

public class EntityFactory {
    private static Model playerModel;
    private static ModelBuilder modelBuilder;
    private static Vector3 directionWalk;

    static {
        modelBuilder = new ModelBuilder();
        playerModel = modelBuilder.createSphere(0.5f, 0.5f, 0.5f, 20, 20, new Material(ColorAttribute.createDiffuse(Color.BROWN)),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
    }


    public static Entity createStaticEntity(Model model, float x, float y, float z) {
        final BoundingBox boundingBox = new BoundingBox();
        model.calculateBoundingBox(boundingBox);
        btCollisionShape col = new btBvhTriangleMeshShape(model.meshParts);
        Entity entity = new Entity();
        ModelComponent modelComponent = new ModelComponent(model, x, y, z);
        entity.add(modelComponent);
        BulletComponent bulletComponent = new BulletComponent();
        bulletComponent.bodyInfo = new btRigidBody.btRigidBodyConstructionInfo(0, null, col, Vector3.Zero);
        bulletComponent.body = new btRigidBody(bulletComponent.bodyInfo);
        bulletComponent.body.userData = entity;
        bulletComponent.motionState = new MotionState(modelComponent.instance.transform);
        ((btRigidBody) bulletComponent.body).setMotionState(bulletComponent.motionState);
        entity.add(bulletComponent);
        return entity;
    }

    private static Entity createCharacter(BulletSystem bulletSystem, float x, float y, float z) {
        Entity entity = new Entity();
        ModelComponent modelComponent = new ModelComponent(playerModel, x, y, z);
        entity.add(modelComponent);
        CharacterComponent characterComponent = new CharacterComponent();
        characterComponent.ghostObject = new btPairCachingGhostObject();
        characterComponent.ghostObject.setWorldTransform(modelComponent.instance.transform);
        characterComponent.ghostShape = new btSphereShape(0.25f);
        characterComponent.ghostObject.setCollisionShape(characterComponent.ghostShape);
        characterComponent.ghostObject.setCollisionFlags(btCollisionObject.CollisionFlags.CF_CHARACTER_OBJECT);
        characterComponent.characterController = new btKinematicCharacterController(characterComponent.ghostObject, characterComponent.ghostShape, .35f);
        directionWalk = new Vector3(0,-1000,0);
        characterComponent.characterController.setGravity(directionWalk);
        characterComponent.characterController.setMaxJumpHeight(0);
        characterComponent.characterController.setMaxSlope(0);
        characterComponent.characterController.setFallSpeed(1000);
        characterComponent.ghostObject.userData = entity;
        entity.add(characterComponent);
        bulletSystem.collisionWorld.addCollisionObject(entity.getComponent(CharacterComponent.class).ghostObject,
                (short) btBroadphaseProxy.CollisionFilterGroups.CharacterFilter,
                (short) (btBroadphaseProxy.CollisionFilterGroups.AllFilter));
        bulletSystem.collisionWorld.addCharacter(entity.getComponent(CharacterComponent.class).characterController);
        return entity;
    }

    public static Entity createBackGround(Model model, float x, float y, float z){
        Entity entity = new Entity();
        ModelComponent modelComponent = new ModelComponent(model,x,y,z);
        entity.add(modelComponent);
        return entity;
    }

    public static Entity createPlayer(BulletSystem bulletSystem, float x, float y, float z) {
        Entity entity = createCharacter(bulletSystem, x, y, z);
        entity.add(new PlayerComponent());
        return entity;
    }


    public static void dispose() {
        playerModel.dispose();
    }
}