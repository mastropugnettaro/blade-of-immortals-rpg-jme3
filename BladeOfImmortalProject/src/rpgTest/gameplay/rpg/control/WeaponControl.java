/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgTest.gameplay.rpg.control;

import com.jme3.animation.SkeletonControl;
import com.jme3.asset.AssetManager;
import com.jme3.bullet.collision.PhysicsCollisionEvent;
import com.jme3.bullet.collision.PhysicsCollisionListener;
import com.jme3.bullet.collision.shapes.SphereCollisionShape;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.effect.ParticleEmitter;
import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.renderer.Camera;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.renderer.queue.RenderQueue.ShadowMode;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.Control;
import com.jme3.scene.shape.Sphere;
import com.jme3.scene.shape.Sphere.TextureMode;
import java.io.IOException;
import rpgTest.stage.world.physic.BombControl;
import sg.atom.stage.StageManager;
import sg.atom.stage.WorldManager;

/**
 *
 * @author hungcuong
 */
public class WeaponControl implements PhysicsCollisionListener, Control {

    CharacterControl character;
    StageManager stageManager;
    private Sphere bullet;
    WorldManager worldManager;
    private SphereCollisionShape bulletCollisionShape;

    private Node modelNode;
    private final ParticleEmitter effect;
    private ParticleEmitter flame;

    public WeaponControl(CharacterControl character, StageManager stageManager) {
        this.character = character;
        this.stageManager = stageManager;
        this.effect = stageManager.getGameEffectManager().getDefaultFactory().createFlame();
    }
    private Material matBullet;

    private void bulletControl() {

        Camera cam = stageManager.getCurrentActiveCamera();
        Node rootNode = worldManager.getRootNode();

        Geometry bulletg = new Geometry("bullet", bullet);
        bulletg.setMaterial(matBullet);
        bulletg.setShadowMode(ShadowMode.CastAndReceive);
        bulletg.setLocalTranslation(character.getPhysicsLocation().add(cam.getDirection().mult(5)));
        RigidBodyControl bulletControl = new BombControl(bulletCollisionShape, 1);
        bulletControl.setCcdMotionThreshold(0.1f);
        bulletControl.setLinearVelocity(cam.getDirection().mult(80));
        bulletg.addControl(bulletControl);
        rootNode.attachChild(bulletg);

        worldManager.getPhysicsSpace().add(bulletControl);
    }

    public void collision(PhysicsCollisionEvent event) {
        if (event.getObjectA() instanceof BombControl) {
            final Spatial node = event.getNodeA();
            effect.killAllParticles();
            effect.setLocalTranslation(node.getLocalTranslation());
            effect.emitAllParticles();
        } else if (event.getObjectB() instanceof BombControl) {
            final Spatial node = event.getNodeB();
            effect.killAllParticles();
            effect.setLocalTranslation(node.getLocalTranslation());
            effect.emitAllParticles();
        }
    }

    Node createSword() {
        AssetManager assetManager = stageManager.getWorldManager().getAssetManager();
        Node swordNode = (Node) assetManager.loadModel("Models/Weapon/sword/sword.j3o");

        return swordNode;
    }

    private void prepareBullet() {

        bullet = new Sphere(32, 32, 0.4f, true, false);
        bullet.setTextureMode(TextureMode.Projected);
        bulletCollisionShape = new SphereCollisionShape(0.4f);
        matBullet = new Material(worldManager.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        matBullet.setColor("Color", ColorRGBA.Green);
        matBullet.setColor("m_GlowColor", ColorRGBA.Green);
        worldManager.getPhysicsSpace().addCollisionListener(this);
    }

    public Control cloneForSpatial(Spatial spatial) {
        //throw new UnsupportedOperationException("Not supported yet.");
        return this;
    }
    Node swordNode;
    SkeletonControl skeletonControl;
    boolean weaponInHand;

    public void setSpatial(Spatial spatial) {
        modelNode = (Node) spatial;
        skeletonControl = modelNode.getControl(SkeletonControl.class);
        //skeletonControl.getAttachmentsNode("hand_L").attachChild(worldManager.createRedBox());
        swordNode = createSword();
        toBack();

    }

    void createFlame() {
        flame = stageManager.getGameEffectManager().getDefaultFactory().createFlame();
        skeletonControl.getAttachmentsNode("hand_L").attachChild(flame);
        flame.emitAllParticles();
    }

    public void toHand() {
        skeletonControl.getAttachmentsNode("hand_L").attachChild(swordNode);
        swordNode.setLocalRotation(new Quaternion().fromAngles(0, 0 * FastMath.DEG_TO_RAD, 180 * FastMath.DEG_TO_RAD));
        swordNode.setLocalTranslation(0f, 0.4f, -2f);
        swordNode.setLocalScale(0.8f);
        weaponInHand = true;
    }

    public void toBack() {
        skeletonControl.getAttachmentsNode("spine").attachChild(swordNode);
        swordNode.setLocalRotation(new Quaternion().fromAngles(15 * FastMath.DEG_TO_RAD, 100 * FastMath.DEG_TO_RAD, 90 * FastMath.DEG_TO_RAD));
        swordNode.setLocalTranslation(-3f, -4f, -12f);
        swordNode.setLocalScale(0.8f);
        weaponInHand = false;
    }

    public void setEnabled(boolean enabled) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean isEnabled() {
        //throw new UnsupportedOperationException("Not supported yet.");
        return true;
    }

    public void update(float tpf) {
        //throw new UnsupportedOperationException("Not supported yet.");
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

    public boolean isWeaponInHand() {
        return weaponInHand;
    }

    public void switchIt() {
        if (weaponInHand) {
            toBack();
        } else {
            toHand();
        }
    }
}
