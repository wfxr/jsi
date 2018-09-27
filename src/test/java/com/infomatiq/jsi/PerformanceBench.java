//   PerformanceBench.java
//   Java Spatial Index Library
//   Copyright (C) 2002-2005 Infomatiq Limited.
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

import junit.framework.TestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * PerformanceBench
 * <p>
 * Generates results used for comparing the performance of the Java Spatial
 * Index library against alternative implementations.
 */
public class PerformanceBench extends TestCase {

    private static final Logger log    = LoggerFactory
            .getLogger(PerformanceBench.class);
    private              Script script = new Script();

    public PerformanceBench(String s) {
        super(s);
    }

    // Tests add, intersect, nearest, nearestn, contains.
    // Can optimize for add performance, memory efficiency, or query
    // performance.
    //
    public void testQueryPerformance() {

        // Test 1: add performance.
        // To acheive maximum add performance, it is necessary to minimize the
        // number of
        // node splits. Therefore set the MinNodeEntries to 1.
        // do each test 3 times to see if there is any variance due to hotspot
        // VM (or something else).

        script.run(IndexType.RTREE, 1, 10, "allqueries-10000", Script.PERFORMANCE);
        script.run(IndexType.RTREE, 1, 10, "allqueries-10000", Script.PERFORMANCE);
        script.run(IndexType.RTREE, 1, 10, "allqueries-10000", Script.PERFORMANCE);

        // script.run("test.RTreeWrapper", p, "allqueries-10000",
        // Script.PERFORMANCE);
        // script.run("test.RTreeWrapper", p, "allqueries-10000",
        // Script.PERFORMANCE);
        // script.run("test.RTreeWrapper", p, "allqueries-10000",
        // Script.PERFORMANCE);
    }

    public void testNearestN() {
        script.run(IndexType.RTREE, 5, 10, "nearestN-100", Script.PERFORMANCE);
        script.run(IndexType.RTREE, 5, 10, "nearestN-1000", Script.PERFORMANCE);
        script.run(IndexType.RTREE, 5, 10, "nearestN-10000", Script.PERFORMANCE);
    }

    /**
     * Tests performance of all the RTree variants for add() and intersect(),
     * for up to 10,000,000 entries
     */
    public void testAllFunctions() {
        log.debug("testAllFunctions()");

        // SimpleIndex and NullIndex do not use Min/MaxNodeEntries, so do them
        // first.
        script.run(IndexType.SIMPLE_INDEX, 1, 1, "allfunctions-100",
                   Script.PERFORMANCE);
        script.run(IndexType.SIMPLE_INDEX, 1, 1, "allfunctions-1000",
                   Script.PERFORMANCE);
        script.run(IndexType.SIMPLE_INDEX, 1, 1, "allfunctions-10000",
                   Script.PERFORMANCE);
        // Only go up to 10000 for simple index, as it takes too int

        script.run(IndexType.NULL_INDEX, 1, 1, "allfunctions-100",
                   Script.PERFORMANCE);
        script.run(IndexType.NULL_INDEX, 1, 1, "allfunctions-1000",
                   Script.PERFORMANCE);
        script.run(IndexType.NULL_INDEX, 1, 1, "allfunctions-10000",
                   Script.PERFORMANCE);
        // script.run(IndexType.NULL_INDEX, 1, 1, "allfunctions-100000",
        // Script.PERFORMANCE);

        script.run(IndexType.RTREE_WRAPPER, 5, 20, "allfunctions-100",
                   Script.PERFORMANCE);
        script.run(IndexType.RTREE_WRAPPER, 5, 20, "allfunctions-1000",
                   Script.PERFORMANCE);
        script.run(IndexType.RTREE_WRAPPER, 5, 20, "allfunctions-10000",
                   Script.PERFORMANCE);
        // script.run(IndexType.RTREE_WRAPPER, 5, 20, "allfunctions-100000",
        // Script.PERFORMANCE);

        script.run(IndexType.RTREE, 5, 20, "allfunctions-100", Script.PERFORMANCE);
        script.run(IndexType.RTREE, 5, 20, "allfunctions-1000", Script.PERFORMANCE);
        script
                .run(IndexType.RTREE, 5, 20, "allfunctions-10000", Script.PERFORMANCE);
        // script.run(IndexType.RTREE, 5, 20, "allfunctions-100000",
        // Script.PERFORMANCE);
    }
}
