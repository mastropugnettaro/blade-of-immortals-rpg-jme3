/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgTest.ui.test.listbox;

import de.lessvoid.nifty.builder.ImageBuilder;
import de.lessvoid.nifty.render.NiftyImage;

/**
 *
 * @author hungcuong
 */
/**
 * Handles a line in the chat controller. This can be either a chat line or an
 * entry in the list of players.
 * 
 * @author Mark
 * @version 0.1
 */
public final class HitEntryModelClass {

    private String label;
    private NiftyImage icon;

    /**
     * Constructor excepting the line and the icon.
     * @param labelParam The label to put in the entry. This can be either a chat line or a player name.
     * @param iconParam The icon to display in the entry, this one is optional.
     */
    public HitEntryModelClass(final String labelParam, final NiftyImage iconParam) {
        this.label = labelParam;
        this.icon = iconParam;
    }

    public HitEntryModelClass(final String labelParam) {
        this.label = labelParam;
        this.icon = null;
    }

    /**
     * Return the supplied label. This can be either a chat line or a player name.
     * @return The supplied label.
     */
    public String getLabel() {
        return label;
    }

    /**
     * Return the supplied icon.
     * @return The supplied icon.
     */
    public NiftyImage getIcon() {
        return icon;
    }
}
