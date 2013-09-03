package rpgTest;

import sg.atom.core.Globals;
import com.jme3.system.AppSettings;
import java.util.logging.Level;
import java.util.logging.Logger;
import rpgTest.state.LoadingState;
import sg.atom.core.AtomMain;

/**
 * test
 *
 * @author normenhansen
 */
public class RPGMain extends AtomMain {

    static RPGMain app;

    static void disableLogging() {
        Logger.getLogger("").setLevel(Level.SEVERE);
    }

    public static void main(String[] args) {
        disableLogging();
        // Set the init setting of an 3D application
        AppSettings settings = new AppSettings(true);
        /*
         try {
        
         settings.load(Main.class.getResourceAsStream("mygame/config/settings.properties"));
        
         } catch (FileNotFoundException ex) {
         Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
         } catch (IOException ex) {
         Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
         }
         * 
         */
        settings.setFrameRate(Globals.SCENE_FPS);
        settings.setSettingsDialogImage("/Interface/Images/splash-small.jpg");
        settings.setTitle("BladeOfUnity");
        settings.setWidth(800);
        settings.setHeight(600);
        // start it up
        app = new RPGMain();
        app.setSettings(settings);
        app.setShowSettings(false);
        app.setPauseOnLostFocus(false);
        app.start();

    }

    @Override
    public void initGUI() {
        gameGUIManager = new RPGGameGUIManager(this);
        gameGUIManager.initGUI();
    }

    @Override
    public void initStage() {
        stageManager = new RPGStageManager(this);
        stageManager.initStage();
    }

    @Override
    public void startup() {
        gameStateManager.setStartupState(LoadingState.class);
        gameStateManager.startUp();
    }

    @Override
    public void simpleInitApp() {
        super.simpleInitApp();
    }

    @Override
    public void initGameStateManager() {
        gameStateManager = new RPGGameStateManager(this);
        gameStateManager.initState();
    }
}
