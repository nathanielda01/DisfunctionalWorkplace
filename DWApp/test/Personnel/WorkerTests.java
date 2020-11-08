package Personnel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class WorkerTests {
    private final Worker Worker = new Worker();
    private final Worker CoWorker = new Worker();
    private final Supervisor Supervisor = new Supervisor();

    //Setup artificial System.out streams to catch output for testing.  Restored to default in the @After
    private final ByteArrayOutputStream testConsoleOut = new ByteArrayOutputStream();
    private final ByteArrayOutputStream testErrorOut = new ByteArrayOutputStream();
    private final PrintStream originalConsoleOut = System.out;
    private final PrintStream originalErrorOut = System.err;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(testConsoleOut));
        System.setErr(new PrintStream(testErrorOut));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalConsoleOut);
        System.setErr(originalErrorOut);
    }

    //Default values check
    @Test
    public void expectedDefaultWorkerValues() {
        //Should I be able to access these values without the getters?  What's the point of getters if I can?
        assertEquals("vacant", Worker.name);
        assertNull(Worker.manager);
        assertFalse(Worker.canFire);
        assertFalse(Worker.canHire);
        assertFalse(Worker.canPromote);
        assertTrue(Worker.isPromotable);
        assertTrue(Worker.canQuit);
        assertFalse(Worker.canLayoff);
        assertEquals("Worker", Worker.position);
    }

    //Default values using getters
    @Test
    public void expectedDefaultWorkerValuesWithGetters() {
        assertEquals("vacant", Worker.getName());
        assertNull(Worker.getManager());
        assertFalse(Worker.getCanFire());
        assertFalse(Worker.getCanHire());
        assertFalse(Worker.getCanPromote());
        assertTrue(Worker.getCanBePromoted());
        assertTrue(Worker.getCanQuit());
        assertFalse(Worker.getCanLayoff());
        assertEquals("Worker", Worker.getPosition());
    }

    //Name function tests
    @Test
    public void workerSetName() {
        Worker.setName("Maximus");
        assertEquals("Maximus", Worker.getName());
    }

    @Test
    public void workerIsEmpty() {
        assertTrue(Worker.isEmpty());
    }

    //The next three tests use "illegal" chars and should not replace value if no legal characters are found
    @Test
    public void workerSetNameEmptyString() {
        Worker.setName("");
        assertEquals("vacant", Worker.getName());
    }

    @Test
    public void workerSetNameWithNumbersString() {
        Worker.setName("1234543221");
        assertEquals("vacant", Worker.getName());
    }

    @Test
    public void workerSetNameWithSpecialChars() {
        Worker.setName("+$*^%&%$(#)#)");
        assertEquals("vacant", Worker.getName());
    }

    @Test
    public void workerSetNameAfterDiscardingIllegalChars() {
        //Contains the letters J o h n within a string of illegal characters
        Worker.setName("836725610298J093209184   312o09123#@#!@-#h#$%!@#$!@n");
        assertEquals("John", Worker.getName());
    }

//    A string of max length can be entered into the function, however it is not feasible to test and not ideal...
//    Maybe we should limit the length a string can be in the name?
//    @Test
//    public void workerSetNameToMaxLength() {
//        int MAX_LENGTH_STRING = 2147483647;
//        String maximus = String.format("%0" + MAX_LENGTH_STRING + "d", 0);
//        Worker.setName(maximus);
//        assertEquals(maximus, Worker.getName());
//    }

    //Manager tests
    /*@Test
    public void workerSetManager() {
        Worker.setManager(Supervisor);
        assertEquals(Supervisor, Worker.getManager());
    }

    //I should not be able to set a co-worker as a manager (someone of the same level)
    @Test
    public void workerSetManagerAsCoWorker() {
        Worker.setManager(CoWorker);
        assertNull(Worker.getManager());
    }

    //Workers should not be able to set their managers as themselves
    @Test
    public void workerSetManagerAsSelf() {
        Worker.setManager(Worker);
        assertNotEquals(Worker.getManager(), Worker);
    }

    //Getters and setters toggling
    /*@Test
    public void workerCanFire() {
        assertFalse(Worker.getCanFire());
        Worker.setCanFire(true);
        assertTrue(Worker.getCanFire());
    }

    @Test
    public void workerCanHire() {
        assertFalse(Worker.getCanHire());
        Worker.setCanHire(true);
        assertTrue(Worker.getCanHire());
    }

    @Test
    public void workerCanLayoff() {
        assertFalse(Worker.getCanLayoff());
        Worker.setCanLayoff(true);
        assertTrue(Worker.getCanLayoff());
    }

    @Test
    public void workerCanTransfer() {
        assertFalse(Worker.getCanTransfer());
        Worker.setCanTransfer(true);
        assertTrue(Worker.getCanTransfer());
    }

    @Test
    public void workerCanPromote() {
        assertFalse(Worker.getCanPromote());
        Worker.setCanPromote(true);
        assertTrue(Worker.getCanPromote());
    }

    @Test
    public void workerCanBePromoted() {
        assertTrue(Worker.getCanBePromoted());
        Worker.setCanBePromoted(false);
        assertFalse(Worker.getCanBePromoted());
    }
*/
    @Test
    public void workerPosition() {
        assertEquals("Worker", Worker.getPosition());
    }

    //TODO: How are positions changed?
//    @Test
//    public void workerChangePosition() {
//        fail();
//    }

    //Print tests
    @Test
    public void workerPrintVacant() {
        Worker.print();
        assertEquals("", testConsoleOut.toString());
    }

    @Test
    public void workerPrintNameAfterSettingEmptyString() {
        //Setting the name to an empty string or null should not update the name from vacant and therefore print empty
        Worker.setName("");
        Worker.print();
        assertEquals("", testConsoleOut.toString());
    }

    @Test
    public void workerPrintName() {
        Worker.setName("Mason");
        Worker.print();
        assertEquals("\t\t\tWorker: Mason\r\n", testConsoleOut.toString());
    }

}
