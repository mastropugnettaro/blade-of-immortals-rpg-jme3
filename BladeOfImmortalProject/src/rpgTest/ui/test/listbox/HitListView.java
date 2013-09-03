/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgTest.ui.test.listbox;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.controls.ListBox.ListBoxViewConverter;
import de.lessvoid.nifty.effects.Effect;
import de.lessvoid.nifty.effects.EffectEventId;
import de.lessvoid.nifty.effects.impl.Move;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.elements.render.ImageRenderer;
import de.lessvoid.nifty.elements.render.TextRenderer;
import de.lessvoid.nifty.render.NiftyImage;
import java.util.logging.Logger;

/**
 *
 * @author hungcuong
 */
public class HitListView implements ListBoxViewConverter<HitEntryModelClass> {

    private static final String ICON = "#magic-icon";
    private static final String TEXT = "#text";

    /**
     * Default constructor.
     */
    public HitListView() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void display(final Element listBoxItem, final HitEntryModelClass item) {

        final Element text = listBoxItem.findElementByName(TEXT);
        final TextRenderer textRenderer = text.getRenderer(TextRenderer.class);
        final Element icon = listBoxItem.findElementByName(ICON);
        final ImageRenderer iconRenderer = icon.getRenderer(ImageRenderer.class);
        if (item != null) {
            textRenderer.setText(item.getLabel());
            if (item.getIcon() != null) {
                iconRenderer.setImage(item.getIcon());
            } else {
                Nifty nifty = listBoxItem.getNifty();
                NiftyImage newImage = nifty.getRenderEngine().createImage(null,"Interface/Images/Icons/ice-jade-1.png", false);
                iconRenderer.setImage(newImage);
            }
        } else {
            textRenderer.setText("");
            iconRenderer.setImage(null);
        }
        for (Effect ef : listBoxItem.getEffects(EffectEventId.onActive, Move.class)) {
            Logger.getLogger(this.getClass().getName()).info(" ef : " + ef.getStateString());
            //ef.setActive(true);
        }
        //((Label) listBoxItem).setText(item.getLabel());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final int getWidth(final Element listBoxItem, final HitEntryModelClass item) {

        final Element text = listBoxItem.findElementByName(TEXT);
        final TextRenderer textRenderer = text.getRenderer(TextRenderer.class);
        final Element icon = listBoxItem.findElementByName(ICON);
        final ImageRenderer iconRenderer = icon.getRenderer(ImageRenderer.class);
        int textWidth = ((textRenderer.getFont() == null) ? 0 : textRenderer.getFont().getWidth(item.getLabel()));
        int iconWidth = ((item.getIcon() == null) ? 0 : item.getIcon().getWidth());

        return textWidth + iconWidth;

    }
}
