package Personnel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class VicePresidentTests {
    private final VicePresident VicePresident = new VicePresident();
    private final VicePresident CoVicePresident = new VicePresident();
    private final President President = new President();
    private final String[] supervisorNames = {"Margo", "Brian", "Nick"};
    private final String[][] workerNames = {
            {"Matthew", "Jordan", "Denice", "Samantha", "Kelly"},
            {"Darren", "Jenny", "Alex", "Jackson", "Mathias"},
            {"Carlos", "Travis", "Casey", "Lauren", "Sarah"}
    };

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
    public void expectedDefaultVicePresidentValues() {
        //Should I be able to access these values without the getters?  What's the point of getters if I can?
        assertEquals("vacant", VicePresident.name);
        assertNull(VicePresident.manager);
        assertTrue(VicePresident.canFire);
        assertTrue(VicePresident.canHire);
        assertTrue(VicePresident.canPromote);
        assertFalse(VicePresident.isPromotable);
        assertTrue(VicePresident.canQuit);
        assertTrue(VicePresident.canLayoff);
        assertTrue(VicePresident.canTransfer);
        assertEquals("Vice President", VicePresident.position);

        //check each supervisor position under the VicePresident is set to vacant and Vice is the calling VP
        for (Supervisor supervisor : VicePresident.getSupervisors()) {
            assertEquals("vacant", supervisor.getName());
            assertEquals(VicePresident, supervisor.getManager());
        }
    }

    //Default values using getters
    @Test
    public void expectedDefaultVicePresidentValuesWithGetters() {
        assertEquals("vacant", VicePresident.getName());
        assertNull(VicePresident.getManager());
        assertTrue(VicePresident.getCanFire());
        assertTrue(VicePresident.getCanHire());
        assertTrue(VicePresident.getCanPromote());
        assertFalse(VicePresident.getCanBePromoted());
        assertTrue(VicePresident.getCanQuit());
        assertTrue(VicePresident.getCanLayoff());
        assertTrue(VicePresident.getCanTransfer());
        assertEquals("Vice President", VicePresident.getPosition());

        //check each supervisor position under the VicePresident is set to vacant and Vice is the calling VP
        for (Supervisor supervisor : VicePresident.getSupervisors()) {
            assertEquals("vacant", supervisor.getName());
            assertEquals(VicePresident, supervisor.getManager());
        }
    }

    //Name function tests
    @Test
    public void vicePresidentSetName() {
        VicePresident.setName("Bartholomew");
        assertEquals("Bartholomew", VicePresident.getName());
    }

    //The next three tests use "illegal" chars and should not replace value if no legal characters are found
    @Test
    public void vicePresidentSetNameEmptyString() {
        VicePresident.setName("");
        assertEquals("vacant", VicePresident.getName());
    }

    @Test
    public void vicePresidentSetNameWithNumbersString() {
        VicePresident.setName("1234543221");
        assertEquals("vacant", VicePresident.getName());
    }

    @Test
    public void vicePresidentSetNameWithSpecialChars() {
        VicePresident.setName("+$*^%&%$(#)#)");
        assertEquals("vacant", VicePresident.getName());
    }

    @Test
    public void vicePresidentSetNameAfterDiscardingIllegalChars() {
        //Contains the letters F o r d within a string of illegal characters
        VicePresident.setName("83672561F093209184   312o 09123#@#!@-#r#$%!@#$!@d.....");
        assertEquals("Ford", VicePresident.getName());
    }

//    A string of max length can be entered into the function, however it is not feasible to test and not ideal...
//    Maybe we should limit the length a string can be in the name?
//    @Test
//    public void vicePresidentSetNameToMaxLength() {
//        int MAX_LENGTH_STRING = 2147483647;
//        String maximus = String.format("%0" + MAX_LENGTH_STRING + "d", 0);
//        VicePresident.setName(maximus);
//        assertEquals(maximus, VicePresident.getName());
//    }

//  Supervisor array access testing
    @Test
    public void vicePresidentIsEmpty() {
        assertTrue(VicePresident.empty());
    }

//  Should this return false if there are supervisors but no employees?  Vice isn't vacant if there are supervisors...
    @Test
    public void vicePresidentShouldNotBeEmpty() {
        int pos = 0;
        for (Supervisor supervisor : VicePresident.getSupervisors()) {
            supervisor.setName(supervisorNames[pos]);
            pos = pos + 1;
        }
//      Currently returning true even if there are supervisors but no workers under them...
        assertFalse(VicePresident.empty());
    }

    @Test
    public void vicePresidentGetSupervisorsDefault() {
        for (Supervisor supervisor : VicePresident.getSupervisors()) {
            assertEquals("vacant", supervisor.getName());
        }
    }

    @Test
    public void vicePresidentSetSupervisorNames() {
        int pos = 0;
        for (Supervisor supervisor : VicePresident.getSupervisors()) {
            supervisor.setName(supervisorNames[pos]);
            pos = pos + 1;
        }

        //Check names after they have been set using the supervisorNames array in the previous loop
        pos = 0;
        for (Supervisor supervisor : VicePresident.getSupervisors()) {
            assertEquals(supervisorNames[pos], supervisor.getName());
            pos = pos + 1;
        }
    }

    @Test
    public void vicePresidentUnderlingsContainsName() {
        int pos = 0;
        for (Supervisor supervisor : VicePresident.getSupervisors()) {
            supervisor.setName(supervisorNames[pos]);
            pos = pos + 1;
        }
        assertTrue(VicePresident.contains("Brian"));
    }

    @Test
    public void vicePresidentSupervisorUnderlingsContainsName() {
        int pos = 0;
        for (Supervisor supervisor : VicePresident.getSupervisors()) {
            supervisor.setName(supervisorNames[pos]);

            int superPos = 0;
            for (Worker worker : supervisor.getWorkers()) {
                worker.setName(workerNames[pos][superPos]);
                superPos = superPos + 1;
            }

            pos = pos + 1;
        }
        assertTrue(VicePresident.contains("Darren"));
    }

    @Test
    public void vicePresidentSupervisorUnderlingsDoesntContainName() {
        int pos = 0;
        for (Supervisor supervisor : VicePresident.getSupervisors()) {
            supervisor.setName(supervisorNames[pos]);

            int superPos = 0;
            for (Worker worker : supervisor.getWorkers()) {
                worker.setName(workerNames[pos][superPos]);
                superPos = superPos + 1;
            }

            pos = pos + 1;
        }
        assertFalse(VicePresident.contains("Billy"));
    }

    @Test
    public void vicePresidentUnderlingsDoesntContainName() {
        int pos = 0;
        for (Supervisor supervisor : VicePresident.getSupervisors()) {
            supervisor.setName(supervisorNames[pos]);
            pos = pos + 1;
        }
        assertFalse(VicePresident.contains("Derek"));
    }

    @Test
    public void vicePresidentUnderlingsContainsVacant() {
        assertTrue(VicePresident.contains("vacant"));
    }

    //Manager tests
    /*@Test
    public void vicePresidentSetManager() {
        VicePresident.setManager(President);
        assertEquals(President, VicePresident.getManager());
    }

    //No employee should be able to set their managers as themselves
    @Test
    public void vicePresidentSetManagerAsSelf() {
        VicePresident.setManager(VicePresident);
        assertNotEquals(VicePresident.getManager(), VicePresident);
    }

    //I should not be able to set a co-Vice President as a manager (someone of the same level)
    @Test
    public void vicePresidentSetManagerAsCoVicePresident() {
        assertNull(VicePresident.getManager());    //Pre-check to see if we have the expected value of null by default
        VicePresident.setManager(CoVicePresident);    //Op should fail leaving the manager as null
        assertNull(VicePresident.getManager());
    }

    @Test
    public void vicePresidentSetManagerAsUnderling() {
        assertNull(VicePresident.getManager());
        int pos = 0;
        for (Supervisor supervisor : VicePresident.getSupervisors()) {
            supervisor.setName(supervisorNames[pos]);
            pos = pos + 1;
        }

        VicePresident.setManager(VicePresident.getSupervisors()[0]);      //Set the value of the vicePresident's manager to worker[0]
        assertNotEquals(supervisorNames[0], VicePresident.getManager().getName());     //Value should not have been set in prev
    }

    @Test
    public void vicePresidentSetManagerAsOtherVicePresidentUnderling() {
        assertNull(VicePresident.getManager());
        int pos = 0;
        for (Supervisor supervisor : CoVicePresident.getSupervisors()) {
            supervisor.setName(supervisorNames[pos]);
            pos = pos + 1;
        }

        VicePresident.setManager(CoVicePresident.getSupervisors()[1]);
        assertNotEquals(supervisorNames[1], VicePresident.getManager().getName());
    }

    //Getters and setters toggling
    /*@Test
    public void vicePresidentCanFire() {
        assertTrue(VicePresident.getCanFire());

        //Test setting function
        VicePresident.setCanFire(false);
        assertFalse(VicePresident.getCanFire());
    }

    @Test
    public void vicePresidentCanHire() {
        assertTrue(VicePresident.getCanHire());

        //Test setting function
        VicePresident.setCanHire(false);
        assertFalse(VicePresident.getCanHire());
    }

    @Test
    public void vicePresidentCanLayoff() {
        assertTrue(VicePresident.getCanLayoff());

        //Test setting function
        VicePresident.setCanLayoff(false);
        assertFalse(VicePresident.getCanLayoff());
    }

    @Test
    public void vicePresidentCanTransfer() {
        assertTrue(VicePresident.getCanTransfer());

        //Test setting function
        VicePresident.setCanTransfer(false);
        assertFalse(VicePresident.getCanTransfer());
    }

    @Test
    public void vicePresidentCanPromote() {
        assertTrue(VicePresident.getCanPromote());

        //Test setting function
        VicePresident.setCanPromote(false);
        assertFalse(VicePresident.getCanPromote());
    }

    @Test
    public void vicePresidentCanBePromoted() {
        assertFalse(VicePresident.getCanBePromoted());

        //Test setting function
        VicePresident.setCanBePromoted(true);
        assertTrue(VicePresident.getCanBePromoted());
    }
*/
    @Test
    public void vicePresidentPosition() {
        assertEquals("Vice President", VicePresident.getPosition());
    }

    //Print tests
    @Test
    public void vicePresidentPrintVacantAndEmpty() {
        VicePresident.print();
        assertEquals("", testConsoleOut.toString());
    }

//  Similar to the print issue above where this won't print if there are supervisors that don't have workers.
    @Test
    public void vicePresidentPrintVacantNameAfterSettingSupervisors() {
        int pos = 0;
        for (Supervisor supervisor : VicePresident.getSupervisors()) {
            supervisor.setName(supervisorNames[pos]);
            pos = pos + 1;
        }
        VicePresident.print();
        String expectedPrint = "\tVice President: vacant\r\n" +
                "\t\tSupervisor: Margo\r\n" +
                "\t\tSupervisor: Brian\r\n" +
                "\t\tSupervisor: Nick\r\n";

        assertEquals(expectedPrint, testConsoleOut.toString());
    }

    @Test
    public void vicePresidentPrintNameAfterSettingEmptyString() {
        //Setting the name to an empty string or null should not update the name from vacant and therefore print empty
        VicePresident.setName("");
        VicePresident.print();
        assertEquals("", testConsoleOut.toString());
    }

    @Test
    public void vicePresidentPrint() {
        VicePresident.setName("Mason");

        int pos = 0;
        for (Supervisor supervisor : VicePresident.getSupervisors()) {
            supervisor.setName(supervisorNames[pos]);
            pos = pos + 1;
        }

        String expectedPrint = "\tVice President: Mason\r\n" +
                "\t\tSupervisor: Margo\r\n" +
                "\t\tSupervisor: Brian\r\n" +
                "\t\tSupervisor: Nick\r\n";

        VicePresident.print();

        assertEquals(expectedPrint, testConsoleOut.toString());
    }

}
