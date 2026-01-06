package com.deloitte.elrr.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class IdentityTest {

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
    *
    */
    @Test
    void test() {
        Identity identity = new Identity();
        identity.setMbox("mailto:test@test.com");
        assertEquals(identity.getIfi(), "mbox::mailto:test@test.com");
        identity.setMbox(null);
        identity.setMboxSha1Sum("32qf23f23f2332f");
        assertEquals(identity.getIfi(), "mbox_sha1sum::32qf23f23f2332f");
        identity.setMboxSha1Sum(null);
        identity.setOpenid("https://verifyopenid.org.net/whaioehnfioeiohhf");
        assertEquals(identity.getIfi(),
                "openid::https://verifyopenid.org.net/whaioehnfioeiohhf");
        identity.setOpenid(null);
        identity.setHomePage("http://accounts.domain.org/accounts");
        identity.setName("12345");
        assertEquals(identity.getIfi(),
                "account::12345@http://accounts.domain.org/accounts");
    }

    /**
     *
     */
    @Test
    void testToString() {
        assertNotNull(new Identity().toString());
    }
}
