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
 * ShortTextTitleTest.java
 * -----------------------
 * (C) Copyright 2008-2022, by David Gilbert and Contributors.
 *
 * Original Author:  David Gilbert;
 * Contributor(s):   -;
 *
 */

package org.jfree.chart.title;

import org.jfree.chart.TestUtils;
import org.jfree.chart.block.AbstractBlock;
import org.jfree.chart.block.LengthConstraintType;
import org.jfree.chart.block.RectangleConstraint;
import org.jfree.chart.internal.CloneUtils;
import org.jfree.data.Range;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;


/**
 * Tests for the {@link ShortTextTitle} class.
 */
public class ShortTextTitleTest {

    private final String DEFAULT_TXT = "Hello";
    private final int WIDTH = 50;
    private final int HEIGHT = 50;

    private Graphics2D g2;
    private ShortTextTitle shortTextTitle;
    private ShortTextTitle shortTextTitleSpy;

    @BeforeEach
    public void setUp() {
        BufferedImage bufferedImage = new BufferedImage(WIDTH, HEIGHT,
                BufferedImage.TYPE_INT_RGB);
        g2 = bufferedImage.createGraphics();
        shortTextTitle = new ShortTextTitle(DEFAULT_TXT);
        shortTextTitleSpy = spy(shortTextTitle);
    }

    /**
     * Check that the equals() method distinguishes all fields.
     */
    @Test
    public void testEquals() {
        ShortTextTitle t1 = new ShortTextTitle("ABC");
        ShortTextTitle t2 = new ShortTextTitle("ABC");
        assertEquals(t1, t2);

        t1.setText("Test 1");
        assertNotEquals(t1, t2);
        t2.setText("Test 1");
        assertEquals(t1, t2);
    }

    /**
     * Two objects that are equal are required to return the same hashCode.
     */
    @Test
    public void testHashcode() {
        ShortTextTitle t1 = new ShortTextTitle("ABC");
        ShortTextTitle t2 = new ShortTextTitle("ABC");
        assertEquals(t1, t2);
        int h1 = t1.hashCode();
        int h2 = t2.hashCode();
        assertEquals(h1, h2);
    }

    /**
     * Confirm that cloning works.
     */
    @Test
    public void testCloning() throws CloneNotSupportedException {
        ShortTextTitle t1 = new ShortTextTitle("ABC");
        ShortTextTitle t2 = CloneUtils.clone(t1);
        assertNotSame(t1, t2);
        assertSame(t1.getClass(), t2.getClass());
        assertEquals(t1, t2);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        ShortTextTitle t1 = new ShortTextTitle("ABC");
        ShortTextTitle t2 = TestUtils.serialised(t1);
        assertEquals(t1, t2);
    }

    @Test
    public void testConstructor() {
        ShortTextTitle shortTextTitle = new ShortTextTitle(DEFAULT_TXT);
        assertEquals(DEFAULT_TXT, shortTextTitle.getText());
    }

    @Test
    public void testConstructorNull() {
        assertThrows(IllegalArgumentException.class,
                () -> new ShortTextTitle(null));
    }

    @Test
    public void testArrangeLengthConstraintTypeNone() {
        RectangleConstraint rc = new RectangleConstraint(0.0, null,
                LengthConstraintType.NONE, 0.0, null,
                LengthConstraintType.NONE);

        shortTextTitleSpy.arrange(g2, rc);

        verify(shortTextTitleSpy).arrangeNN(g2);
    }

    @Test
    public void testArrangeHeightLengthConstraintTypeRange() {
        RectangleConstraint rc = new RectangleConstraint(0.0, null,
                LengthConstraintType.NONE, 0.0, null,
                LengthConstraintType.RANGE);

        Exception exception = assertThrows(RuntimeException.class,
                () -> shortTextTitle.arrange(g2,
                rc));

        assertEquals("Not yet implemented.", exception.getMessage());
    }

    @Test
    public void testArrangeHeightLengthConstraintTypeFixed() {
        RectangleConstraint rc = new RectangleConstraint(0.0, null,
                LengthConstraintType.NONE, 0.0, null,
                LengthConstraintType.FIXED);

        Exception exception = assertThrows(RuntimeException.class,
                () -> shortTextTitle.arrange(g2, rc));

        assertEquals("Not yet implemented.", exception.getMessage());
    }
}
