package com.example.cm1601_2331419_20221812;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class uhdTest {

    uhd UHD_obj = new uhd();

    @Test
    // The following test outputs what happens if the inserted ID does not exist in the horseData table
    void incorrectID(){
        assertNull(UHD_obj.UHD("021",2/*index of column to replace the present data*/,"test name"/*val to replace*/));
        /*
        A null value is returned if the inserted index is out of range or does not exist,
        the prompt 'ID DOES NOT EXIST' is then output
        */
    }

    @Test
    // The following test outputs what happens if the user enters the correct ID
    void correctID(){
        assertNotNull(UHD_obj.UHD("020",2/*index of column to replace the present data*/,"test horse name"/*val to replace*/));
        /*
        The table will be printed out with the updated value, this process will be executed assuming
        that the ID being looked for already exists
        */
    }
}