/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgTest.gameplay.rpg;

import java.util.ArrayList;
import sg.atom.gameplay.actor.Actor;

/**
 *
 * @author hungcuong
 */
public class RPGGamePlay {

    public ArrayList<Actor> goodGuys = new ArrayList<Actor>();
    public ArrayList<Actor> badGuys = new ArrayList<Actor>();

    public ArrayList<Actor> getGoodGuys() {
        return goodGuys;
    }

    public ArrayList<Actor> getBadGuys() {
        return badGuys;
    }

    public boolean find(ArrayList<Actor> list, int num) {
        for (int i = 0; i < list.size(); i++) {
            if (num == list.get(i).getId()) {
                return true;
            }
        }

        return false;
    }

    public Actor search(int num) {
        for (int i = 0; i < badGuys.size(); i++) {
            if (num == badGuys.get(i).getId()) {
                return badGuys.get(i);
            }
        }

        for (int i = 0; i < goodGuys.size(); i++) {
            if (num == goodGuys.get(i).getId()) {
                return goodGuys.get(i);
            }
        }

        return null;
    }

    public Actor getActor(String s) {
        for (int i = 0; i < badGuys.size(); i++) {
            if (s.equals(Integer.toString(badGuys.get(i).getId()))) {
                return badGuys.get(i);
            }
        }

        for (int i = 0; i < goodGuys.size(); i++) {
            if (s.equals(Integer.toString(goodGuys.get(i).getId()))) {
                return goodGuys.get(i);
            }
        }

        return null;
    }

    public ArrayList<Actor> getEnemies(int num) {
        if (find(badGuys, num)) {
            return goodGuys;
        }

        return badGuys;
    }

    public ArrayList<Actor> getFriends(int num) {
        if (find(badGuys, num)) {
            return badGuys;
        }

        return goodGuys;
    }
}
