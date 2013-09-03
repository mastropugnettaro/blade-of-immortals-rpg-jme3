/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgTest;

import java.util.logging.Level;
import java.util.logging.Logger;
import rpgTest.state.InGameState;
import rpgTest.state.LoadingState;
import rpgTest.state.MainMenuState;
import sg.atom.core.AtomMain;
import sg.atom.core.GameStateManager;

/**
 *
 * @author cuong.nguyenmanh2
 */
public class RPGGameStateManager extends GameStateManager {

    Logger logger = Logger.getLogger(RPGGameStateManager.class.getName());

    public RPGGameStateManager(AtomMain app) {
        super(app);
    }

    public void goInGame() {
        LoadingState loadingState = stateManager.getState(LoadingState.class);
        boolean detached = stateManager.detach(loadingState);
        stateManager.attach(new InGameState());
        logger.log(Level.INFO, "Detach Loading State");
    }

    @Override
    public void loadGame() {
        MainMenuState menuState = stateManager.getState(MainMenuState.class);
        boolean detached = stateManager.detach(menuState);
        stateManager.attach(new LoadingState());
    }
}