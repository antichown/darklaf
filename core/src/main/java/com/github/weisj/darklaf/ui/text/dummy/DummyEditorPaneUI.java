/*
 * MIT License
 *
 * Copyright (c) 2021 Jannis Weis
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package com.github.weisj.darklaf.ui.text.dummy;

import java.beans.PropertyChangeEvent;

import javax.swing.plaf.basic.BasicEditorPaneUI;
import javax.swing.text.JTextComponent;

public class DummyEditorPaneUI extends BasicEditorPaneUI implements DummyTextUIMethods {

    @Override
    protected void installDefaults() {}

    @Override
    protected void installListeners() {}

    @Override
    protected void modelChanged() {}

    @Override
    protected void installKeyboardActions() {}

    @Override
    public void installKeyBoardActionsReal() {
        super.installKeyboardActions();
    }

    @Override
    public void installUI(final JTextComponent editor) {
        super.installUI(editor);
    }

    @Override
    public void propertyChange(final PropertyChangeEvent evt) {
        super.propertyChange(evt);
    }

    @Override
    @SuppressWarnings("EmptyCatch")
    public void uninstallUI(final JTextComponent editor) {
        try {
            super.uninstallUI(editor);
        } catch (UninstallBlockedException ignored) {
        }
    }

    @Override
    protected void uninstallKeyboardActions() {}

    @Override
    protected void uninstallListeners() {
        // This avoids unsetting the `editor` variable in `BasicTextUI`.
        throw new UninstallBlockedException();
    }

    private static class UninstallBlockedException extends RuntimeException {
    }
}
