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
package com.github.weisj.darklaf.components.treetable.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DefaultTreeTableNode implements TreeTableNode {

    private final List<TreeTableNode> children;
    private final TreeTableNode parent;
    private final List<Object> columns;

    public DefaultTreeTableNode(final TreeTableNode parent, final Object[] columns) {
        this(parent, Arrays.asList(columns));
    }

    public DefaultTreeTableNode(final TreeTableNode parent, final List<Object> columns) {
        this.parent = parent;
        this.columns = columns;
        this.children = new ArrayList<>();
    }

    @Override
    public List<TreeTableNode> getChildren() {
        return children;
    }

    @Override
    public TreeTableNode getParent() {
        return parent;
    }

    @Override
    public boolean getAllowsChildren() {
        return true;
    }

    public void addChild(final TreeTableNode child) {
        children.add(child);
    }

    public void removeChild(final TreeTableNode child) {
        children.remove(child);
    }

    @Override
    public String toString() {
        return getTreeValue().toString();
    }

    @Override
    public List<Object> getColumns() {
        return columns;
    }
}
