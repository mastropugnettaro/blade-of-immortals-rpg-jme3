/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgTest.ui;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;
import sg.atom.core.GameGUIManager;
import sg.atom.state.HelloWarningAppState;

/**
 *
 * @author hungcuong
 */
public class UIHelloWarningController implements ScreenController {

    GameGUIManager gameGUIManager;

    public UIHelloWarningController(GameGUIManager gameGUIManager) {
        this.gameGUIManager = gameGUIManager;
    }

    public String getWarningText() {
        String[] warningText = {
            "You need your mom and dad to watch your back, kid!",
            "Blood is RED, the Devil is BLACK, the Moon is DARK and You are DEAD !",
            "Do your homework and enjoy your life ...if you can ..."
        };
        long i = Math.round(Math.random() * warningText.length);
        return warningText[(int) i];
    }

    public void bind(Nifty nifty, Screen screen) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public void onStartScreen() {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public void onEndScreen() {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    // TO DO: The function control the UI must be PUBLIC 
    public void enterGame() {
        //System.out.println("You in !");
        gameGUIManager.getApp().getGameStateManager().enterGameMenu();
    }

    public void quitGame() {
        //System.out.println("You quit , loser !");
        gameGUIManager.getApp().getGameStateManager().quitGame();
    }
}
