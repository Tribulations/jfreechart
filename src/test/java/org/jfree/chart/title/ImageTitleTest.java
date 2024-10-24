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
 * -------------------
 * ImageTitleTest.java
 * -------------------
 * (C) Copyright 2004-2022, by David Gilbert and Contributors.
 *
 * Original Author:  David Gilbert;
 * Contributor(s):   -;
 *
 */

package org.jfree.chart.title;

import org.jfree.chart.api.HorizontalAlignment;
import org.jfree.chart.api.RectangleEdge;
import org.jfree.chart.api.RectangleInsets;
import org.jfree.chart.api.VerticalAlignment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Tests for the {@link ImageTitle} class.
 */
public class ImageTitleTest {

//    /**
//     * Check that the equals() method distinguishes all fields.
//     */
//    @Test
//    public void testEquals() {
//        ImageTitle t1 = new ImageTitle(JFreeChart.INFO.getLogo());
//        ImageTitle t2 = new ImageTitle(JFreeChart.INFO.getLogo());
//        assertEquals(t1, t2);
//
//        t1.setImage(new BufferedImage(2, 1, BufferedImage.TYPE_INT_RGB));
//        assertFalse(t1.equals(t2));
//        t2.setImage(new BufferedImage(2, 1, BufferedImage.TYPE_INT_RGB));
//        // images considered equal only if they're the SAME object
//        // TODO: is there a way to do a better test?
//        assertFalse(t1.equals(t2));
//    }

//    /**
//     * Two objects that are equal are required to return the same hashCode.
//     */
//    @Test
//    public void testHashcode() {
//        ImageTitle t1 = new ImageTitle(JFreeChart.INFO.getLogo());
//        ImageTitle t2 = new ImageTitle(JFreeChart.INFO.getLogo());
//        assertTrue(t1.equals(t2));
//        int h1 = t1.hashCode();
//        int h2 = t2.hashCode();
//        assertEquals(h1, h2);
//    }

//    /**
//     * Confirm that cloning works.
//     */
//    @Test
//    public void testCloning() throws CloneNotSupportedException {
//        ImageTitle t1 = new ImageTitle(JFreeChart.INFO.getLogo());
//        ImageTitle t2 = (ImageTitle) t1.clone();
//        assertTrue(t1 != t2);
//        assertTrue(t1.getClass() == t2.getClass());
//        assertTrue(t1.equals(t2));
//    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    public void testSerialization() {
        // TODO: add serialization support for images
    }

    private static final double EPSILON = 0.00000001;

    private Image mockImage;
    private Graphics2D g2;
    private Rectangle2D area;
    private final int WIDTH = 100;
    private final int HEIGHT = 50;

//    /**
//     * Check the width and height.
//     */
//    @Test
//    public void testWidthAndHeight() {
//        ImageTitle t1 = new ImageTitle(JFreeChart.INFO.getLogo());
//        assertEquals(100, t1.getWidth(), EPSILON);
//        assertEquals(100, t1.getHeight(), EPSILON);
//    }

//    /**
//     * Some checks for the arrange method.
//     */
//    @Test
//    public void testArrangeNN() {
//        BufferedImage image = new BufferedImage(100, 100,
//                BufferedImage.TYPE_INT_RGB);
//        Graphics2D g2 = image.createGraphics();
//        ImageTitle t = new ImageTitle(JFreeChart.INFO.getLogo());
//        Size2D s = t.arrange(g2);
//        assertEquals(102.0, s.getWidth(), EPSILON);
//        assertEquals(102.0, s.getHeight(), EPSILON);
//
//        t.setPadding(1.0, 2.0, 3.0, 4.0);
//        s = t.arrange(g2);
//        assertEquals(106.0, s.getWidth(), EPSILON);
//        assertEquals(104.0, s.getHeight(), EPSILON);
//
//        t.setMargin(5.0, 6.0, 7.0, 8.0);
//        s = t.arrange(g2);
//        assertEquals(120.0, s.getWidth(), EPSILON);
//        assertEquals(116.0, s.getHeight(), EPSILON);
//    }

    @BeforeEach
    public void setUp() {
        mockImage = mock(BufferedImage.class);
        BufferedImage bufferedImage = new BufferedImage(WIDTH, HEIGHT,
                BufferedImage.TYPE_INT_RGB);
        g2 = bufferedImage.createGraphics();
        area = new Rectangle2D.Double(0, 0, WIDTH, HEIGHT);
    }

    @Test
    public void testConstructor() {
        ImageTitle imageTitle = new ImageTitle(mockImage);
        assertNotNull(imageTitle);
        assertEquals(mockImage, imageTitle.getImage());
        assertEquals(Title.DEFAULT_POSITION, imageTitle.getPosition());
        assertEquals(Title.DEFAULT_HORIZONTAL_ALIGNMENT, imageTitle.getHorizontalAlignment());
        assertEquals(Title.DEFAULT_VERTICAL_ALIGNMENT, imageTitle.getVerticalAlignment());
        assertEquals(Title.DEFAULT_PADDING, imageTitle.getPadding());
    }

    @Test
    public void testConstructorWithNullImage() {
        assertThrows(NullPointerException.class,
                () -> new ImageTitle(null));
    }

    @Test
    public void testConstructorWithNullImageErrorMessage() {
        Exception exception = assertThrows(NullPointerException.class,
                () -> new ImageTitle(null));
        String actualMessage = exception.getMessage();

        assertEquals("Null 'image' argument.", actualMessage);
    }

    @Test
    public void testConstructorPositionRectangleEdgeTop() {
        RectangleEdge position = Title.DEFAULT_POSITION;
        ImageTitle imageTitle = new ImageTitle(mockImage, position,
                Title.DEFAULT_HORIZONTAL_ALIGNMENT, Title.DEFAULT_VERTICAL_ALIGNMENT);

        assertEquals(position, imageTitle.getPosition());
    }

    @Test
    public void testConstructorPositionRectangleEdgeBottom() {
        RectangleEdge position = RectangleEdge.BOTTOM;
        ImageTitle imageTitle = new ImageTitle(mockImage, position,
                Title.DEFAULT_HORIZONTAL_ALIGNMENT, Title.DEFAULT_VERTICAL_ALIGNMENT);

        assertEquals(position, imageTitle.getPosition());
    }

    @Test
    public void testConstructorPositionRectangleEdgeLeft() {
        RectangleEdge position = RectangleEdge.LEFT;
        ImageTitle imageTitle = new ImageTitle(mockImage, position,
                Title.DEFAULT_HORIZONTAL_ALIGNMENT, Title.DEFAULT_VERTICAL_ALIGNMENT);

        assertEquals(position, imageTitle.getPosition());
    }

    @Test
    public void testConstructorPositionRectangleEdgeRight() {
        RectangleEdge position = RectangleEdge.RIGHT;
        ImageTitle imageTitle = new ImageTitle(mockImage, position,
                Title.DEFAULT_HORIZONTAL_ALIGNMENT,
                Title.DEFAULT_VERTICAL_ALIGNMENT);

        assertEquals(position, imageTitle.getPosition());
    }

    @Test
    public void testConstructorHorizontalAlignmentRight() {
        HorizontalAlignment alignment = HorizontalAlignment.RIGHT;
        ImageTitle imageTitle = new ImageTitle(mockImage, Title.DEFAULT_POSITION,
                alignment, Title.DEFAULT_VERTICAL_ALIGNMENT);

        assertEquals(alignment, imageTitle.getHorizontalAlignment());
    }

    @Test
    public void testConstructorHorizontalAlignmentCenter() {
        HorizontalAlignment alignment = HorizontalAlignment.CENTER;
        ImageTitle imageTitle = new ImageTitle(mockImage, Title.DEFAULT_POSITION,
                alignment, Title.DEFAULT_VERTICAL_ALIGNMENT);

        assertEquals(alignment, imageTitle.getHorizontalAlignment());
    }

    @Test
    public void testConstructorHorizontalAlignmentLeft() {
        HorizontalAlignment alignment = HorizontalAlignment.LEFT;
        ImageTitle imageTitle = new ImageTitle(mockImage, Title.DEFAULT_POSITION,
                alignment, Title.DEFAULT_VERTICAL_ALIGNMENT);

        assertEquals(alignment, imageTitle.getHorizontalAlignment());
    }

    @Test
    public void testConstructorVerticalAlignmentTop() {
        VerticalAlignment alignment = VerticalAlignment.TOP;
        ImageTitle imageTitle = new ImageTitle(mockImage, Title.DEFAULT_POSITION,
                Title.DEFAULT_HORIZONTAL_ALIGNMENT, alignment);

        assertEquals(alignment, imageTitle.getVerticalAlignment());
    }

    @Test
    public void testConstructorVerticalAlignmentCenter() {
        VerticalAlignment alignment = VerticalAlignment.CENTER;
        ImageTitle imageTitle = new ImageTitle(mockImage, Title.DEFAULT_POSITION,
                Title.DEFAULT_HORIZONTAL_ALIGNMENT, alignment);

        assertEquals(alignment, imageTitle.getVerticalAlignment());
    }

    @Test
    public void testConstructorVerticalAlignmentBottom() {
        VerticalAlignment alignment = VerticalAlignment.BOTTOM;
        ImageTitle imageTitle = new ImageTitle(mockImage, Title.DEFAULT_POSITION,
                Title.DEFAULT_HORIZONTAL_ALIGNMENT, alignment);

        assertEquals(alignment, imageTitle.getVerticalAlignment());
    }

    @Test
    public void testPaddingZeroBoundary() {
        RectangleInsets zeroPadding = new RectangleInsets(0, 0, 0, 0);
        ImageTitle imageTitleZeroPad = new ImageTitle(mockImage, HEIGHT,
                WIDTH, Title.DEFAULT_POSITION,
                Title.DEFAULT_HORIZONTAL_ALIGNMENT,
                Title.DEFAULT_VERTICAL_ALIGNMENT, zeroPadding);

        assertEquals(zeroPadding, imageTitleZeroPad.getPadding());
    }

    @Test
    public void testPaddingMaxBoundary() {
        // QUESTION: It may be wise to add an upper boundary for padding?

        RectangleInsets maxPadding = new RectangleInsets(Integer.MAX_VALUE,
                Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
        ImageTitle imageTitle = new ImageTitle(mockImage, HEIGHT, WIDTH,
                Title.DEFAULT_POSITION, Title.DEFAULT_HORIZONTAL_ALIGNMENT,
                Title.DEFAULT_VERTICAL_ALIGNMENT, maxPadding);

        assertEquals(maxPadding, imageTitle.getPadding());
    }

    @Test
    public void testPaddingMinBoundary() {

        // QUESTION: Are negative values treated as 0 or ignored?
        RectangleInsets minPadding = new RectangleInsets(Integer.MIN_VALUE,
                Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);
        ImageTitle imageTitle = new ImageTitle(mockImage, HEIGHT, WIDTH,
                Title.DEFAULT_POSITION, Title.DEFAULT_HORIZONTAL_ALIGNMENT,
                Title.DEFAULT_VERTICAL_ALIGNMENT, minPadding);

        assertEquals(minPadding, imageTitle.getPadding());
    }

    @Test
    public void testDrawHorizontalPositionTop() {
        ImageTitle imageTitle = new ImageTitle(mockImage, RectangleEdge.TOP,
                Title.DEFAULT_HORIZONTAL_ALIGNMENT,
                Title.DEFAULT_VERTICAL_ALIGNMENT);
        ImageTitle spyTitle = spy(imageTitle);

        spyTitle.draw(g2, area);

        verify(spyTitle).drawHorizontal(g2, area);
        g2.dispose();
    }

    @Test
    public void testDrawHorizontalPositionBottom() {
        ImageTitle imageTitle = new ImageTitle(mockImage,
                RectangleEdge.BOTTOM, Title.DEFAULT_HORIZONTAL_ALIGNMENT,
                Title.DEFAULT_VERTICAL_ALIGNMENT);
        ImageTitle spyTitle = spy(imageTitle);

        spyTitle.draw(g2, area);

        verify(spyTitle).drawHorizontal(g2, area);
    }

    @Test
    public void testDrawVerticalPositionLeft() {
        ImageTitle imageTitle = new ImageTitle(mockImage,
                RectangleEdge.LEFT, Title.DEFAULT_HORIZONTAL_ALIGNMENT,
                Title.DEFAULT_VERTICAL_ALIGNMENT);
        ImageTitle spyTitle = spy(imageTitle);

        spyTitle.draw(g2, area);

        verify(spyTitle).drawVertical(g2, area);
    }

    @Test
    public void testDrawVerticalPositionRight() {
        ImageTitle imageTitle = new ImageTitle(mockImage,
                RectangleEdge.RIGHT, Title.DEFAULT_HORIZONTAL_ALIGNMENT,
                Title.DEFAULT_VERTICAL_ALIGNMENT);
        ImageTitle spyTitle = spy(imageTitle);

        spyTitle.draw(g2, area);

        verify(spyTitle).drawVertical(g2, area);
    }

    @Test
    public void testDrawInvalidPosition() {
        ImageTitle imageTitle = new ImageTitle(mockImage, Title.DEFAULT_POSITION,
                Title.DEFAULT_HORIZONTAL_ALIGNMENT,
                Title.DEFAULT_VERTICAL_ALIGNMENT);
        ImageTitle spyTitle = spy(imageTitle);
        when(spyTitle.getPosition()).thenReturn(null);

        assertThrows(RuntimeException.class, () -> spyTitle.draw(g2, area));
    }

}
