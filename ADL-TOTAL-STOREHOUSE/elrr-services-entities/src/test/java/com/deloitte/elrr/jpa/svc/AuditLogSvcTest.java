package com.deloitte.elrr.jpa.svc;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.UUID;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.deloitte.elrr.entity.AuditLog;
import com.deloitte.elrr.util.ValueObjectTestUtility;

class AuditLogSvcTest {

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
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterEach
    void tearDown() throws Exception {
    }

    /**
    * Test basic accessor methods.
    */
    @Test
    void test() {
        ValueObjectTestUtility.validateAccessors(AuditLogSvc.class);
    }

    /**
     * Test that delete method throws UnsupportedOperationException.
     */
    @Test
    void testDeleteThrowsException() {
        AuditLogSvc service = new AuditLogSvc(null);
        UUID testId = UUID.randomUUID();
        
        assertThrows(UnsupportedOperationException.class, () -> {
            service.delete(testId);
        });
    }

    /**
     * Test that deleteAll method throws UnsupportedOperationException.
     */
    @Test
    void testDeleteAllThrowsException() {
        AuditLogSvc service = new AuditLogSvc(null);
        
        assertThrows(UnsupportedOperationException.class, () -> {
            service.deleteAll();
        });
    }

    /**
     * Test that update method throws UnsupportedOperationException.
     */
    @Test
    void testUpdateThrowsException() {
        AuditLogSvc service = new AuditLogSvc(null);
        AuditLog auditLog = new AuditLog();
        
        assertThrows(UnsupportedOperationException.class, () -> {
            service.update(auditLog);
        });
    }
}
