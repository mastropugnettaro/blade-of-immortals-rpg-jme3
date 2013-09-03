/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgTest.ui.test.listbox;

import com.jme3.app.SimpleApplication;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.niftygui.NiftyJmeDisplay;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.NiftyEventSubscriber;
import de.lessvoid.nifty.controls.ListBox;
import de.lessvoid.nifty.controls.ListBoxSelectionChangedEvent;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hungcuong
 */
public class TestListBox extends SimpleApplication implements ScreenController, ActionListener {

    Nifty nifty;

    public static void main(String[] args) {
        Logger.getLogger("com.jme3").setLevel(Level.WARNING);
        Logger.getLogger("de.lessvoid").setLevel(Level.WARNING);
        TestListBox app = new TestListBox();
        app.start();



    }
    boolean debugMode = false;
    float lastTime = 0;
    /**Use ActionListener to respond to pressed/released inputs (key presses, mouse clicks) */
    private ActionListener actionListener = new ActionListener() {

        public void onAction(String name, boolean pressed, float tpf) {
            if (name.equals("NiftyDebugMode")) {
                nifty.setDebugOptionPanelColors(debugMode);
                /*
                if (lastTime < getTimer().getTimeInSeconds() - 0.5) {
                debugMode = !debugMode;
                lastTime = getTimer().getTimeInSeconds();
                }
                 * 
                 */
            }
        }
    };
    /** Use AnalogListener to respond to continuous inputs (key presses, mouse clicks) */
    private AnalogListener analogListener = new AnalogListener() {

        public void onAnalog(String name, float value, float tpf) {
            System.out.println(name + " = " + value);
        }
    };

    void initNifty() {
        //just some text
        NiftyJmeDisplay niftyDisplay = new NiftyJmeDisplay(getAssetManager(),
                getInputManager(),
                getAudioRenderer(),
                getGuiViewPort());

        nifty = niftyDisplay.getNifty();
        //this.assetManager.registerLocator("src/jme3test/animation/", FileLocator.class); // default

        nifty.fromXml("Interface/MyListBoxControl.xml", "start");

        //nifty.setDebugOptionPanelColors(true);
        guiViewPort.addProcessor(niftyDisplay);
    }

    void initInput() {
        /** Map one or more inputs to an action */
        inputManager.addMapping("NiftyDebugMode",
                new KeyTrigger(KeyInput.KEY_D));
        /** Add an action to one or more listeners */
        inputManager.addListener(actionListener, "NiftyDebugMode");

    }

    @Override
    public void simpleInitApp() {
        initNifty();
        initInput();
        setupKeys();
    }
    /**
     * Fill the listbox with items. In this case with Strings.
     */
    ListBox<HitEntryModelClass> listBox;

    public void fillMyListBox(Screen screen) {
        listBox = (ListBox<HitEntryModelClass>) screen.findNiftyControl("myCustomListBox", ListBox.class);

        listBox.addItem(new HitEntryModelClass("+180"));
        listBox.addItem(new HitEntryModelClass("+400"));
        listBox.addItem(new HitEntryModelClass("+800"));

        listBox.refresh();
    }

    /**
     * When the selection of the ListBox changes this method is called.
     */
    @NiftyEventSubscriber(id = "myCustomListBox")
    public void onMyListBoxSelectionChanged(final String id, final ListBoxSelectionChangedEvent<String> event) {
        /*
        List<String> selection = event.getSelection();
        for (String selectedItem : selection) {
        System.out.println("listbox selection [" + selectedItem + "]");
        }
         */
    }

    private void setupKeys() {

        inputManager.addMapping("AddMore", new KeyTrigger(KeyInput.KEY_SPACE));
        inputManager.addListener(this, "AddMore");

    }

    public void onAction(String binding, boolean value, float tpf) {
        if (binding.equals("AddMore")) {
            int number = (int) Math.round(Math.random() * 100);
            screen = nifty.getCurrentScreen();
            listBox = (ListBox<HitEntryModelClass>) screen.findNiftyControl("myCustomListBox", ListBox.class);
            listBox.addItem(new HitEntryModelClass("+" + number));
            listBox.refresh();
            Logger.getLogger(this.getClass().getName()).info(" num : " + number);
        }
    }
    Screen screen;

    public void bind(Nifty nifty, Screen screen) {
        fillMyListBox(screen);
        this.screen = screen;
    }

    public void onStartScreen() {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public void onEndScreen() {
        //throw new UnsupportedOperationException("Not supported yet.");
    }
}
