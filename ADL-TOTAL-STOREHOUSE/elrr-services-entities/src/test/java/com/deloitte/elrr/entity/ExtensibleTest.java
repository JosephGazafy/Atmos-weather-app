package com.deloitte.elrr.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.deloitte.elrr.util.ValueObjectTestUtility;

/**
 * Test class for Extensible entity
 */
@ExtendWith(MockitoExtension.class)
class ExtensibleTest {

    private TestExtensible testExtensible;

    /**
     * Concrete implementation of Extensible for testing purposes
     */
    private static class TestExtensible extends Extensible {
        // Simple concrete implementation for testing
    }

    /**
     * @throws java.lang.Exception
     */
    @BeforeAll
    static void setUpBeforeClass() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterAll
    static void tearDownAfterClass() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @BeforeEach
    void setUp() throws Exception {
        testExtensible = new TestExtensible();
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterEach
    void tearDown() throws Exception {
    }

    /**
     * Test accessor methods using ValueObjectTestUtility
     */
    @Test
    void testAccessors() {
        ValueObjectTestUtility.validateAccessors(TestExtensible.class);
    }

    /**
     * Test extensions field initialization
     */
    @Test
    void testExtensionsInitialization() {
        // Test that extensions field is null by default
        assertNull(testExtensible.getExtensions());
    }

    /**
     * Test setting and getting extensions with IRI keys
     */
    @Test
    void testExtensionsSetterGetter() {
        Map<URI, Object> extensions = new HashMap<>();
        extensions.put(URI.create("https://example.org/schema#customField"), "test value");

        testExtensible.setExtensions(extensions);

        assertNotNull(testExtensible.getExtensions());
        assertEquals("test value", testExtensible.getExtensions().get(URI.create("https://example.org/schema#customField")));
    }

    /**
     * Test inheritance from Auditable
     */
    @Test
    void testInheritanceFromAuditable() {
        assertTrue(testExtensible instanceof Auditable);
        
        // Test that inherited methods are available
        assertNotNull(testExtensible.getClass().getSuperclass());
        assertEquals(Extensible.class, testExtensible.getClass().getSuperclass());
        assertEquals(Auditable.class, testExtensible.getClass().getSuperclass().getSuperclass());
    }

    /**
     * Test constructor with extensions
     */
    @Test
    void testConstructorWithExtensions() {
        Map<URI, Object> extensions = new HashMap<>();
        extensions.put(URI.create("https://example.org/schema#key"), "value");

        TestExtensible extensible = new TestExtensible();
        extensible.setExtensions(extensions);
        
        assertNotNull(extensible.getExtensions());
        assertEquals("value", extensible.getExtensions().get(URI.create("https://example.org/schema#key")));
    }
}
