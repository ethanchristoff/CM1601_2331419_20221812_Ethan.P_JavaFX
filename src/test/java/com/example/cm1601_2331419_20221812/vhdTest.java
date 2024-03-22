package com.example.cm1601_2331419_20221812;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class vhdTest {
    file_manip f_obj = new file_manip();
    vhd VHD_obj = new vhd(f_obj.read_from_file(f_obj.find_f_path("horse_data.txt")));

    @Test
    /*
    The following test shows what happens when the user selects a header to sort by from the
    choice box
    */
    void testVHD(){
        /*
        A data type will never be returned as it is not required, instead the table is printed as such below
        */
        VHD_obj.run_vhd("breed");// Assuming that the user clicked on breed
        // The process of validating the input is done so in the controller file
    }
}