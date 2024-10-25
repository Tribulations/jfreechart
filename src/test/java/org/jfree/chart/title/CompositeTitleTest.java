/* ===========================================================
 * JFreeChart : a free chart library for the Java(tm) platform
 * ===========================================================
 *
 * (C) Copyright 2000-2022, by David Gilbert and Contributors.
 *
 * Project Info:  http://www.jfree.org/jfreechart/index.html
 *
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301,
 * USA.
 *
 * [Oracle and Java are registered trademarks of Oracle and/or its affiliates. 
 * Other names may be trademarks of their respective owners.]
 *
 * -----------------------
 * CompositeTitleTest.java
 * -----------------------
 * (C) Copyright 2005-2022, by David Gilbert and Contributors.
 *
 * Original Author:  David Gilbert;
 * Contributor(s):   -;
 *
 */

package org.jfree.chart.title;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import org.jfree.chart.ChartElementVisitor;
import org.jfree.chart.TestUtils;

import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.block.BlockContainer;
import org.jfree.chart.api.RectangleInsets;
import org.jfree.chart.block.RectangleConstraint;
import org.jfree.chart.block.Size2D;
import org.jfree.chart.internal.CloneUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for the {@link CompositeTitle} class.
 */
public class CompositeTitleTest {

    CompositeTitle ct;
    BlockContainer bc;

    @BeforeEach
    public void setUp() {
        bc = mock(BlockContainer.class);
        ct = new CompositeTitle(bc);

    }


    /**
     * -NEW-
     */
    @Test
    public void testSetTitleContainer() {
        ct.setTitleContainer(bc);
        assertEquals(ct.getContainer(), bc);
    }

    /**
     *  -NEW- Test receive()
     */
    @Test
    public void testReceive() {
        ChartElementVisitor mockVisitor = mock(ChartElementVisitor.class);
        ct.receive(mockVisitor);
        verify(mockVisitor).visit(ct);
    }

    /**
     *  -NEW- For branch when objects are *same*.
     */
    @Test
    public void testEqualsWhenSame() {
        CompositeTitle ct2 = ct;
        assertTrue(ct.equals(ct2));
    }

    /**
     *  -NEW- For branch when objects are of different class.
     */
    @Test
    public void testEqualsOtherClass() {
        assertFalse(ct.equals(bc));
    }

    /**
     *  -NEW- Verify that no-parameters draw calls the overloaded version
     */
    @Test
    public void testDraw() {
        CompositeTitle titleSpy = spy(ct);

        BufferedImage bufferedImage = new BufferedImage(10, 10, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = bufferedImage.createGraphics();
        Rectangle2D area = new Rectangle2D.Double(0, 0, 10, 10);

        titleSpy.draw(g2, area);

        // Check that it overloads
        verify(titleSpy).draw(g2, area, null);

        g2.dispose();
    }

    /**
     * Some checks for the constructor.
     */
    @Test
    public void testConstructor() {
        CompositeTitle t = new CompositeTitle();
        assertNull(t.getBackgroundPaint());
    }

    /**
     * Check that the equals() method distinguishes all fields.
     */
    @Test
    public void testEquals() {
        CompositeTitle t1 = new CompositeTitle(new BlockContainer());
        CompositeTitle t2 = new CompositeTitle(new BlockContainer());
        assertEquals(t1, t2);
        assertEquals(t2, t1);

        // margin
        t1.setMargin(new RectangleInsets(1.0, 2.0, 3.0, 4.0));
        assertNotEquals(t1, t2);
        t2.setMargin(new RectangleInsets(1.0, 2.0, 3.0, 4.0));
        assertEquals(t1, t2);

        // frame
        t1.setFrame(new BlockBorder(Color.RED));
        assertNotEquals(t1, t2);
        t2.setFrame(new BlockBorder(Color.RED));
        assertEquals(t1, t2);

        // padding
        t1.setPadding(new RectangleInsets(1.0, 2.0, 3.0, 4.0));
        assertNotEquals(t1, t2);
        t2.setPadding(new RectangleInsets(1.0, 2.0, 3.0, 4.0));
        assertEquals(t1, t2);

        // contained titles
        t1.getContainer().add(new TextTitle("T1"));
        assertNotEquals(t1, t2);
        t2.getContainer().add(new TextTitle("T1"));
        assertEquals(t1, t2);

        t1.setBackgroundPaint(new GradientPaint(1.0f, 2.0f, Color.RED,
                3.0f, 4.0f, Color.YELLOW));
        assertNotEquals(t1, t2);
        t2.setBackgroundPaint(new GradientPaint(1.0f, 2.0f, Color.RED,
                3.0f, 4.0f, Color.YELLOW));
        assertEquals(t1, t2);

    }

    /**
     * Two objects that are equal are required to return the same hashCode.
     */
    @Test
    public void testHashcode() {
        CompositeTitle t1 = new CompositeTitle(new BlockContainer());
        t1.getContainer().add(new TextTitle("T1"));
        CompositeTitle t2 = new CompositeTitle(new BlockContainer());
        t2.getContainer().add(new TextTitle("T1"));
        assertEquals(t1, t2);
        int h1 = t1.hashCode();
        int h2 = t2.hashCode();
        assertEquals(h1, h2);
    }

    /**
     * Confirm that cloning works.
     */
    @Test
    public void testCloning() {
        CompositeTitle t1 = new CompositeTitle(new BlockContainer());
        t1.getContainer().add(new TextTitle("T1"));
        t1.setBackgroundPaint(new GradientPaint(1.0f, 2.0f, Color.RED,
                3.0f, 4.0f, Color.YELLOW));
        CompositeTitle t2 = null;
        try {
            t2 = CloneUtils.clone(t1);
        }
        catch (CloneNotSupportedException e) {
            fail(e.toString());
        }
        assertNotSame(t1, t2);
        assertSame(t1.getClass(), t2.getClass());
        assertEquals(t1, t2);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        CompositeTitle t1 = new CompositeTitle(new BlockContainer());
        t1.getContainer().add(new TextTitle("T1"));
        t1.setBackgroundPaint(new GradientPaint(1.0f, 2.0f, Color.RED,
                3.0f, 4.0f, Color.BLUE));
        CompositeTitle t2 = TestUtils.serialised(t1);
        assertEquals(t1, t2);
    }

}
