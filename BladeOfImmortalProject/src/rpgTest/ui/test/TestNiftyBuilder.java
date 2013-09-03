/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgTest.ui.test;

import com.jme3.app.SimpleApplication;
import com.jme3.niftygui.NiftyJmeDisplay;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.builder.EffectBuilder;
import de.lessvoid.nifty.builder.HoverEffectBuilder;
import de.lessvoid.nifty.builder.ImageBuilder;
import de.lessvoid.nifty.builder.LayerBuilder;
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.builder.ScreenBuilder;
import de.lessvoid.nifty.builder.TextBuilder;
import de.lessvoid.nifty.controls.Draggable;
import de.lessvoid.nifty.controls.Droppable;
import de.lessvoid.nifty.controls.DroppableDropFilter;
import de.lessvoid.nifty.controls.button.builder.ButtonBuilder;
import de.lessvoid.nifty.controls.dragndrop.DroppableControl;
import de.lessvoid.nifty.controls.dragndrop.builder.DraggableBuilder;
import de.lessvoid.nifty.controls.dragndrop.builder.DroppableBuilder;
import de.lessvoid.nifty.controls.label.builder.LabelBuilder;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;
import de.lessvoid.nifty.tools.Color;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestNiftyBuilder extends SimpleApplication implements ScreenController, DroppableDropFilter {
    
    private Nifty nifty;
    
    public static void main(String[] args) {
        TestNiftyBuilder app = new TestNiftyBuilder();
        app.showSettings = false;
        app.setPauseOnLostFocus(false);
        app.start();
        Logger.getLogger("de.lessvoid").setLevel(Level.WARNING);
    }
    int i = 0, j = 0;
    
    public void simpleInitApp() {
        NiftyJmeDisplay niftyDisplay = new NiftyJmeDisplay(assetManager,
                inputManager,
                audioRenderer,
                guiViewPort);
        nifty = niftyDisplay.getNifty();
        nifty.loadStyleFile("nifty-default-styles.xml");
        nifty.loadControlFile("nifty-default-controls.xml");
// create nifty
        nifty.registerEffect("customHint", "rpgTest.ui.test.CustomHint");
        
        
        nifty.addScreen("start", makeScreen1(nifty));
        nifty.gotoScreen("start");

        // attach the nifty display to the gui view port as a processor
        guiViewPort.addProcessor(niftyDisplay);

        // disable the fly cam
        flyCam.setEnabled(false);
    }
    DragAndDropDialogController dragController;
    
    public Screen makeScreen2(final Nifty nifty) {
        dragController = new DragAndDropDialogController();
        Screen screen = new ScreenBuilder("start") {
            
            {
                
                inputMapping("de.lessvoid.nifty.input.mapping.DefaultInputMapping"); // this will enable Keyboard events for the screen controller
                controller(new TestNiftyBuilder());
                
                layer(new LayerBuilder("layer") {
                    
                    {
                        backgroundColor("#003f");
                        childLayoutVertical();
                        controller(dragController);
                        control(new LabelBuilder("dragAndDropDescription", "Drop the Key on the Chest to open it.") {
                            
                            {
                                style("nifty-label");
                                height("45px");
                                width("100%");
                                textHAlignCenter();
                            }
                        });
                        
                        panel(new PanelBuilder() {
                            
                            {
                                height("300px");
                                childLayoutHorizontal();
                                control(new DroppableBuilder("chest") {
                                    
                                    {
                                        width("101px");
                                        height("171px");
                                        panel(new PanelBuilder() {
                                            
                                            {
                                                childLayoutOverlay();
                                                image(new ImageBuilder("chest-image") {
                                                    
                                                    {
                                                        filename("Interface/Test/dragndrop/Chest Open.png");
                                                    }
                                                });
                                                image(new ImageBuilder("chest-open") {
                                                    
                                                    {
                                                        filename("Interface/Test/dragndrop/Chest Lid.png");
                                                        onCustomEffect(new EffectBuilder("move") {
                                                            
                                                            {
                                                                effectParameter("mode", "toOffset");
                                                                effectParameter("offsetY", "-100");
                                                                length(250);
                                                                customKey("switchOpen");
                                                                neverStopRendering(true);
                                                            }
                                                        });
                                                        onCustomEffect(new EffectBuilder("fade") {
                                                            
                                                            {
                                                                effectParameter("start", "#f");
                                                                effectParameter("end", "#0");
                                                                length(250);
                                                                customKey("switchOpen");
                                                                neverStopRendering(true);
                                                            }
                                                        });
                                                    }
                                                });
                                            }
                                        });
                                    }
                                });
                                control(new DroppableBuilder("key-initial") {
                                    
                                    {
                                        width("101px");
                                        height("171px");
                                        onActiveEffect(new EffectBuilder("border") {
                                            
                                            {
                                                effectParameter("color", "#0003");
                                            }
                                        });
                                        control(new DraggableBuilder("key") {
                                            
                                            {
                                                childLayoutCenter();
                                                image(new ImageBuilder() {
                                                    
                                                    {
                                                        filename("Interface/Test/dragndrop/Key.png");
                                                    }
                                                });
                                            }
                                        });
                                    }
                                });
                            }
                        });
                        panel(new PanelBuilder() {
                            
                            {
                                height("200px");
                                width("100%");
                                childLayoutCenter();
                                control(new ButtonBuilder("resetButton", "Reset") {
                                    
                                    {
                                        alignCenter();
                                    }
                                });
                            }
                        });
                        
                        
                    }
                });
            }
        }.build(nifty);
        
        return (screen);
    }
    
    public Screen makeScreen1(final Nifty nifty) {

        // create a screen
        Screen screen = new ScreenBuilder("start") {
            
            {
                controller(TestNiftyBuilder.this);
                
                layer(new LayerBuilder("layer") {
                    
                    {
                        backgroundColor("#003f");
                        childLayoutCenter();
                        
                        panel(new PanelBuilder() {
                            
                            {
                                id("panel");
                                childLayoutCenter();
                                height("450px");
                                width("80%");
                                alignCenter();
                                valignCenter();
                                padding("10px");
                                backgroundColor("#460f");
                                visibleToMouse();
                                onStartScreenEffect(new EffectBuilder("move") {
                                    
                                    {
                                        effectParameter("mode", "in");
                                        effectParameter("direction", "top");
                                        length(300);
                                        startDelay(0);
                                        inherit(true);
                                    }
                                });
                                
                                onEndScreenEffect(new EffectBuilder("move") {
                                    
                                    {
                                        effectParameter("mode", "out");
                                        effectParameter("direction", "bottom");
                                        length(300);
                                        startDelay(0);
                                        inherit(true);
                                    }
                                });
                                
                                
                                
                                panel(new PanelBuilder() {
                                    
                                    {
                                        padding("4px,4px,4px,4px");
                                        childLayoutVertical();
                                        alignCenter();
                                        valignCenter();
                                        width("100%");
                                        height("400px");
                                        backgroundColor("#111f");
                                        for (i = 0; i < 5; i++) {
                                            panel(new PanelBuilder("row" + i) {
                                                
                                                {
                                                    childLayoutHorizontal();
                                                    width("100%");
                                                    height("80px");
                                                    for (j = 0; j < 5; j++) {
                                                        panel(new PanelBuilder("itemPading") {
                                                            
                                                            {
                                                                childLayoutCenter();
                                                                width("20%");
                                                                height("100%");
                                                                padding("4px,4px,4px,4px");
                                                                backgroundColor("#444f");
                                                                visibleToMouse();
                                                                interactOnClick("selectItem()");
                                                                onHoverEffect(new HoverEffectBuilder("pulsate") {
                                                                    
                                                                    {
                                                                        effectParameter("scaleFactor", "0.008");
                                                                        effectParameter("startColor", "#f600");
                                                                        effectParameter("endColor", "#ffff");
                                                                        post(true);
                                                                    }
                                                                });
                                                                
                                                                control(new DroppableBuilder("item" + i + "_" + j) {
                                                                    
                                                                    {
                                                                        
                                                                        if ((i * 5 + j) % 2 == 0) {
                                                                            control(new DraggableBuilder("key") {
                                                                                
                                                                                {
                                                                                    
                                                                                    childLayoutCenter();
                                                                                    image(new ImageBuilder() {
                                                                                        
                                                                                        {
                                                                                            height("100%");
                                                                                            width("100%");
                                                                                            filename("Interface/Test/dragndrop/Key.png");
                                                                                        }
                                                                                    });
                                                                                    
                                                                                    onHoverEffect(new HoverEffectBuilder("customHint"){
                                                                                        {
                                                                                            effectParameter("startDelay", "2000");
                                                                                            effectParameter("targetElement", "hintPanel");
                                                                                            effectParameter("hintText", "This is good");
                                                                                        }
                                                                                    });
                                                                                }
                                                                            });
                                                                        }
                                                                    }
                                                                });
                                                                
                                                            }
                                                        });
                                                    }
                                                }
                                            });
                                        }
                                        
                                        text(new TextBuilder() {
                                            
                                            {
                                                text("Hello Nifty Builder World!!!");
                                                font("aurulent-sans-16.fnt");
                                                color("#000f");
                                                width("*");
                                                alignCenter();
                                                valignCenter();
                                            }
                                        });
                                    }
                                });
                            }
                        });
                    }
                });
                
                layer(new LayerBuilder("hintLayer") {
                    
                    {

                        childLayoutAbsoluteInside();
                        panel(new PanelBuilder("hintPanel") {
                            
                            {
                        width("400px");
                        height("400px");
                                backgroundColor("#0009");
                                childLayoutVertical();
                                panel(new PanelBuilder("info") {
                                    
                                    {
                                        height("80px");
                                        childLayoutHorizontal();
                                        
                                        text(new TextBuilder("content") {
                                            
                                            {
                                                width("50%");
                                                text("Info");
                                                font("aurulent-sans-16.fnt");
                                                color("#ffff");
                                                alignCenter();
                                                valignCenter();
                                            }
                                        });
                                        
                                        image(new ImageBuilder() {
                                            
                                            {
                                                
                                                width("50%");
                                                filename("Interface/Test/dragndrop/Key.png");
                                            }
                                        });
                                    }
                                });
                                
                                panel(new PanelBuilder("panel2") {

                                    {
                                        childLayoutVertical();
                                        text(new TextBuilder("long info") {

                                            {
                                                font("aurulent-sans-16.fnt");
                                                color("#ffff");
                                                wrap(true);
                                                text("Long info");
                                            }
                                        });
                                    }
                                });
                            }
                        });
                    }
                });
            }
        }.build(nifty);
        
        return (screen);
    }
    
    @Override
    public void bind(final Nifty nifty, final Screen screen) {
        System.out.println("bind()");
        this.nifty = nifty;
        
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                Element e = screen.findElementByName("item" + i + "_" + j);
                DroppableControl drroppableControl = e.getControl(DroppableControl.class);
                drroppableControl.addFilter(this);
            }
        }
        
    }
    
    @Override
    public void onStartScreen() {
        System.out.println("onStartScreen()");
    }
    
    @Override
    public void onEndScreen() {
        System.out.println("onEndScreen()");
    }
    
    public void quit() {
        nifty.exit();
    }
    
    public void selectItem() {
        System.out.println("you select item!");
    }
    
    String getIconsPath(int index) {
        
        String[] map = {"beam-jade-1.png", "beam-jade-2.png", "beam-jade-3.png"};
        String rootPath = "Interface/Images/Icons/";
        
        return rootPath + map[index % map.length];
    }
    
    public boolean accept(Droppable dropSource, Draggable draggedItem, Droppable droppedAt) {
        if (isFree(droppedAt)) {
            return true;
            
        } else {
            return false;
        }
    }
    
    boolean isFree(Droppable dropSource) {
        if (dropSource.getElement().getElements().size() > 1) {
            return false;
            
        } else {
            return true;
        }
    }
}
