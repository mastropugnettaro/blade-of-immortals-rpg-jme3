/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgTest.gameplay.rpg;

import com.jme3.input.ChaseCamera;
import com.jme3.input.FlyByCamera;
import com.jme3.input.InputManager;
import com.jme3.renderer.Camera;
import com.jme3.scene.Node;

/**
 *
 * @author hungcuong
 */
public class RPGCamera {
    
    public RPGCamera(Camera cam, FlyByCamera flyCam, Node playerModel, InputManager inputManager) {
        this.cam = cam;
        this.flyCam = flyCam;
        this.playerModel = playerModel;
        this.inputManager = inputManager;
    }
    FlyByCamera flyCam;
    ChaseCamera chaseCam;
    Camera cam;
    Node playerModel;
    InputManager inputManager;
    
    public void setupChaseCamera() {
        flyCam.setEnabled(false);
        chaseCam = new ChaseCamera(cam, playerModel, inputManager);
        chaseCam.setDefaultDistance(8f);
        chaseCam.setZoomSensitivity(0.1f);
        chaseCam.setMinDistance(5.8f);
        chaseCam.setMaxDistance(35f);
        
        chaseCam.setSmoothMotion(true);
    }
}
