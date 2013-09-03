/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgTest.gameplay.rpg;

import rpgTest.gameplay.rpg.control.RPGCharacterControl;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.renderer.queue.RenderQueue.ShadowMode;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import java.util.ArrayList;
import sg.atom.ai.steering.DefaultVehicle;
import sg.atom.ai.steering.SteerManager;
import sg.atom.ai.steering.control.PersuitSeparationControl;
import sg.atom.ai.steering.control.SimpleVehicleControl;
import sg.atom.core.AtomMain;
import sg.atom.gameplay.GameLevel;
import sg.atom.gameplay.player.Player;
import sg.atom.stage.GamePlayManager;

/**
 *
 * @author cuong.nguyenmanh2
 */
public class RPGGamePlayManager extends GamePlayManager {
    //character -----------------------------------------------

    Node playerModel;
    Node playerModelFile;
    Node magicFX;
    protected boolean firstPersonView = true;
    RPGCharacterControl characterControl;
    private SteerManager steerManager;
    private DefaultVehicle mainPlayerVehicle;
    private ArrayList<DefaultVehicle> demonVehicles;

    public RPGGamePlayManager(AtomMain app) {
        super(app);
    }

    private void setupKeys() {
        inputManager.addMapping("forward", new KeyTrigger(KeyInput.KEY_U));
        inputManager.addMapping("backward", new KeyTrigger(KeyInput.KEY_J));
        inputManager.addMapping("left", new KeyTrigger(KeyInput.KEY_H));
        inputManager.addMapping("right", new KeyTrigger(KeyInput.KEY_K));
        inputManager.addMapping("jump", new KeyTrigger(KeyInput.KEY_SPACE));
        inputManager.addMapping("reload", new KeyTrigger(KeyInput.KEY_R));
        inputManager.addMapping("shoot", new MouseButtonTrigger(0));
        inputManager.addMapping("zoom", new MouseButtonTrigger(1));
        inputManager.addListener(characterControl, "right");
        inputManager.addListener(characterControl, "jump");
        inputManager.addListener(characterControl, "reload");
        inputManager.addListener(characterControl, "shoot");
        inputManager.addListener(characterControl, "zoom");
        inputManager.addListener(characterControl, "left");
        inputManager.addListener(characterControl, "backward");
        inputManager.addListener(characterControl, "forward");

        inputManager.addMapping("spawnEnemy", new KeyTrigger(KeyInput.KEY_I));
        inputManager.addMapping("spawnFriend", new KeyTrigger(KeyInput.KEY_Y));
        inputManager.addMapping("gamePause", new KeyTrigger(KeyInput.KEY_F3));
        inputManager.addMapping("toggleView", new KeyTrigger(KeyInput.KEY_P));
        inputManager.addListener(commonActionListener, "toggleView");
        inputManager.addListener(commonActionListener, "spawnFriend");
        inputManager.addListener(commonActionListener, "spawnEnemy");
        inputManager.addListener(commonActionListener, "gamePause");
    }

    private void setupKeys2() {
        inputManager.addMapping("wireframe", new KeyTrigger(KeyInput.KEY_T));
        inputManager.addListener(characterControl, "wireframe");
        inputManager.addMapping("CharLeft", new KeyTrigger(KeyInput.KEY_A));
        inputManager.addMapping("CharRight", new KeyTrigger(KeyInput.KEY_D));
        inputManager.addMapping("CharUp", new KeyTrigger(KeyInput.KEY_W));
        inputManager.addMapping("CharDown", new KeyTrigger(KeyInput.KEY_S));
        inputManager.addMapping("CharSpace", new KeyTrigger(KeyInput.KEY_SPACE));
        inputManager.addMapping("CharShoot", new KeyTrigger(KeyInput.KEY_F));
        inputManager.addMapping("CharWeapon", new KeyTrigger(KeyInput.KEY_V));


        inputManager.addListener(characterControl, "CharLeft");
        inputManager.addListener(characterControl, "CharRight");
        inputManager.addListener(characterControl, "CharUp");
        inputManager.addListener(characterControl, "CharDown");
        inputManager.addListener(characterControl, "CharSpace");
        inputManager.addListener(characterControl, "CharShoot");
        inputManager.addListener(characterControl, "CharWeapon");

        // FOR MOUSE
        inputManager.addMapping("shoot", new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
        inputManager.addListener(commonActionListener, "shoot");
    }
    private ActionListener commonActionListener = new ActionListener() {
        public void onAction(String name, boolean keyPressed, float tpf) {
            if (name.equals("toggleView") && !keyPressed) {
                firstPersonView = !firstPersonView;
            }

            if (name.equals("spawnEnemy") && !keyPressed) {
                //getEnemies(id).add(new Ghoul(currentStage, worldManager));
            }

            if (name.equals("spawnFriend") && !keyPressed) {
                //getFriends(id).add(new Ghoul(currentStage, worldManager));
            }

            if (name.equals("shoot") && !keyPressed) {
                Camera cam = stageManager.getCurrentActiveCamera();
                Node shootables = currentLevel.getLevelNode();
            }
        }
    };

    @Override
    public void startLevel(GameLevel level) {
        Vector3f startPos = currentLevel.getStartPos();
        currentLevel.getLevelNode().attachChild(mainPlayer.getPlayerModel());
        //mainPlayer.getPlayerModel().setLocalScale(0.04f);
        //mainPlayer.getPlayerModel().setLocalTranslation(0, -0.8f, 0);
        currentLevel.getStartPos().set(10f, 10, 10f);
        mainPlayer.getCharacterControl().setLocation(new Vector3f(0f, 100f, 0f));
        steerManager = new SteerManager(this.stageManager.getApp());
        createRandomDemon();
    }

    @Override
    public void update(float tpf) {
        super.update(tpf);

        /*
         for (DefaultVehicle aVehicle:demonVehicles){
            
         }
         */
    }

    void configEntities() {
        // Config Entity Manager
/*
         stageManager.getEntityManager().addType("Player");
         stageManager.getEntityManager().addType("house");
         Spatial church = SceneGraphHelper.findSpatialByName("Church", currentLevel.getLevelNode(), false);
         stageManager.getEntityManager().addEntity(church, "house");
         stageManager.getEntityManager().addEntity(mainPlayer.getPlayerModel(), "Player");
         church.addControl(new PickingControl(worldManager));
         */
    }

    @Override
    public void configGamePlay() {

        //playerModel = (Node) SceneGraphHelper.getSpatialByPath("Armature/Body", playerModelFile);
        playerModel = playerModelFile;
        //playerModel.attachChild(magicFX);
        playerModel.setShadowMode(ShadowMode.Cast);
        //magicFX.setLocalScale(0.01f, 0.01f, 0.01f);
        //magicFX.setLocalTranslation(0f, 0.4f, 0f);
        characterControl = new RPGCharacterControl(stageManager);
        mainPlayer = new Player(stageManager, "Kreyna");
        mainPlayer.initPlayer(playerModel, characterControl);
        mainPlayerVehicle = new DefaultVehicle(steerManager);
        SimpleVehicleControl sControl = new SimpleVehicleControl(steerManager);
        sControl.setVehicle(mainPlayerVehicle);
        sControl.setSpatialToVehicle(true);
        mainPlayer.getModelNode().addControl(sControl);

        RPGCamera rpgCam = new RPGCamera(stageManager.getCurrentActiveCamera(), flyCam, playerModel, inputManager);
        rpgCam.setupChaseCamera();
        setupKeys2();
    }

    @Override
    public void loadGamePlay() {
        // load all GameCharacter models
        // WorldManager will help but
        // for simple case , this 's enough
        //playerModelFile = (Node) assetManager.loadModel("Models/Character/MainCharacter/girlFight2 fix.j3o");

        playerModelFile = (Node) assetManager.loadModel("Models/Character/Shahdee/Shahdee.j3o");
        /*
         magicFX = (Node) assetManager.loadModel("Models/Magic/MagicFx.j3o");
         */

        //playerModelFile = (Node) assetManager.loadModel("");

    }

    public void createRandomDemon() {
        Spatial orgDemon = assetManager.loadModel("Models/Character/NPC/Demon1/demon1.j3o");
        orgDemon.setShadowMode(ShadowMode.Cast);
        int num = 7;


        for (int i = 0; i < num; i++) {
            float x = FastMath.nextRandomFloat() * 50;
            float z = FastMath.nextRandomFloat() * 50;

            Spatial cloneDemon = orgDemon.clone();
            cloneDemon.setLocalScale(0.15f);
            cloneDemon.setLocalTranslation(x, 0f, z);
            currentLevel.getLevelNode().attachChild(cloneDemon);


            DefaultVehicle aVehicle = new DefaultVehicle(steerManager);
            aVehicle.speed = 8f;
            aVehicle.maxTurnForce = 4f;
            aVehicle.maxSpeed = 8f;

            aVehicle.setLocation(new Vector3f(x, 0f, z));
            PersuitSeparationControl fControl = new PersuitSeparationControl(steerManager, mainPlayerVehicle);
            fControl.setRadius(10f);
            fControl.setVehicle(aVehicle);
            steerManager.addVehicle(aVehicle);
            cloneDemon.addControl(fControl);


        }

    }
}
