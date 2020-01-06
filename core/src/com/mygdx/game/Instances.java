package com.mygdx.game;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;

public class Instances {
    private static ArrayMap<String, GameObject.Constructor> constructors = new ArrayMap<>();
    public void putConstructors(String id, GameObject.Constructor instance){ constructors.put(id, instance); }
    public ArrayMap<String, GameObject.Constructor> getConstructors() { return constructors; }


    private static Array<GameObject> instances = new Array<GameObject>();
    public void addInstances(GameObject instance){
        instances.add(instance);
    }
    public Array<GameObject> getInstances() {
        return instances;
    }

    public void dispose(GameObject object){
        object.dispose();
    }
}
