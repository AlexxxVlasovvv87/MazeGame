package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.Bullet;

public class Maze extends InputAdapter implements ApplicationListener {
	private ModelBatch modelBatch;
	private Environment environment;
	private MazeStage labStage;
	private BackGround backGround;
	private Camera camera;
	private Instances instances;
	private Sphere sphere;
	private DynamicWorld dynamicWorld;
	private Activity activity;
	private AssetManager assetManager;
	private boolean flag = false;

	@Override
	public void create() {
		Bullet.init();

		activity = new Activity();
		dynamicWorld = new DynamicWorld();
		sphere = new Sphere();
		backGround = new BackGround();
		modelBatch = new ModelBatch();
		instances = new Instances();
		labStage = new MazeStage();
		camera = new Camera();
		assetManager = new AssetManager();

		environment = new Environment();
		environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
		environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));

		camera.create();
		sphere.create();
		backGround.create();
		labStage.create();
		dynamicWorld.create();
		activity.create();
	}

	@Override
	public void resize(int width, int height) {
		activity.resize(width, height);
	}


	@Override
	public void render() {
		final float delta = Math.min(1f / 30f, Gdx.graphics.getDeltaTime());
		labStage.moveAxel(instances.getInstances().get(1));

		dynamicWorld.getDynamicsWorld().stepSimulation(delta, 5, 1f / 60f);
		camera.getCamController().update();

		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		if(backGround.isLoading() && backGround.getAssetManager().update())
			backGround.doneLoading();

		modelBatch.begin(camera.getCamera());
		modelBatch.render(instances.getInstances(), environment);
		if(backGround.getSpace()!=null)
			modelBatch.render(backGround.getSpace());
		modelBatch.end();

		Vector3 vec = new Vector3();
		instances.getInstances().get(0).transform.getTranslation(vec);
		if(Math.abs(vec.x)<0.1f || Math.abs(vec.z)<0.1f) {
			flag=true;
		}
		if(flag){
			activity.render();
		}
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
		for (GameObject object : instances.getInstances())
			instances.dispose(object);

		for (GameObject.Constructor object : instances.getConstructors().values())
			object.dispose();
		instances.getInstances().clear();
		instances.getConstructors().clear();

		modelBatch.dispose();

		dynamicWorld.dispose();

		assetManager.dispose();
	}
}