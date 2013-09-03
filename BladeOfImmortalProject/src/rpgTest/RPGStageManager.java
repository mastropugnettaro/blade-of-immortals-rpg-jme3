/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgTest;

import java.util.ArrayList;
import rpgTest.gameplay.rpg.RPGGamePlayManager;
import sg.atom.core.AtomMain;
import sg.atom.gameplay.GameLevel;
import sg.atom.stage.StageManager;
import sg.atom.stage.WorldManager;
import sg.atom.world.WorldSettings;

/**
 *
 * @author hungcuong
 */
public class RPGStageManager extends StageManager {

    public RPGStageManager(AtomMain app) {
        super(app);
    }

    @Override
    public void initStage() {
        super.initStage();

    }
    ArrayList<GameLevel> levels = new ArrayList<GameLevel>(4);

    @Override
    public void initStageCustom() {
        this.worldManager = new WorldManager(app, rootNode);
        this.gamePlayManager = new RPGGamePlayManager(app);
        setupLevels();
        this.currentLevel = levels.get(1);

        gamePlayManager.initGamePlay(currentLevel);
        WorldSettings worldSettings = new WorldSettings();
        worldSettings.useTerrainLOD = false;
        worldSettings.useWater = false;
        worldSettings.usePhysics = true;
        worldSettings.useTerrain = true;
        worldManager.initWorld(currentLevel, worldSettings);
    }

    protected void setupLevels() {
        levels.add(new GameLevel(gamePlayManager, worldManager, "Level1", "Scenes/PlatoScene/scene1.j3o"));
        levels.add(new GameLevel(gamePlayManager, worldManager, "Level2", "Scenes/CastleScene/ChurchScene.j3o"));
    }

    @Override
    public void finishStageCustom() {
        gamePlayManager.configGamePlay();

    }

    @Override
    public void updateStageCustom(float tpf) {
        super.updateStageCustom(tpf);
        gamePlayManager.update(tpf);
    }

    public void goInGame() {
        doReadyToPlay();
    }

    public void pauseGame() {
    }

    public void goOutGame() {
    }

    public void resumeGame() {
    }
}
