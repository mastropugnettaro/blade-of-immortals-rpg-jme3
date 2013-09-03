/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgTest.gameplay.rpg.control;

import com.jme3.animation.AnimChannel;
import com.jme3.animation.AnimControl;
import com.jme3.animation.AnimEventListener;
import com.jme3.animation.LoopMode;
import com.jme3.animation.SkeletonControl;
import com.jme3.bullet.collision.shapes.CapsuleCollisionShape;
import com.jme3.bullet.control.CharacterControl;

import com.jme3.input.ChaseCamera;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.Control;
import java.util.logging.Logger;

import sg.atom.core.StageManager;
import sg.atom.fx.GameEffectManager;
import sg.atom.gameplay.AtomCharacterControl;
import sg.atom.stage.WorldManager;

/**
 *
 * @author hungcuong
 */
public class RPGCharacterControl extends AtomCharacterControl implements AnimEventListener {
    //animation

    private static final Logger logger = Logger.getLogger(RPGCharacterControl.class.getName());
    AnimChannel animationChannel;
    AnimChannel shootingChannel;
    AnimControl animationControl;
    float airTime = 0;
    //camera
    boolean left = false, right = false, up = false, down = false;
    ChaseCamera chaseCam;
    //temp vectors
    Vector3f walkDirection = new Vector3f();
    Camera cam;
    private CharacterControl character;
    private Node playerModel;
    GameEffectManager gameEffectManager;
    StageManager stageManager;
    SkeletonControl skeletonControl;
    private boolean jumping;
    private boolean rolling;
    private WeaponControl weaponControl;
    AnimChannel lowerBody, upperBody;
    float moveSpeed;
    private float changeViewSpeed = 0.5f;

    public RPGCharacterControl(StageManager stageManager) {
        this.stageManager = stageManager;
        this.gameEffectManager = stageManager.getGameEffectManager();
    }

    void moveChar(float tpf) {
        this.cam = stageManager.getCurrentActiveCamera();
        moveSpeed = 0.1f;
        /*
         if (isOnWater()) {
         moveSpeed = 0.04f;
         }*/
        // Gameplay
        Vector3f camDir = cam.getDirection().clone().multLocal(moveSpeed);
        Vector3f camLeft = cam.getLeft().clone().multLocal(moveSpeed);
        camDir.y = 0;
        camLeft.y = 0;
        walkDirection.set(0, 0, 0);
        if (left) {
            walkDirection.addLocal(camLeft);
        }
        if (right) {
            walkDirection.addLocal(camLeft.negate());
        }
        if (up) {
            walkDirection.addLocal(camDir);
        }
        if (down) {
            walkDirection.addLocal(camDir.negate());
        }
        if (!character.onGround()) {
            //System.out.println(airTime);
            airTime = airTime + tpf;
            if (airTime > 0.4f) {
                jumping = true;
            }
        } else {
            airTime = 0;
            jumping = false;
            rolling = false;
        }

        Vector3f tempViewDir = character.getViewDirection().clone();

        character.setViewDirection(tempViewDir.interpolate(walkDirection, 1 / changeViewSpeed * tpf));
        character.setWalkDirection(walkDirection);
    }

    public void updateAnimation() {
        if (jumping) {
            if (!"Jump".equals(animationChannel.getAnimationName())) {
                if (!rolling) {
                    animationChannel.setAnim("Jump", 0f);
                    animationChannel.setSpeed(1f);
                    animationChannel.setLoopMode(LoopMode.DontLoop);
                    //System.out.println("Jumping");
                }
            } else {
                if (airTime > 1f) {
                    // On the air
                    if (!"FlyRolling".equals(animationChannel.getAnimationName())) {
                        animationChannel.setAnim("FlyRolling", 0.8f);
                        animationChannel.setLoopMode(LoopMode.Loop);
                        animationChannel.setSpeed(1.2f);
                        //System.out.println("Rolling");
                        rolling = true;
                    }
                }
            }
        } else if (walkDirection.length() == 0) {
            if (!"Stand".equals(animationChannel.getAnimationName())) {
                animationChannel.setAnim("Stand");
                animationChannel.setLoopMode(LoopMode.Loop);
                animationChannel.setSpeed(2f);
            }
        } else {

            if (airTime > 0.1f) {
                // On the air
                if (!"Walk".equals(animationChannel.getAnimationName())) {
                    animationChannel.setAnim("Walk", 0.2f);
                    animationChannel.setLoopMode(LoopMode.Loop);
                    animationChannel.setSpeed(2f);
                }
            } else if (!"Run".equals(animationChannel.getAnimationName())) {
                animationChannel.setAnim("Run", 1.2f);
                animationChannel.setLoopMode(LoopMode.Loop);
                animationChannel.setSpeed(2f);
            }
        }
    }

    public void onAction(String binding, boolean value, float tpf) {
        if (binding.equals("CharLeft")) {
            if (value) {
                left = true;
            } else {
                left = false;
            }
        } else if (binding.equals("CharRight")) {
            if (value) {
                right = true;
            } else {
                right = false;
            }
        } else if (binding.equals("CharUp")) {
            if (value) {
                up = true;
            } else {
                up = false;
            }
        } else if (binding.equals("CharDown")) {
            if (value) {
                down = true;
            } else {
                down = false;
            }
        } else if (binding.equals("CharSpace")) {
            //System.out.println("Jumped");
            jumping = true;
            character.jump();
        } else if (binding.equals("CharShoot") && !value) {
            if (weaponControl.isWeaponInHand()) {
                attack(1);
            }
        } else if (binding.equals("CharWeapon") && !value) {
            weaponControl.switchIt();
        }
    }
    int currentAttackType = 1;

    void attack(float speed) {
        String add = "";
        if (currentAttackType < 3) {
            currentAttackType++;
            add = Integer.toString(currentAttackType);
        } else {
            currentAttackType = 1;
        }
        setupPartsAnim();
        upperBody.setAnim("Attack_Sword" + add);
        upperBody.setLoopMode(LoopMode.DontLoop);
        upperBody.setSpeed(speed);
    }

    private void setupAnimationController() {
        animationControl = playerModel.getControl(AnimControl.class);

        animationControl.addListener(this);
        setupBodyAnim();
        setupPartsAnim();
        /*
         shootingChannel = animationControl.createChannel();
        
         shootingChannel.addFromRootBone(
         "upper_arm_R");
         * 
         */

    }

    void setupBodyAnim() {
        animationChannel = animationControl.createChannel();

    }

    public void onAnimCycleDone(AnimControl control, AnimChannel channel, String animName) {
        if (channel == shootingChannel) {
            channel.setAnim("stand");
        } else if (channel == upperBody) {
            animationControl.clearChannels();
            setupBodyAnim();

        }
    }

    public void onAnimChange(AnimControl control, AnimChannel channel, String animName) {
    }

    void setupPartsAnim() {

        lowerBody = animationControl.createChannel();
        lowerBody.addFromRootBone("thigh_L");
        lowerBody.addFromRootBone("thigh_R");

        upperBody = animationControl.createChannel();
        upperBody.addFromRootBone("spine");

        /*
         // will blend over 15 seconds to stand
         feet.setAnim("Walk", 15);
         feet.setSpeed(0.25f);
         feet.setLoopMode(LoopMode.Cycle);
        
        
        
         // left hand will pull
         upperBody.addFromRootBone("spine");
         upperBody.setAnim("Magic1");
         upperBody.setSpeed(0.5f);
         upperBody.setLoopMode(LoopMode.Cycle);
         */
    }

    void attachWeapon() {
        //WorldManager worldManager = stageManager.getWorldManager();
        //ParticleEmitter flame = gameEffectManager.createFlame();
        //skeletonControl.getAttachmentsNode("hand_L").attachChild(flame);
        //flame.emitAllParticles();
        //skeletonControl.getAttachmentsNode("hand_L").attachChild(worldManager.createRedBox());
        //Node swordNode = WeaponControl.createSword();
    }

    private void createWeapon() {
        weaponControl = new WeaponControl(character, stageManager);
        playerModel.addControl(weaponControl);
    }

    private void createCharacter() {
        CapsuleCollisionShape capsule = new CapsuleCollisionShape(0.8f, 1f);
        character = new CharacterControl(capsule, 0.5f);
        character.setJumpSpeed(40f);
        playerModel.addControl(character);

        /*
         TrailControl trailControl = new TrailControl(character, stageManager);
         playerModel.addControl(trailControl);
         */
        System.out.println("Add physic control for Character");
        WorldManager worldManager = stageManager.getWorldManager();
        if (worldManager.getWorldSettings().usePhysics) {
            worldManager.getPhysicsSpace().add(character);
            logger.warning("Add physic control for Character");
        } else {
            throw new RuntimeException("To use Character Control you have to support physics!");
        }
    }

    @Override
    public void setSpatial(Spatial spatial) {
        super.setSpatial(spatial);
        playerModel = (Node) spatial;

        //setupAnimationController();
        createCharacter();

    }

    public boolean isOnWater() {
        float y = character.getPhysicsLocation().y;
        if (y > 0.4f && y < 1.6f) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void controlUpdate(float tpf) {
        moveChar(tpf);
        //updateAnimation();
    }

    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {
        //
    }

    @Override
    public Control cloneForSpatial(Spatial spatial) {
        //
        return new RPGCharacterControl(stageManager);
    }

    @Override
    public void setLocation(Vector3f location) {
        character.setPhysicsLocation(location.add(0, 1f, 0));
    }

    @Override
    public void setMoveSpeed(float speed) {
        this.moveSpeed = speed;
    }

    @Override
    public Vector3f getLocation() {
        return character.getPhysicsLocation();
    }
}
