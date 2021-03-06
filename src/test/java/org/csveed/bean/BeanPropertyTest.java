package org.csveed.bean;

import org.csveed.bean.conversion.Converter;
import org.csveed.test.converters.BeanSimpleConverter;
import org.csveed.test.model.BeanSimple;
import org.csveed.test.model.BeanVariousNotAnnotated;
import org.junit.Test;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.math.BigDecimal;

import static junit.framework.Assert.*;

public class BeanPropertyTest {

    @Test
    public void construct() throws IntrospectionException {
        BeanProperty property = new BeanProperty();
        Converter converter = new BeanSimpleConverter();
        PropertyDescriptor propertyDescriptor = new PropertyDescriptor("name", BeanSimple.class);
        property.setConverter(converter);
        property.setColumnIndex(3);
        property.setColumnName("name");
        property.setPropertyDescriptor(propertyDescriptor);
        property.setRequired(true);
        assertEquals(converter, property.getConverter());
        assertEquals(3, property.getColumnIndex());
        assertEquals("name", property.getColumnName());
        assertEquals(propertyDescriptor, property.getPropertyDescriptor());
        assertTrue(property.isRequired());
    }

    @Test
    public void numberClass() throws IntrospectionException {
        BeanProperty property = new BeanProperty();
        property.setPropertyDescriptor(new PropertyDescriptor("number", BeanVariousNotAnnotated.class));
        assertNotNull(property.getNumberClass());
    }

    @Test
    public void notANumberClass() throws IntrospectionException {
        BeanProperty property = new BeanProperty();
        property.setPropertyDescriptor(new PropertyDescriptor("name", BeanSimple.class));
        assertNull(property.getNumberClass());
    }

}