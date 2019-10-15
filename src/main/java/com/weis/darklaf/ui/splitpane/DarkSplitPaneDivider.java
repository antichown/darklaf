/*
 * MIT License
 *
 * Copyright (c) 2019 Jannis Weis
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.weis.darklaf.ui.splitpane;

import com.weis.darklaf.defaults.DarkIcons;
import com.weis.darklaf.icons.EmptyIcon;
import org.jetbrains.annotations.Contract;

import javax.swing.*;
import javax.swing.plaf.UIResource;
import javax.swing.plaf.basic.BasicSplitPaneDivider;
import javax.swing.plaf.basic.BasicSplitPaneUI;
import java.awt.*;

/**
 * @author Jannis Weis
 */
public class DarkSplitPaneDivider extends BasicSplitPaneDivider {

    public DarkSplitPaneDivider(final BasicSplitPaneUI ui) {
        super(ui);
    }


    @Override
    public void paint(final Graphics g) {
        super.paint(g);
        if (splitPane.getOrientation() == JSplitPane.VERTICAL_SPLIT) {
            Icon icon = getVerticalSplitIcon();
            icon.paintIcon(this, g, (getWidth() - icon.getIconWidth()) / 2,
                           (getHeight() - icon.getIconHeight()) / 2);
        } else {
            Icon icon = getHorizontalSplitIcon();
            icon.paintIcon(this, g, (getWidth() - icon.getIconWidth()) / 2,
                           (getHeight() - icon.getIconHeight()) / 2);
        }
    }

    protected Icon getVerticalSplitIcon() {
        return DarkIcons.get().getSplitPaneDividerVerticalSplit();
    }

    protected Icon getHorizontalSplitIcon() {
        return DarkIcons.get().getSplitPaneDividerHorizontalSplit();
    }

    @Override
    protected JButton createLeftOneTouchButton() {
        return new OneTouchLeftButton();
    }

    @Override
    protected JButton createRightOneTouchButton() {
        return new OneTouchRightButton();
    }

    protected Icon getLeftOneTouchIcon() {
        return DarkIcons.get().getSplitPaneDividerLeftOneTouch();
    }

    protected Icon getRightOneTouchIcon() {
        return DarkIcons.get().getSplitPaneDividerRightOneTouch();
    }

    protected Icon getTopOneTouchIcon() {
        return DarkIcons.get().getSplitPaneDividerTopOneTouch();
    }

    protected Icon getBottomOneTouchIcon() {
        return DarkIcons.get().getSplitPaneDividerBottomOneTouch();
    }

    protected static class OneTouchButton extends JButton implements UIResource {
        protected OneTouchButton() {
            putClientProperty("JButton.variant", "onlyLabel");
            setMinimumSize(new Dimension(ONE_TOUCH_SIZE, ONE_TOUCH_SIZE));
            setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            setRequestFocusEnabled(false);
            setBorderPainted(false);
            setFocusPainted(false);
        }

        @Contract(pure = true)
        @SuppressWarnings("deprecation")
        public boolean isFocusTraversable() {
            return false;
        }

        @Override
        public Icon getPressedIcon() {
            return getIcon();
        }

        @Override
        public Icon getDisabledIcon() {
            return getIcon();
        }
    }

    protected class OneTouchRightButton extends OneTouchButton {
        @Override
        public Icon getIcon() {
            if (splitPane != null) {
                return splitPane.getOrientation() == JSplitPane.HORIZONTAL_SPLIT
                       ? getRightOneTouchIcon() : getBottomOneTouchIcon();
            }
            return EmptyIcon.create(0);
        }
    }

    protected class OneTouchLeftButton extends OneTouchButton {
        @Override
        public Icon getIcon() {
            if (splitPane != null) {
                return splitPane.getOrientation() == JSplitPane.HORIZONTAL_SPLIT
                       ? getLeftOneTouchIcon() : getTopOneTouchIcon();
            }
            return EmptyIcon.create(0);
        }
    }
}
