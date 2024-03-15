import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class ActorsTest {
    static String data1 , data2;
    static Actors actors;
    @BeforeAll
    static void setUp() {
        actors = new Actors(0,false,"");
        data1 = actors.getActorData("jennifer lawrence");
        data2 =  actors.getActorData("robin williams");
    }
    @Test
    void getNetWorthViaApi1() {
        assertEquals(160000000,actors.getNetWorthViaApi(data1));
    }
    @Test
    void IsAlive1() {
        assertEquals(true,actors.isAlive(data1));
    }
    @Test
    void getNetWorthViaApi2() {
        assertEquals(50000000,actors.getNetWorthViaApi(data2));
    }
    @Test
    void IsAlive2() {
        assertEquals(false,actors.isAlive(data2));
    }
    @Test
    void getDateOfDeathViaApi2() {
        assertEquals("2014-08-11", actors.getDateOfDeathViaApi(data2));
    }
}
