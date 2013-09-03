/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgTest;

import rpgTest.ui.UIHelloWarningController;
import rpgTest.ui.UIIngameController;
import rpgTest.ui.UIMainMenuController;
import sg.atom.core.GameGUIManager;
import sg.atom.ui.common.UILoadingScreenController;

/**
 *
 * @author cuong.nguyenmanh2
 */
public class RPGGameGUIManager extends GameGUIManager {
    
    public RPGGameGUIManager(RPGMain app) {
        super(app);
    }
    
    @Override
    public void initGUI() {
        super.initGUI();
    }
    
    @Override
    public void setupCommonScreens() {
        nifty.registerScreenController(new UIIngameController(this),
                new UIMainMenuController(this),
                new UIHelloWarningController(this),
                new UILoadingScreenController(this));
        nifty.addXml("Interface/Ingame2.xml");
        nifty.addXml("Interface/MainMenu.xml");
//        nifty.addXml("Interface/HelloWarning.xml");
        nifty.addXml("Interface/MainMenuScreens/GameOptions.xml");
        nifty.addXml("Interface/Loading.xml");
    }
    
    public void goInGame() {
        nifty.gotoScreen("InGameScreen");
        
    }

    public void pauseGame() {
        
    }

    public void goOutGame() {
        
    }

    public void resumeGame() {
        
    }

    public void simpleUpdate(float tpf) {
    }
}
