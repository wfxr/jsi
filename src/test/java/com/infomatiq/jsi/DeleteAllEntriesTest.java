//   DeleteAllEntriesTest.java
//   Java Spatial Index Library
//   Copyright (C) 2010 aled@sourceforge.net
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
//  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307 USA

package com.infomatiq.jsi;

import com.infomatiq.jsi.rtree.RTree;
import gnu.trove.procedure.TIntProcedure;
import junit.framework.TestCase;

import java.util.Random;

public class DeleteAllEntriesTest extends TestCase {

    Rectangle[] rects = null;

    public DeleteAllEntriesTest(String name) {
        super(name);
    }

    ;

    public void testDeleteAllEntries() {
        System.out.println("testDeleteAllEntries");

        int numRects = 500;

        rects = new Rectangle[numRects];
        Random r = new Random();
        r.setSeed(0);
        for (int i = 0; i < numRects; i += 1) {
            rects[i] = new Rectangle(r.nextDouble(), r.nextDouble(), r.nextDouble(),
                                     r.nextDouble());
        }

        run(1, 2, numRects);
        run(1, 3, numRects);
        run(2, 4, numRects);
        run(2, 5, numRects);
        run(2, 6, numRects);
    }

    private void run(int minNodeEntries, int maxNodeEntries, int numRects) {
        RTree rtree = new RTree(minNodeEntries, maxNodeEntries);

        for (int i = 0; i <= numRects; i += 100) {
            // add some entries
            for (int j = 0; j < i; j++) {
                rtree.add(rects[j], j);
            }
            assertTrue(rtree.checkConsistency());

            // now delete them all
            for (int j = 0; j < i; j++) {
                rtree.delete(rects[j], j);
            }
            assertTrue(rtree.size() == 0);
            assertTrue(rtree.checkConsistency());

            // check that we can make queries on an empty rtree without error.
            Rectangle testRect  = new Rectangle(1, 2, 3, 4);
            Point     testPoint = new Point(1, 2);

            Counter counter = new Counter();
            rtree.intersects(testRect, counter);
            assertTrue(counter.count == 0);

            rtree.nearest(testPoint, counter, Double.MAX_VALUE);
            assertTrue(counter.count == 0);

            rtree.nearestN(testPoint, counter, 10, Double.MAX_VALUE);
            assertTrue(counter.count == 0);

            rtree.nearestNUnsorted(testPoint, counter, 10, Double.MAX_VALUE);
            assertTrue(counter.count == 0);

            rtree.contains(testRect, counter);
            assertTrue(counter.count == 0);
        }
    }

    class Counter implements TIntProcedure {
        public int count = 0;

        @Override
        public boolean execute(int arg0) {
            count++;
            return true;
        }
    }
}
