package com.example.cm1601_2331419_20221812;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class ahdTest {
    ahd AHD_obj = new ahd();

    @Test
    // Test to see what happens if user tries to enter value into table that is already full
    void valuesExceeded(){
        /*
        Assuming that the table is already full this will return a null value and prompt that the table is
        already full
        */
        String[] test_input ={"020","D", "Velocity-Voyager", "Joseph-Perez", "3_years", "Appaloosa", "2-wins-in-5-races","horse_1.jpg"};
        assertNull(AHD_obj.AHD(test_input));
    }

    @Test
    // Test to see what happens if ID entered already exists
    void IDexists(){
        /*
         Assuming that the able isn't full, if the ID already exists in the table a null value is returned
         and the user is prompted that the ID already exists
        */
        String[] test_input ={"019","D", "Velocity-Voyager", "Joseph-Perez", "3_years", "Appaloosa", "2-wins-in-5-races","horse_1.jpg"};
        assertNull(AHD_obj.AHD(test_input));
    }

    @Test
    /*
     Test to see what happens if user enter all the values in correctly (inclusive of an image that has been
     dragged and dropped into the image area)
    */
    void correctINP(){
        String[] test_input ={"020","D", "Velocity-Voyager", "Joseph-Perez", "3_years", "Appaloosa", "2-wins-in-5-races","horse_1.jpg"};
        assertNotNull(test_input);
        /*
         A table of the type String[][] is returned if the process is successful,
         so the return type should not be null
         */
        vhd VHD_OBJ = new vhd(AHD_obj.AHD(test_input));
        VHD_OBJ.printTable();// The updated table will be outputted as such
    }
}