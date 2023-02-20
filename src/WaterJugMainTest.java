import junit.framework.TestCase;
import org.junit.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import org.junit.jupiter.api.Assertions.*;

class WaterJugMainTest {
    @BeforeAll
    static void setUp(){
        PriorityQueue<Map.Entry<List<Integer>, Double>> openStates = new PriorityQueue<>(Map.Entry.comparingByValue());
    }

    @Test
    void aStarSearchTest() {
        ArrayList<Integer> l1 = new ArrayList<>();
        l1.add(Integer.MAX_VALUE);
        l1.add(5);
        l1.add(8);
        TestCase.assertEquals(4, WaterJugMain.aStarSearch(l1, 13));
    }

    @Test
    void aStarSearchTest1() {
        ArrayList<Integer> l1 = new ArrayList<>();
        l1.add(Integer.MAX_VALUE);
        l1.add(2);
        l1.add(3);
        l1.add(5);
        l1.add(19);
        l1.add(121);
        l1.add(852);
        TestCase.assertEquals(36, WaterJugMain.aStarSearch(l1, 11443));
    }

    @Test
    void aStarSearchTest2() {
        ArrayList<Integer> l1 = new ArrayList<>();
        l1.add(Integer.MAX_VALUE);
        l1.add(2);
        l1.add(5);
        l1.add(6);
        l1.add(72);
        TestCase.assertEquals(7, WaterJugMain.aStarSearch(l1, 143));
    }

    @Test
    void aStarSearchTest3() {
        ArrayList<Integer> l1 = new ArrayList<>();
        l1.add(Integer.MAX_VALUE);
        l1.add(3);
        l1.add(6);
        TestCase.assertEquals(-1, WaterJugMain.aStarSearch(l1, 2));
    }

    @Test
    void aStarSearchTest4() {
        ArrayList<Integer> l1 = new ArrayList<>();
        l1.add(Integer.MAX_VALUE);
        l1.add(1);
        l1.add(4);
        l1.add(10);
        l1.add(15);
        l1.add(22);
        TestCase.assertEquals(19, WaterJugMain.aStarSearch(l1, 181));
    }

    @Test
    void aStarSearchTest5() {
        ArrayList<Integer> l1 = new ArrayList<>();
        l1.add(Integer.MAX_VALUE);
        l1.add(2);
        TestCase.assertEquals(-1, WaterJugMain.aStarSearch(l1, 143));
    }

}