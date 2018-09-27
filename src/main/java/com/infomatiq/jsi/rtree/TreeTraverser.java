//  This file is part of JSI.
//
//  This library is free software; you can redistribute it and/or
//  modify it under the terms of the GNU Lesser General Public
//  License as published by the Free Software Foundation; either
//  version 2.1 of the License, or (at your option) any later version.
//
//  This library is distributed in the hope that it will be useful,
//  but WITHOUT ANY WARRANTY; without even the implied warranty of
//  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
//  Lesser General Public License for more details.
//
//  You should have received a copy of the GNU Lesser General Public
//  License along with this library; if not, write to the Free Software
//  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA

package com.infomatiq.jsi.rtree;

import com.infomatiq.jsi.Rectangle;

/**
 * @author Sebastian Kuerten (sebastian@topobyte.de)
 */
public class TreeTraverser {

    private Traversal traversal;

    private RTree rtree;

    /**
     * Create a new Traverser for the denoted tree.
     *
     * @param tree      the tree to traverse.
     * @param traversal the object to execute callbacks at.
     */
    public TreeTraverser(RTree tree, Traversal traversal) {
        this.traversal = traversal;
        rtree = tree;
    }

    /**
     * Execute the traversal.
     */
    public void traverse() {
        traverse(rtree.getRoot(), 0);
    }

    private void traverse(Node node, int level) {
        if (node == null) {
            return;
        }
        Rectangle mbb = node.getMbb();
        traversal.node(mbb);

        if (node.isLeaf()) {
            int nc = node.getEntryCount();
            for (int i = 0; i < nc; i++) {
                int       element = node.getId(i);
                Rectangle box     = node.getEntryMbb(i);
                traversal.element(box, element);
            }
            return;
        }

        int nc = node.getEntryCount();
        for (int i = 0; i < nc; i++) {
            int  id    = node.getId(i);
            Node child = rtree.getNode(id);
            traverse(child, level + 1);
        }
    }

}
