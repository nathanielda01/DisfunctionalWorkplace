package Personnel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class SupervisorTests {
    private final Supervisor Supervisor = new Supervisor();
    private final Supervisor CoSupervisor = new Supervisor();
    private final VicePresident Vice = new VicePresident();
    private final String[] workerNames = {"Darren", "Jenny", "Alex", "Jackson", "Mathias"};

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
    public void expectedDefaultSupervisorValues() {
        //Should I be able to access these values without the getters?  What's the point of getters if I can?
        assertEquals("vacant", Supervisor.name);
        assertNull(Supervisor.manager);
        assertTrue(Supervisor.canFire);
        assertTrue(Supervisor.canHire);
        assertFalse(Supervisor.canPromote);
        assertTrue(Supervisor.isPromotable);
        assertTrue(Supervisor.canQuit);
        assertTrue(Supervisor.canLayoff);
        assertFalse(Supervisor.canTransfer);
        assertEquals("Supervisor", Supervisor.position);

        //check each worker position under the Supervisor is set to vacant and supervisor is the calling supervisor
        for (Worker worker : Supervisor.getWorkers()) {
            assertEquals("vacant", worker.getName());
            assertEquals(Supervisor, worker.getManager());
        }
    }

    //Default values using getters
    @Test
    public void expectedDefaultSupervisorValuesWithGetters() {
        assertEquals("vacant", Supervisor.getName());
        assertNull(Supervisor.getManager());
        assertTrue(Supervisor.getCanFire());
        assertTrue(Supervisor.getCanHire());
        assertFalse(Supervisor.getCanPromote());
        assertTrue(Supervisor.getCanBePromoted());
        assertTrue(Supervisor.getCanQuit());
        assertTrue(Supervisor.getCanLayoff());
        assertFalse(Supervisor.getCanTransfer());
        assertEquals("Supervisor", Supervisor.getPosition());

        //check each worker position under the Supervisor is set to vacant and supervisor is the calling supervisor
        for (Worker worker : Supervisor.getWorkers()) {
            assertEquals("vacant", worker.getName());
            assertEquals(Supervisor, worker.getManager());
        }
    }

    //Name function tests
    @Test
    public void supervisorSetName() {
        Supervisor.setName("Artemis");
        assertEquals("Artemis", Supervisor.getName());
    }

    //The next three tests use "illegal" chars and should not replace value if no legal characters are found
    @Test
    public void supervisorSetNameEmptyString() {
        Supervisor.setName("");
        assertEquals("vacant", Supervisor.getName());
    }

    @Test
    public void supervisorSetNameWithNumbersString() {
        Supervisor.setName("1234543221");
        assertEquals("vacant", Supervisor.getName());
    }

    @Test
    public void supervisorSetNameWithSpecialChars() {
        Supervisor.setName("+$*^%&%$(#)#)");
        assertEquals("vacant", Supervisor.getName());
    }

    @Test
    public void supervisorSetNameAfterDiscardingIllegalChars() {
        //Contains the letters M a r k within a string of illegal characters
        Supervisor.setName("836725610298M093209184   312a09123#@#!@-#r#$%!@#$!@k");
        assertEquals("Mark", Supervisor.getName());
    }

//    A string of max length can be entered into the function, however it is not feasible to test and not ideal...
//    Maybe we should limit the length a string can be in the name?
//    @Test
//    public void supervisorSetNameToMaxLength() {
//        int MAX_LENGTH_STRING = 2147483647;
//        String maximus = String.format("%0" + MAX_LENGTH_STRING + "d", 0);
//        Supervisor.setName(maximus);
//        assertEquals(maximus, Supervisor.getName());
//    }

//  Worker array access testing
    @Test
    public void supervisorIsEmpty() {
        assertTrue(Supervisor.empty());
    }

    @Test
    public void supervisorShouldNotBeEmpty() {
        int pos = 0;
        for (Worker emp : Supervisor.getWorkers()) {
            emp.setName(workerNames[pos]);
            pos = pos + 1;
        }

        assertFalse(Supervisor.empty());
    }

    @Test
    public void supervisorGetWorkersDefault() {
        for (Worker worker : Supervisor.getWorkers()) {
            assertEquals("vacant", worker.getName());
        }
    }

    @Test
    public void supervisorSetWorkerNames() {
        int pos = 0;
        for (Worker worker : Supervisor.getWorkers()) {
            worker.setName(workerNames[pos]);
            pos = pos + 1;
        }

        //Check names after they have been set using the workerNames array in the previous loop
        pos = 0;
        for (Worker worker : Supervisor.getWorkers()) {
            assertEquals(workerNames[pos], worker.getName());
            pos = pos + 1;
        }
    }

    @Test
    public void supervisorUnderlingsContainsName() {
        int pos = 0;
        for (Worker worker : Supervisor.getWorkers()) {
            worker.setName(workerNames[pos]);
            pos = pos + 1;
        }
        assertTrue(Supervisor.contains("Jenny"));
    }

    @Test
    public void supervisorUnderlingsDoesntContainName() {
        int pos = 0;
        for (Worker worker : Supervisor.getWorkers()) {
            worker.setName(workerNames[pos]);
            pos = pos + 1;
        }
        assertFalse(Supervisor.contains("Bruce"));
    }

    @Test
    public void supervisorUnderlingsContainsVacant() {
        assertTrue(Supervisor.contains("vacant"));
    }

    //Manager tests
    /*@Test
    public void supervisorSetManager() {
        Supervisor.setManager(Vice);
        assertEquals(Vice, Supervisor.getManager());
    }

    //No employee should not be able to set their managers as themselves
    @Test
    public void supervisorSetManagerAsSelf() {
        Supervisor.setManager(Supervisor);
        assertNotEquals(Supervisor.getManager(), Supervisor);
    }

    //I should not be able to set a co-supervisor as a manager (someone of the same level)
    @Test
    public void supervisorSetManagerAsCoSupervisor() {
        assertNull(Supervisor.getManager());    //Pre-check to see if we have the expected value of null by default
        Supervisor.setManager(CoSupervisor);    //Op should fail leaving the manager as null
        assertNull(Supervisor.getManager());
    }

    @Test
    public void supervisorSetManagerAsUnderling() {
        assertNull(Supervisor.getManager());
        int pos = 0;
        for (Worker emp : Supervisor.getWorkers()) {
            emp.setName(workerNames[pos]);
            pos = pos + 1;
        }

        Supervisor.setManager(Supervisor.getWorkers()[0]);      //Set the value of the supervisor's manager to worker[0]
        assertNotEquals(workerNames[0], Supervisor.getManager().getName());     //Value should not have been set in prev
    }

    @Test
    public void supervisorSetManagerAsOtherSupervisorUnderling() {
        assertNull(Supervisor.getManager());
        int pos = 0;
        for (Worker emp : CoSupervisor.getWorkers()) {
            emp.setName(workerNames[pos]);
            pos = pos + 1;
        }

        Supervisor.setManager(CoSupervisor.getWorkers()[1]);
        assertNotEquals(workerNames[1], Supervisor.getManager().getName());
    }

    //Getters and setters toggling
    /*@Test
    public void supervisorCanFire() {
        assertTrue(Supervisor.getCanFire());

        //Test setting function
        Supervisor.setCanFire(false);
        assertFalse(Supervisor.getCanFire());
    }

    @Test
    public void supervisorCanHire() {
        assertTrue(Supervisor.getCanHire());

        //Test setting function
        Supervisor.setCanHire(false);
        assertFalse(Supervisor.getCanHire());
    }

    @Test
    public void supervisorCanLayoff() {
        assertTrue(Supervisor.getCanLayoff());

        //Test setting function
        Supervisor.setCanLayoff(false);
        assertFalse(Supervisor.getCanLayoff());
    }

    @Test
    public void supervisorCanTransfer() {
        assertFalse(Supervisor.getCanTransfer());

        //Test setting function
        Supervisor.setCanTransfer(true);
        assertTrue(Supervisor.getCanTransfer());
    }

    @Test
    public void supervisorCanPromote() {
        assertFalse(Supervisor.getCanPromote());

        //Test setting function
        Supervisor.setCanPromote(true);
        assertTrue(Supervisor.getCanPromote());
    }

    @Test
    public void supervisorCanBePromoted() {
        assertTrue(Supervisor.getCanBePromoted());

        //Test setting function
        Supervisor.setCanBePromoted(false);
        assertFalse(Supervisor.getCanBePromoted());
    }
*/
    @Test
    public void supervisorPosition() {
        assertEquals("Supervisor", Supervisor.getPosition());
    }

//    TODO: How are positions changed?
//    @Test
//    public void supervisorChangePosition() {
//        fail();
//    }

    //Print tests
    @Test
    public void supervisorPrintVacant() {
        Supervisor.print();
        assertEquals("", testConsoleOut.toString());
    }

    @Test
    public void supervisorPrintNameAfterSettingEmptyString() {
        //Setting the name to an empty string or null should not update the name from vacant and therefore print empty
        Supervisor.setName("");
        Supervisor.print();
        assertEquals("", testConsoleOut.toString());
    }

    @Test
    public void supervisorPrint() {
        Supervisor.setName("Mason");

        int pos = 0;
        for (Worker worker : Supervisor.getWorkers()) {
            worker.setName(workerNames[pos]);
            pos = pos + 1;
        }

        String expectedPrint = "\t\tSupervisor: Mason\r\n" +
                "\t\t\tWorker: Darren\r\n" +
                "\t\t\tWorker: Jenny\r\n" +
                "\t\t\tWorker: Alex\r\n" +
                "\t\t\tWorker: Jackson\r\n" +
                "\t\t\tWorker: Mathias\r\n";

        Supervisor.print();

        assertEquals(expectedPrint, testConsoleOut.toString());
    }

}
