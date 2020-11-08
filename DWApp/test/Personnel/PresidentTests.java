package Personnel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class PresidentTests {
    private final VicePresident VicePresident = new VicePresident();
    private final VicePresident CoVicePresident = new VicePresident();
    private final President President = new President();
    private final President SeparateCompanyPresident = new President();
    private final String[] vicePresidentNames = {"Jack", "Jill"};
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
    public void expectedDefaultPresidentValues() {
        //Should I be able to access these values without the getters?  What's the point of getters if I can?
        assertEquals("vacant", President.name);
        assertNull(President.manager);
        assertTrue(President.canFire);
        assertTrue(President.canHire);
        assertTrue(President.canPromote);
        assertFalse(President.isPromotable);
        assertFalse(President.canQuit);
        assertTrue(President.canLayoff);
        assertTrue(President.canTransfer);
        assertEquals("President", President.position);

        //check each supervisor position under the President is set to vacant and  is the calling VP
        for (VicePresident vice : President.getVPs()) {
            assertEquals("vacant", vice.getName());
            assertEquals(President, vice.getManager());
        }
    }

    //Default values using getters
    @Test
    public void expectedDefaultPresidentValuesWithGetters() {
        assertEquals("vacant", President.getName());
        assertNull(President.getManager());
        assertTrue(President.getCanFire());
        assertTrue(President.getCanHire());
        assertTrue(President.getCanPromote());
        assertFalse(President.getCanBePromoted());
        assertFalse(President.getCanQuit());
        assertTrue(President.getCanLayoff());
        assertTrue(President.getCanTransfer());
        assertEquals("President", President.getPosition());

        //check each supervisor position under the President is set to vacant and  is the calling VP
        for (VicePresident vice : President.getVPs()) {
            assertEquals("vacant", vice.getName());
            assertEquals(President, vice.getManager());
        }
    }

    //Name function tests
    @Test
    public void presidentSetName() {
        President.setName("Winnifred");
        assertEquals("Winnifred", President.getName());
    }

    //The next three tests use "illegal" chars and should not replace value if no legal characters are found
    @Test
    public void presidentSetNameEmptyString() {
        President.setName("");
        assertEquals("vacant", President.getName());
    }

    @Test
    public void presidentSetNameWithNumbersString() {
        President.setName("1234543221");
        assertEquals("vacant", President.getName());
    }

    @Test
    public void presidentSetNameWithSpecialChars() {
        President.setName("+$*^%&%$(#)#)");
        assertEquals("vacant", President.getName());
    }

    @Test
    public void presidentSetNameAfterDiscardingIllegalChars() {
        //Contains the letters G r e g within a string of illegal characters
        President.setName("83672561G093209184   312r 09123#@#!@-#e#$%!@#$!@g.....");
        assertEquals("Greg", President.getName());
    }

    //  Vice President array access testing
//  TODO: President doesn't have any "empty()" function that we can check, do we want that?

    @Test
    public void presidentGetVPsDefault() {
        for (VicePresident vice : President.getVPs()) {
            assertEquals("vacant", vice.getName());
        }
    }

    @Test
    public void presidentSetVPsNames() {
        int pos = 0;
        for (VicePresident vice : President.getVPs()) {
            vice.setName(vicePresidentNames[pos]);
            pos = pos + 1;
        }

        //Check names after they have been set using the vicePresidentNames array in the previous loop
        pos = 0;
        for (VicePresident vice : President.getVPs()) {
            assertEquals(vicePresidentNames[pos], vice.getName());
            pos = pos + 1;
        }
    }

//  TODO: The President doesn't have methods to check if it has a person underneath them.  Do we want this?

    //Manager tests
    /*@Test
    public void presidentSetManager() {
        President.setManager(SeparateCompanyPresident);
        assertNull(President.getManager());     //We should not be able to set the president's manager ever.
    }

    //No employee should be able to set their managers as themselves
    @Test
    public void presidentSetManagerAsSelf() {
        President.setManager(President);
        assertNull(President.getManager());
    }

    @Test
    public void presidentSetManagerAsUnderling() {
        assertNull(President.getManager());
        int pos = 0;
        for (VicePresident vice : President.getVPs()) {
            vice.setName(vicePresidentNames[pos]);
            pos = pos + 1;
        }

        President.setManager(President.getVPs()[0]);      //Set the value of the President's manager to worker[0]
        assertNull(President.getManager());
    }

    //Getters and setters toggling
    @Test
    public void presidentCanFire() {
        assertTrue(President.getCanFire());

        //Test setting function
        President.setCanFire(false);
        assertFalse(President.getCanFire());
    }

    @Test
    public void presidentCanHire() {
        assertTrue(President.getCanHire());

        //Test setting function
        President.setCanHire(false);
        assertFalse(President.getCanHire());
    }

    @Test
    public void presidentCanLayoff() {
        assertTrue(President.getCanLayoff());

        //Test setting function
        President.setCanLayoff(false);
        assertFalse(President.getCanLayoff());
    }

    @Test
    public void presidentCanTransfer() {
        assertTrue(President.getCanTransfer());

        //Test setting function
        President.setCanTransfer(false);
        assertFalse(President.getCanTransfer());
    }

    @Test
    public void presidentCanPromote() {
        assertTrue(President.getCanPromote());

        //Test setting function
        President.setCanPromote(false);
        assertFalse(President.getCanPromote());
    }

    @Test
    public void presidentCanBePromoted() {
        assertFalse(President.getCanBePromoted());

        //Test setting function
        President.setCanBePromoted(true);
        assertFalse(President.getCanBePromoted());  //Value should be final for president and unchangeable
    }

    @Test
    public void presidentCanQuit() {
        assertFalse(President.getCanQuit());

        President.setCanQuit(true);
        assertFalse(President.getCanQuit());    //Again, should be false as it cannot be changed
    }
*/
    @Test
    public void presidentPosition() {
        assertEquals("President", President.getPosition());
    }

    //Print tests
    @Test
    public void presidentPrintVacantAndEmpty() {
        President.print();
        assertEquals("President: vacant\r\n\r\n", testConsoleOut.toString());
    }

    //  Similar to the print issue above where this won't print if there are supervisors that don't have workers.
    @Test
    public void presidentPrintVacantNameAfterSettingVPs() {
        int pos = 0;
        for (VicePresident vice : President.getVPs()) {
            vice.setName(vicePresidentNames[pos]);
            pos = pos + 1;
        }
        President.print();
        String expectedPrint = "President: vacant\r\n" +
                "\tVice President: Jack\r\n" +
                "\tVice President: Jill\r\n\r\n";

        assertEquals(expectedPrint, testConsoleOut.toString());
    }

    @Test
    public void presidentPrintNameAfterSettingEmptyString() {
        //Setting the name to an empty string or null should not update the name from vacant and therefore print empty
        President.setName("");
        President.print();
        assertEquals("President: vacant\r\n\r\n", testConsoleOut.toString());
    }

    @Test
    public void presidentPrint() {
        President.setName("Wilfred");

        int pos = 0;
        for (VicePresident vice : President.getVPs()) {
            vice.setName(vicePresidentNames[pos]);
            pos = pos + 1;
        }

        String expectedPrint = "President: Wilfred\r\n" +
                "\tVice President: Jack\r\n" +
                "\tVice President: Jill\r\n\r\n";

        President.print();

        assertEquals(expectedPrint, testConsoleOut.toString());
    }
}
