/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgTest.stage.effect;

import com.jme3.animation.SkeletonControl;
import com.jme3.bullet.collision.PhysicsCollisionEvent;
import com.jme3.bullet.collision.PhysicsCollisionListener;
import com.jme3.bullet.collision.shapes.SphereCollisionShape;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.effect.ParticleEmitter;
import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import com.jme3.material.Material;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.Control;
import com.jme3.scene.shape.Sphere;
import java.io.IOException;
import sg.atom.stage.StageManager;
import sg.atom.stage.WorldManager;

/**
 *
 * @author hungcuong
 */
public class TrailControl implements PhysicsCollisionListener, Control {

    CharacterControl character;
    StageManager stageManager;
    private Sphere bullet;
    WorldManager worldManager;
    private SphereCollisionShape bulletCollisionShape;
    private ParticleEmitter dust, waterBack, waterRipple, waterTrail;
    private Node modelNode;
    private SkeletonControl skeletonControl;
    private Node allEmitter;

    TrailControl(CharacterControl character, StageManager stageManager) {
        this.character = character;
        this.stageManager = stageManager;
    }
    private Material matBullet;

    public void collision(PhysicsCollisionEvent event) {
    }

    private void prepareEmitter() {
        allEmitter = (Node) stageManager.getWorldManager().getAssetManager().loadModel("Models/MagicEffect/Water.j3o");

    }

    public Control cloneForSpatial(Spatial spatial) {
        //throw new UnsupportedOperationException("Not supported yet.");
        return this;
    }

    public void setSpatial(Spatial spatial) {
        modelNode = (Node) spatial;
        prepareEmitter();
        //modelNode.attachChild(allEmitter);
        //skeletonControl = modelNode.getControl(SkeletonControl.class);
        //skeletonControl.getAttachmentsNode("hand_L").attachChild(getDust());

    }

    void createFlame() {
    }

    public void setEnabled(boolean enabled) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean isEnabled() {
        //throw new UnsupportedOperationException("Not supported yet.");
        return true;
    }

    public void update(float tpf) {
    }

    public void render(RenderManager rm, ViewPort vp) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public void write(JmeExporter ex) throws IOException {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public void read(JmeImporter im) throws IOException {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    private Spatial getDust() {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
