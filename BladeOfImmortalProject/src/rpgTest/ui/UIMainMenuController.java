/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgTest.ui;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;
import java.util.logging.Logger;
import sg.atom.ui.GameGUIManager;

/**
 *
 * @author hungcuong
 */
public class UIMainMenuController implements ScreenController {

    private final GameGUIManager gameGUIManager;

    public UIMainMenuController(GameGUIManager gameGUIManager) {
        this.gameGUIManager = gameGUIManager;
    }

    public void bind(Nifty nifty, Screen screen) {
        Logger.getLogger(this.getClass().getName()).info("MainMenuUI");
    }

    public void onStartScreen() {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public void onEndScreen() {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public void runGame() {
        // Say click Run game
        System.out.print("click Run game");
        gameGUIManager.getApp().getGameStateManager().loadGame();
    }

    public void showOptions() {
        // 
        gameGUIManager.loadAndGotoScreen("options");
    }

    public void quitGame() {
        //System.out.println("You quit , loser !");
        gameGUIManager.getApp().getGameStateManager().quitGame();
    }
}
