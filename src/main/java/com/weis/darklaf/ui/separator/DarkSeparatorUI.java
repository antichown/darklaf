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
package com.weis.darklaf.ui.separator;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicSeparatorUI;
import java.awt.*;

/**
 * @author Jannis Weis
 */
public class DarkSeparatorUI extends BasicSeparatorUI {

    @NotNull
    @Contract("_ -> new")
    public static ComponentUI createUI(final JComponent c) {
        return new DarkSeparatorUI();
    }

    public void paint(final Graphics g, @NotNull final JComponent c) {
        Dimension s = c.getSize();

        g.setColor(UIManager.getDefaults().getColor("Separator.foreground"));
        if (((JSeparator) c).getOrientation() == JSeparator.VERTICAL) {
            g.fillRect(0, 0, 1, s.height);
        } else {
            g.fillRect(0, 0, s.width, 1);
        }
    }

    public Dimension getPreferredSize(final JComponent c) {
        if (((JSeparator) c).getOrientation() == JSeparator.VERTICAL) {
            return new Dimension(1, 0);
        } else {
            return new Dimension(0, 1);
        }
    }

    public Dimension getMinimumSize(final JComponent c) {
        return null;
    }

    public Dimension getMaximumSize(final JComponent c) {
        return null;
    }
}
