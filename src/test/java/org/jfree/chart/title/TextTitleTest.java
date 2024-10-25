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
 * ------------------
 * TextTitleTest.java
 * ------------------
 * (C) Copyright 2004-2022, by David Gilbert and Contributors.
 *
 * Original Author:  David Gilbert;
 * Contributor(s):   -;
 *
 */

package org.jfree.chart.title;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.stream.Stream;

import org.jfree.chart.TestUtils;
import org.jfree.chart.api.HorizontalAlignment;
import org.jfree.chart.api.RectangleEdge;
import org.jfree.chart.block.LengthConstraintType;
import org.jfree.chart.block.RectangleConstraint;
import org.jfree.chart.block.Size2D;
import org.jfree.chart.internal.CloneUtils;

import org.jfree.data.Range;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for the {@link TextTitle} class.
 */
public class TextTitleTest {

    /**
     * Check that the equals() method distinguishes all fields.
     */
    @Test
    public void testEquals() {
        TextTitle t1 = new TextTitle();
        TextTitle t2 = new TextTitle();
        assertEquals(t1, t2);

        t1.setText("Test 1");
        assertNotEquals(t1, t2);
        t2.setText("Test 1");
        assertEquals(t1, t2);

        Font f = new Font("SansSerif", Font.PLAIN, 15);
        t1.setFont(f);
        assertNotEquals(t1, t2);
        t2.setFont(f);
        assertEquals(t1, t2);

        t1.setTextAlignment(HorizontalAlignment.RIGHT);
        assertNotEquals(t1, t2);
        t2.setTextAlignment(HorizontalAlignment.RIGHT);
        assertEquals(t1, t2);

        // paint
        t1.setPaint(new GradientPaint(1.0f, 2.0f, Color.RED,
                3.0f, 4.0f, Color.BLUE));
        assertNotEquals(t1, t2);
        t2.setPaint(new GradientPaint(1.0f, 2.0f, Color.RED,
                3.0f, 4.0f, Color.BLUE));
        assertEquals(t1, t2);

        // backgroundPaint
        t1.setBackgroundPaint(new GradientPaint(4.0f, 3.0f, Color.RED,
                2.0f, 1.0f, Color.BLUE));
        assertNotEquals(t1, t2);
        t2.setBackgroundPaint(new GradientPaint(4.0f, 3.0f, Color.RED,
                2.0f, 1.0f, Color.BLUE));
        assertEquals(t1, t2);

        // maximumLinesToDisplay
        t1.setMaximumLinesToDisplay(3);
        assertNotEquals(t1, t2);
        t2.setMaximumLinesToDisplay(3);
        assertEquals(t1, t2);

        // toolTipText
        t1.setToolTipText("TTT");
        assertNotEquals(t1, t2);
        t2.setToolTipText("TTT");
        assertEquals(t1, t2);

        // urlText
        t1.setURLText(("URL"));
        assertNotEquals(t1, t2);
        t2.setURLText(("URL"));
        assertEquals(t1, t2);

        // expandToFitSpace
        t1.setExpandToFitSpace(!t1.getExpandToFitSpace());
        assertNotEquals(t1, t2);
        t2.setExpandToFitSpace(!t2.getExpandToFitSpace());
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
     * Confirm that cloning works.
     */
    @Test
    public void testCloning() throws CloneNotSupportedException {
        TextTitle t1 = new TextTitle();
        TextTitle t2 = CloneUtils.clone(t1);
        assertNotSame(t1, t2);
        assertSame(t1.getClass(), t2.getClass());
        assertEquals(t1, t2);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        TextTitle t1 = new TextTitle("Test");
        TextTitle t2 = TestUtils.serialised(t1);
        assertEquals(t1, t2);
    }

    private static Stream<Arguments> arrangeTestCases() {
        return Stream.of(
                Arguments.of(LengthConstraintType.NONE, LengthConstraintType.NONE, false),
                Arguments.of(LengthConstraintType.NONE, LengthConstraintType.RANGE, true),
                Arguments.of(LengthConstraintType.NONE, LengthConstraintType.FIXED, true),
                Arguments.of(LengthConstraintType.FIXED, LengthConstraintType.FIXED, true),
                Arguments.of(LengthConstraintType.FIXED, LengthConstraintType.NONE, false),
                Arguments.of(LengthConstraintType.FIXED, LengthConstraintType.RANGE, true),
                Arguments.of(LengthConstraintType.RANGE, LengthConstraintType.RANGE, false),
                Arguments.of(LengthConstraintType.RANGE, LengthConstraintType.NONE, false),
                Arguments.of(LengthConstraintType.RANGE, LengthConstraintType.FIXED, true)
        );
    }

    @ParameterizedTest
    @MethodSource("arrangeTestCases")
    public void testArrangeWithDifferentConstraints(LengthConstraintType widthConstraint, LengthConstraintType heightConstraint, boolean shouldThrow) {
        TextTitle title = new TextTitle("Test Title");
        Range range = new Range(0, 1.0);
        RectangleConstraint constraint = new RectangleConstraint(500, range, widthConstraint,
                500, range, heightConstraint);

        if (shouldThrow) {
            Assertions.assertThrows(RuntimeException.class, () -> title.arrange(createTestGraphics2D(), constraint));
        } else {
            Size2D bounds = title.arrange(createTestGraphics2D(), constraint);
            Assertions.assertNotNull(bounds);
        }
    }

    private static Stream<Arguments> arrangeFNTestCases() {
        return Stream.of(
                Arguments.of(RectangleEdge.TOP, Boolean.TRUE),
                Arguments.of(RectangleEdge.TOP, Boolean.FALSE),
                Arguments.of(RectangleEdge.LEFT, Boolean.TRUE),
                Arguments.of(RectangleEdge.LEFT, Boolean.FALSE)
        );
    }

    @ParameterizedTest
    @MethodSource("arrangeFNTestCases")
    public void testArrangeFNWithDifferentPositionsAndExpansions(RectangleEdge position, Boolean expandToFit) {
        TextTitle title = new TextTitle("Test Title");
        title.setPosition(position);
        title.setExpandToFitSpace(expandToFit);

        Size2D size = title.arrangeFN(createTestGraphics2D(), 100);

        assertNotNull(size);
        assertTrue(size.getWidth() > 0);
        assertTrue(size.getHeight() > 0);
    }

    @Test
    public void testArrangeRN() {
        TextTitle title = new TextTitle("Test Title");
        Range widthRange = new Range(0, 55.0);

        Size2D size = title.arrangeRN(createTestGraphics2D(), widthRange);

        assertNotNull(size);
        assertTrue(size.getWidth() > 0);
        assertTrue(size.getHeight() > 0);
    }

    @ParameterizedTest
    @MethodSource("arrangeRRTestCases")
    public void testArrangeRRWithDifferentPositionsAndExpansions(RectangleEdge position, Boolean expandToFit) {
        // Arrange
        TextTitle title = new TextTitle("Test Title");
        title.setPosition(position);
        title.setExpandToFitSpace(expandToFit);
        Range range = new Range(0, 10.0);

        // Act
        Size2D size = title.arrangeRR(createTestGraphics2D(), range, range);

        // Assert
        assertNotNull(size);
        assertTrue(size.getWidth() > 0);
        assertTrue(size.getHeight() > 0);
    }

    /**
     * Create a BufferedImage to get a valid Graphics2D object
     * @return a Graphics2D object
     */
    private Graphics2D createTestGraphics2D() {
        return new BufferedImage(500, 100, BufferedImage.TYPE_INT_ARGB).createGraphics();
    }

    private static Stream<Arguments> arrangeRRTestCases() {
        return Stream.of(
                Arguments.of(RectangleEdge.TOP, Boolean.TRUE),
                Arguments.of(RectangleEdge.LEFT, Boolean.TRUE),
                Arguments.of(RectangleEdge.LEFT, Boolean.FALSE)
        );
    }

    @Test
    public void testSetFontAtConstruction() {
        Font font = new Font("SansSerif", Font.PLAIN, 15);
        TextTitle t = new TextTitle("Text", font);

        assertEquals(font, t.getFont());
    }
}
