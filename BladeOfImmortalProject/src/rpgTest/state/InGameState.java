/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgTest.state;

import rpgTest.RPGGameStateManager;
import com.jme3.app.Application;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.scene.Node;
import java.util.logging.Logger;
import rpgTest.RPGGameGUIManager;
import rpgTest.RPGMain;
import rpgTest.RPGStageManager;
import sg.atom.core.GameGUIManager;

/**
 *
 * @author cuong.nguyenmanh2
 */
public class InGameState extends AbstractAppState {

    private RPGMain app;
    private Node rootNode;
    private AssetManager assetManager;
    private AppStateManager stateManager;
    private RPGGameStateManager gameStateManager;
    private static final Logger logger = Logger.getLogger(InGameState.class.getName());
    private GameGUIManager gameGUIManager;
    private RPGStageManager stageManager;
    private boolean gamePause;

    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        this.app = (RPGMain) app; // can cast Application to something more specific

        this.rootNode = this.app.getRootNode();
        this.assetManager = this.app.getAssetManager();
        this.stateManager = this.app.getStateManager();
        this.gameStateManager = (RPGGameStateManager) this.app.getGameStateManager();

        if (this.app.getGameGUIManager() == null) {
            this.app.initGUI();
        }
        this.gameGUIManager = this.app.getGameGUIManager();
        if (this.app.getStageManager() == null) {
            this.app.initStage();
        }
        this.stageManager = (RPGStageManager) this.app.getStageManager();
        setEnabled(true);

    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);

        if (enabled) {
            //stageManager.initStage();
            goInGame();
        } else {
            goOutGame();
        }
    }

    void goInGame() {
        getGameGUIManager().goInGame();
        getStageManager().goInGame();
    }

    void pauseGame() {
        getGameGUIManager().pauseGame();
        getStageManager().pauseGame();

    }

    void goOutGame() {
        getGameGUIManager().goOutGame();
        getStageManager().goOutGame();
    }

    @Override
    public void update(float tpf) {
        super.update(tpf);

        // units
        // building
        //miniView
        if (!gamePause) {
            getGameGUIManager().simpleUpdate(tpf);
            getStageManager().updateStage(tpf);
        }
    }

    private void resumeGame() {
        getGameGUIManager().resumeGame();
        getStageManager().resumeGame();
    }

    public RPGGameGUIManager getGameGUIManager() {
        return (RPGGameGUIManager) gameGUIManager;
    }

    public RPGStageManager getStageManager() {
        return (RPGStageManager)stageManager;
    }
    
    
}
