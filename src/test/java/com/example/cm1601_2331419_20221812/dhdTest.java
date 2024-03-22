package com.example.cm1601_2331419_20221812;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class dhdTest {

    dhd DHD_obj = new dhd();

    @Test
    /*
    The following test checks to see what happens if the ID being entered into the DHD function does not
    exists such that there is no such horse row to delete
     */
    void non_existant_ID(){
        assertNull(DHD_obj.DHD("021"));
        /*
        The prompt 'The entered ID is not in the horse table, try again...' will be output and
        a null value will be returned from the function
        */
    }

    @Test
    /*
    The following test is to see what happens if the user enters an ID that already exists
    in the table
    */
    void id_exists(){
        assertNotNull(DHD_obj.DHD("020"));
        /*
        The table is printed out with the deleted row and the horse data with the type String[][] is
        returned
        */
    }
}