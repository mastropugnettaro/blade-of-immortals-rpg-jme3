/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgTest.gameplay.rpg;

import java.util.HashMap;
import sg.atom.gameplay.GameCharacter;
import sg.atom.gameplay.actor.Actor;

/**
 *
 * @author hungcuong
 */
public class NPCManager {

    private HashMap<Long, Actor> npcList = new HashMap<Long, Actor>();
    private HashMap<Long, String> types = new HashMap<Long, String>();
    long totalId = -1;

    public NPCManager() {
        npcList.put(new Long(1), new GameCharacter("Vilager1"));
        types.put(Long.valueOf(1), "Vilager");
    }

    public long getNewGlobalId() {
        return totalId++;
    }
}
