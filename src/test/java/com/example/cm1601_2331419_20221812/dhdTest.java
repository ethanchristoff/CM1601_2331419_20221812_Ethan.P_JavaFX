package com.example.cm1601_2331419_20221812;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class dhdTest {

    file_manip f_obj = new file_manip();
    String[][] horseData = f_obj.read_from_file(f_obj.find_f_path("horse_data.txt"));

    dhd DHD_obj = new dhd(horseData);

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