//   NullIndex.java
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

import com.slimjars.dist.gnu.trove.procedure.TIntProcedure;

/**
 * An implementation of SpatialIndex that does absolutely nothing.
 * The purpose of this class is to measure the overhead of the
 * testing framework.
 */
public class NullIndex implements SpatialIndex {

  /**
   * @see com.infomatiq.jsi.SpatialIndex#nearest(Point, gnu.trove.TIntProcedure, double)
   */
  @Override
  public void nearest(Point p, TIntProcedure v, double distance) {
  }

  /**
   * @see com.infomatiq.jsi.SpatialIndex#nearestN(Point, gnu.trove.TIntProcedure, int, double)
   */
  @Override
  public void nearestN(Point p, TIntProcedure v, int n, double distance) {
  }

  /**
   * @see com.infomatiq.jsi.SpatialIndex#nearestNUnsorted(Point, gnu.trove.TIntProcedure, int, double)
   */
  @Override
  public void nearestNUnsorted(Point p, TIntProcedure v, int n, double distance) {
  }

  /**
   * @see com.infomatiq.jsi.SpatialIndex#intersects(Rectangle, gnu.trove.TIntProcedure)
   */
  @Override
  public void intersects(Rectangle r, TIntProcedure ip) {
  }

  /**
   * @see com.infomatiq.jsi.SpatialIndex#contains(Rectangle, gnu.trove.TIntProcedure)
   */
  @Override
  public void contains(Rectangle r, TIntProcedure ip) {
  }

  /**
   * @see com.infomatiq.jsi.SpatialIndex#add(Rectangle, int)
   */
  @Override
  public void add(Rectangle r, int id) {
  }

  /**
   * @see com.infomatiq.jsi.SpatialIndex#delete(Rectangle, int)
   */
  @Override
  public boolean delete(Rectangle r, int id) {
    return false;
  }

  /**
   * @see com.infomatiq.jsi.SpatialIndex#size()
   */
  @Override
  public int size() {
    return 0;
  }

  /**
   * @see com.infomatiq.jsi.SpatialIndex#getBounds()
   */
  @Override
  public Rectangle getBounds() {
    return null;
  }

}
