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
 * --------------
 * TitleTest.java
 * --------------
 * (C) Copyright 2004-2022, by David Gilbert and Contributors.
 *
 * Original Author:  David Gilbert;
 * Contributor(s):   -;
 *
 */

package org.jfree.chart.title;

import org.jfree.chart.ChartElementVisitor;
import org.jfree.chart.api.HorizontalAlignment;
import org.jfree.chart.api.RectangleEdge;
import org.jfree.chart.api.VerticalAlignment;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the abstract {@link Title} class.
 */
public class TitleTest {
    TextTitle textTitle;
    VerticalAlignment verticalAlignment = VerticalAlignment.TOP;
    HorizontalAlignment horizontalAlignment = HorizontalAlignment.LEFT;
    RectangleEdge position = RectangleEdge.BOTTOM;

    @BeforeEach
    public void setUp() {
        textTitle = new TextTitle();
        textTitle.setVerticalAlignment(verticalAlignment);
        textTitle.setHorizontalAlignment(horizontalAlignment);
        textTitle.setPosition(position);
    }
    /**
     * Some checks for the equals() method.
     */
    @Test
    public void testEquals() {
        // use the TextTitle class because it is a concrete subclass
        Title t1 = new TextTitle();
        Title t2 = new TextTitle();
        assertEquals(t1, t2);

        t1.setPosition(RectangleEdge.LEFT);
        assertNotEquals(t1, t2);
        t2.setPosition(RectangleEdge.LEFT);
        assertEquals(t1, t2);

        t1.setHorizontalAlignment(HorizontalAlignment.RIGHT);
        assertNotEquals(t1, t2);
        t2.setHorizontalAlignment(HorizontalAlignment.RIGHT);
        assertEquals(t1, t2);

        t1.setVerticalAlignment(VerticalAlignment.BOTTOM);
        assertNotEquals(t1, t2);
        t2.setVerticalAlignment(VerticalAlignment.BOTTOM);
        assertEquals(t1, t2);

        t1.setVisible(false);
        assertNotEquals(t1, t2);
        t2.setVisible(false);
        assertEquals(t1, t2);
    }

    /**
     * Two objects that are equal are required to return the same hashCode.
     */
    @Test
    public void testHashcode() {
        TextTitle t1 = new TextTitle();
        TextTitle t2 = new TextTitle();
        assertEquals(t1, t2);
        int h1 = t1.hashCode();
        int h2 = t2.hashCode();
        assertEquals(h1, h2);
    }

    /**
     * Asserts that the correct exception type is thrown when a null argument is provided.
     */
    @Test
    void testSetHorizontalAlignmentNullValue() {
        assertThrows(IllegalArgumentException.class, () -> textTitle.setHorizontalAlignment(null));
    }

    /**
     * Asserts that the correct exception type is thrown when a null argument is provided.
     */
    @Test
    void testSetVerticalAlignmentNullValue() {
        assertThrows(IllegalArgumentException.class, () -> textTitle.setVerticalAlignment(null));
    }

    /**
     * Asserts that the correct exception type is thrown when a null argument is provided.
     */
    @Test
    void testSetPositionNullValue() {
        assertThrows(IllegalArgumentException.class, () -> textTitle.setPosition(null));
    }

    /**
     * Verify that notify is correctly set to false.
     */
    @Test
    void testSetNotifyToFalse() {
        textTitle.setNotify(false);
        assertFalse(textTitle.getNotify());
    }

    /**
     * Verify that notify is correctly set to true.
     */
    @Test
    void testSetNotifyToTrue() {
        textTitle.setNotify(true);
        assertTrue(textTitle.getNotify());
    }

    /**
     * Verify that visit is called exactly once when Title::receive is called.
     */
    @Test
    void testReceiveCallsVisit() {
        ChartElementVisitor mockVisitor = mock(ChartElementVisitor.class);
        textTitle.receive(mockVisitor);

        verify(mockVisitor, times(1)).visit(textTitle);
    }


}
