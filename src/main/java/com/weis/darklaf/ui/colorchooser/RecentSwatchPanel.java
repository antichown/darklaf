package com.weis.darklaf.ui.colorchooser;

import com.weis.darklaf.defaults.DarkColors;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Objects;

/**
 * @author Jannis Weis
 */
class RecentSwatchPanel extends SwatchPanel {
    private Color defaultRecentColor;

    protected void initValues() {
        swatchSize = UIManager.getDimension("ColorChooser.swatchesRecentSwatchSize", getLocale());
        numSwatches = new Dimension(30, 5);
        gap = new Dimension(1, 1);
    }


    protected void initColors() {
        defaultRecentColor = DarkColors.get().getColorChooserDefaultRecentColor();
        int numColors = numSwatches.width * numSwatches.height;

        colors = new Color[numColors];
        for (int i = 0; i < numColors; i++) {
            colors[i] = defaultRecentColor;
        }
    }

    @Override
    public String getToolTipText(@NotNull final MouseEvent e) {
        Color color = getColorForLocation(e.getX(), e.getY());
        if (color == defaultRecentColor || color == null) return null;
        return color.getRed() + ", " + color.getGreen() + ", " + color.getBlue();
    }

    public void setMostRecentColor(final Color c) {
        if (Objects.equals(colors[0], c)) return;
        System.arraycopy(colors, 0, colors, 1, colors.length - 1);
        colors[0] = c;
        repaint();
    }
}
