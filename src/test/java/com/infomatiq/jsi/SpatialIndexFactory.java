//   SpatialIndexFactory.java
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
//  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA

package com.infomatiq.jsi;

import com.infomatiq.jsi.rtree.RTree;

/**
 * Factory class used to create instances of spatial indexes
 */
public class SpatialIndexFactory {

    public static SpatialIndex newInstance(IndexType type) {
        switch (type) {
            default:
            case NULL_INDEX:
                return new NullIndex();
            case SIMPLE_INDEX:
                return new SimpleIndex();
            case RTREE:
                return new RTree();
            case RTREE_WRAPPER:
                return new RTreeWrapper();
        }
    }

}
