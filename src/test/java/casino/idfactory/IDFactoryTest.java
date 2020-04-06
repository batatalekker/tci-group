package casino.idfactory;

import org.junit.Test;

import static org.junit.Assert.*;

public class IDFactoryTest {

    @Test
    public void generateID() {
        String expected="PC985620001";
        assertEquals(expected,IDFactory.GenerateID("PlayerCard"));
    }
}