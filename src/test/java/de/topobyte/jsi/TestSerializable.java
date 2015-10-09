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

package de.topobyte.jsi;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Set;

import com.infomatiq.jsi.Rectangle;

/**
 * @author Sebastian Kuerten (sebastian@topobyte.de)
 */
public class TestSerializable {

  /**
  * Test serialization of an RTree. Underlying library does not implement
  * Serializable, thus Externalizable has been implemented.
  *
  * @param args none.
  * @throws Exception on failure.
  */
  public static void main(String[] args) throws Exception {
    GenericRTree<Integer> tree = new GenericRTree<Integer>(1, 10);
    tree.add(new Rectangle(1, 3, 2, 4), 99);
    tree.add(new Rectangle(1, 3, 2, 4), 88);
    Set<Integer> i = tree.intersects(new Rectangle(-10, -10, 10, 10));
    System.out.println(i);

    ObjectOutputStream oos = new ObjectOutputStream(new
        FileOutputStream("/tmp/baxx"));
    oos.writeObject(tree);
    oos.close();

    ObjectInputStream ois = new ObjectInputStream(new
        FileInputStream("/tmp/baxx"));
    GenericRTree<Integer> tree2 = (GenericRTree<Integer>) ois.readObject();
    ois.close();

    Set<Integer> k = tree2.intersects(new Rectangle(-10, -10, 10, 10));
    System.out.println(k);
  }

}
