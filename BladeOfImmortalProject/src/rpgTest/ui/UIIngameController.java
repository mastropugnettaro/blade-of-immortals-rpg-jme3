/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgTest.ui;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;
import sg.atom.core.GameGUIManager;

/**
 *
 * @author hungcuong
 */
public class UIIngameController implements ScreenController {

    private final GameGUIManager gameGUIManager;

    public UIIngameController(GameGUIManager gameGUIManager) {
        this.gameGUIManager = gameGUIManager;
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
}