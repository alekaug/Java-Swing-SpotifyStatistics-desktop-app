package pl.polsl.lab.cw1.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Order;

/**
 *
 * @author Aleksander Augustyniak
 */
public class SpearmanRankTest {
    
    public SpearmanRankTest() {
    }

    @org.junit.jupiter.api.BeforeAll
    public static void setUpClass() throws Exception {
    }

    @org.junit.jupiter.api.AfterAll
    public static void tearDownClass() throws Exception {
    }

    @org.junit.jupiter.api.BeforeEach
    public void setUp() throws Exception {
    }

    @org.junit.jupiter.api.AfterEach
    public void tearDown() throws Exception {
    }
    
//    @BeforeAll
//    public static void setUpClass() {
//    }
//    
//    @AfterAll
//    public static void tearDownClass() {
//    }
//    
//    @BeforeEach
//    public void setUp() {
//    }
//    
//    @AfterEach
//    public void tearDown() {
//    }

    /**
     * Test of SpearmanRank method, of class SpearmanRank.
     */
    @Test
    @Order(1)
    public void testInversedCorrelationSpearmanRank() {
        Long[] x = new Long[]{1L, 2L, 3L, 4L, 5L};
        Long[] y = new Long[]{5L, 4L, 3L, 2L, 1L};
        SpearmanRank instance = new SpearmanRank();
        Double expResult = -1.0;
        Double result = instance.SpearmanRank(x, y);
        assertEquals(expResult, result);
    }
    
    @Test
    @Order(2)
    public void testPositiveCorrelationSpearmanRank() {
        Long[] x = new Long[]{1L, 2L, 3L, 4L, 5L};
        Long[] y = new Long[]{1L, 2L, 3L, 4L, 5L};
        SpearmanRank instance = new SpearmanRank();
        Double expResult = 1.0;
        Double result = instance.SpearmanRank(x, y);
        assertEquals(expResult, result);
    }
}
