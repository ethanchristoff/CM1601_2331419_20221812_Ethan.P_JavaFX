package com.example.cm1601_2331419_20221812;

import javafx.scene.control.Alert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Random;

class file_manip{
    public String[][] return_data(){
        return new String[][]{
                {"001", "A", "Speedy-Runner", "Jane-Smith", "5_years", "Thoroughbred", "5-wins-in-10-races","horse_1.jpg"},
                {"002", "A", "Jitty-Sprinter", "John-Doe", "3_years", "Quarter-Horse", "2-wins-in-5-races","horse_2.jpg"},
                {"003", "A", "Fast-Racer", "Emily-Johnson", "4_years", "Arabian", "3-wins-in-7-races","horse_3.jpg"},
                {"004", "A", "Quick-Gallop", "Michael-Brown", "6_years", "Paint-Horse", "4-wins-in-9-races","horse_4.jpg"},
                {"005", "A", "Exelsior-Runner", "Jessica-Wilson", "2_years", "Mustang", "1-win-in-3-races","horse_5.jpg"},
                {"006", "B", "Flash-Runner", "David-Anderson", "7_years", "Appaloosa", "6-wins-in-10-races","horse_6.jpg"},
                {"007", "B", "Bolt-Runner", "Jennifer-Lee", "5_years", "Morgan-Horse", "5-wins-in-10-races","horse_7.jpg"},
                {"008", "B", "Fleet-Feet", "Robert-Miller", "3_years", "Thoroughbred", "2-wins-in-5-races","horse_8.jpg"},
                {"009", "B", "Velocity-Vaulter", "Sarah-Taylor", "4_years", "Quarter-Horse", "3-wins-in-7-races","horse_9.jpg"},
                {"010", "B", "Turbo-Trotter", "William-Harris", "6_years", "Arabian", "4-wins-in-9-races","horse_10.jpg"},
                {"011", "C", "Energetic-Equine", "Laura-Clark", "2_years", "Paint-Horse", "1-win-in-3-races","horse_11.jpg"},
                {"012", "C", "Mercury-Mount", "Kevin-Martinez", "7_years", "Mustang", "6-wins-in-10-races","horse_12.jpg"},
                {"013", "C", "Sonic-Speedster", "Amanda-White", "5_years", "Appaloosa", "5-wins-in-10-races","horse_13.jpg"},
                {"014", "C", "Haste-Hoof", "Daniel-Robinson", "3_years", "Morgan-Horse", "2-wins-in-5-races","horse_14.jpg"},
                {"015", "C", "Quick-Canter", "Olivia-Hall", "4_years", "Thoroughbred", "3-wins-in-7-races","horse_15.jpg"},
                {"016", "D", "Nimble-Nitro", "Matthew-Adams", "6_years", "Quarter-Horse", "4-wins-in-9-races","horse_16.jpg"},
                {"017", "D", "Speedster-Supreme", "Sophia-Turner", "2_years", "Arabian", "1-win-in-3-races","horse_17.jpg"},
                {"018", "D", "Race-Rocket", "Christopher-Evans", "7_years", "Paint-Horse", "6-wins-in-10-races","horse_18.jpg"},
                {"019", "D", "Swift-Sprinter", "Megan-Mitchell", "5_years", "Mustang", "5-wins-in-10-races","horse_19.jpg"},
                {"020", "D", "Velocity-Voyager", "Joseph-Perez", "3_years", "Appaloosa", "2-wins-in-5-races","horse_20.jpg"}
        };
    }

    public String[][] read_from_file(String path) {
        List<String[]> rows = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(",");
                rows.add(row);
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());// Returns error message with custom text appended to it
        }

        return rows.toArray(new String[0][]);
    }

    public void write_to_file(String path, String[][] horseData){

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            for (String[] row : horseData) {
                writer.write(String.join(",", row));
                writer.newLine();
            }
            System.out.println("Data written to " + path);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    // The following method finds the path of the horse_data.txt file and returns its value as a string
    public String find_f_path(String fileName){
        Path currentPath = Paths.get("").toAbsolutePath();
        String horseDataPath = currentPath + "\\" +fileName;//Concatanating the path
        return horseDataPath;
    }

}

class help{
    public void getHelp(){
        String[] help = {
                "• AHD is for adding horse details.",
                "• UHD is for updating horse details.",
                "• DHD is for deleting horse details.",
                "• VHD is for viewing the registered horses details table.",
                "• SRH is for selecting four horses randomly for the major round.",
                "• SWH is for selecting the Winning horses details.",
                "• VWH is for Visualizing the time of the winning horses.",
                "• When updating an image make sure you add one to the horse images folder first",
                "• The acceptable parameters for the uhd headers are:\n-•\"id\"\n-•\"group\"\n-•\"horse name\"\n-•\"owner\"\n-•\"age\"\n-•\"breed\"\n-•\"performance\"\n-•\"image\""
        };
        for (String s : help) {
            System.out.println(s);
        }
    }
}

class validation{
    private final String[][] horseData;// Creates a local variable for the class that is utilized within and taken in as an input

    public validation(String[][] horseData){
        this.horseData=horseData;
    }


    public boolean tbl_count_check(){
        int count = horseData.length;
        if (count<20){
            return false;// If count < 20 number of values is too little, so it returns false
        }else{
            return true;// returns true if the number of values = 20
        }
    }

    public String replaceSpacesWithDashes(String input) {
        return input.replaceAll(" ", "-");// Utilizes regex func to loop through string and replace empty segments
    }

    public boolean group_checker(){
        Map<String,Integer> groups_Dictionary = new HashMap<>();// hashmaps are basically the equivalent of a dictionary
        groups_Dictionary.put("a", 0);
        groups_Dictionary.put("b", 0);
        groups_Dictionary.put("c", 0);
        groups_Dictionary.put("d", 0);
        for (String[] horseDatum : horseData) {
            for (String keys : groups_Dictionary.keySet()) {
                if (horseDatum[1].toLowerCase().equals(keys)) {
                    groups_Dictionary.put(keys, groups_Dictionary.get(keys) + 1);
                }
            }
        }
        for (String keys: groups_Dictionary.keySet()){
            if (groups_Dictionary.get(keys)>5){
                System.out.printf("There are to many horses under group: %s , delete one horse!\n",keys);
                return true;
            }
        }
        return false;
    }

    // The purpose of this function is to ensure that the ID inputted by the user doesn't already exist
    public boolean id_checker(String[] usr_inp){
        for (String[] horseDatum : horseData) {
            if (usr_inp[0].equals(horseDatum[0])) {
                return true;// Returns true if the ID exists
            }
        }
        return false;// Returns false if the ID does not exist
    }
}

// class for the View Horse Details function
class vhd{
    private final String[][] horseData;// Initiates an array class unique to this class

    public vhd(String[][] horseData) {
        this.horseData = horseData;
    }

    //Simple bubble sort algorithm that sorts through the nested rows to print out the data in the order of a certain index
    public void bubbleSortByColumn(int columnIndex) {
        int n = horseData.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (horseData[j][columnIndex].compareTo(horseData[j + 1][columnIndex]) > 0) {
                    // Rows are swapped here after comparing each value to one another
                    String[] temp = horseData[j];
                    horseData[j] = horseData[j + 1];
                    horseData[j + 1] = temp;
                }
            }
        }
    }

    public void printTable() {
        for (String[] row : horseData) {
            for (String value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }

    public void run_vhd(String head){
        String[] headers = {"id", "group", "horse name", "owner", "age", "breed", "performance"};
        int colidx=0;


        for (int i=0;i<headers.length;i++){
            if (headers[i].equals(head)){
                colidx=i;
                break;
            }
        }
        System.out.printf("\nThe following data was sorted by the column: %s,\n",head);
        bubbleSortByColumn(colidx); // Sorting by column specified by user
        printTable();
    }

}

class ahd // The update horse details function
{
    private final String[][] horseData;

    public ahd(String[][] horseData){
        this.horseData=horseData;
    }

    public String[][] AHD(String[] usr_inp) {
        file_manip f_obj = new file_manip();
        validation v_obj = new validation(horseData);
        boolean state = v_obj.tbl_count_check();
        if (state) {
            System.out.println("There are already 20 values in the data set delete one!");
        } else if(v_obj.group_checker()){
            return null;
        } else if(v_obj.id_checker(usr_inp)) {
            System.out.println("The ID already exists!");
        } else {
            String[] values = new String[8];
            int index = 0;
            while (index < values.length && values[index] != null && !values[index].isEmpty()) {
                index++;
            }
            for (int i = 0; i < values.length; i++) {
                if (index < values.length) {
                    values[i] = v_obj.replaceSpacesWithDashes(usr_inp[i]);// All the gaps are replaced with dashes
                } else {
                    System.out.println("The list is full! No more values can be added..");
                }
            }
            String[][] newHorseData = new String[horseData.length + 1][];
            System.arraycopy(horseData, 0, newHorseData, 0, horseData.length);
            newHorseData[horseData.length] = values;
            // Update the original array reference to point to the new array
            vhd newViewObj = new vhd(newHorseData);
            newViewObj.printTable();
            return newHorseData;
        }
        return null;
    }
}

class uhd // The update horse details function
{
    public String[][] UHD(String h_id, int valToEdit, String newVal){
        file_manip f_obj = new file_manip();
        String[][] horseData = f_obj.read_from_file(f_obj.find_f_path("horse_data.txt"));
        validation v_obj = new validation(horseData);
        newVal = v_obj.replaceSpacesWithDashes(newVal);
        int row=0;
        for (int i=0;i<horseData.length;i++){
            if (h_id.equals(horseData[i][0])){
                row = i;
                break;
            }
        }
        if (row==0){
            System.out.println("That ID does not exist!");
        }else{
            int col = valToEdit;
            horseData[row][col]=newVal;
            vhd ViewObj = new vhd(horseData);
            f_obj.write_to_file(f_obj.find_f_path("horse_data.txt"),horseData);
            ViewObj.printTable();
            return horseData;
        }
        return null;
    }
}

class dhd // The delete horse details function
{
    private final String[][] horseData;

    public dhd(String[][] horseData){
        this.horseData=horseData;
    }

    /*
    The DHD class here takes in the users input (as in the ID of the horse to delete and then
    parses it into an integer value.
    */
    public String[][] DHD(String u_inp)
    {
        file_manip f_obj = new file_manip();
        if (Integer.parseInt(u_inp) > horseData.length || Integer.parseInt(u_inp) <= 0){
            /*
            For the sake of ensuring that the users input is in a certain range
            it is parsed into a numeric integer value, and then it's validated to ensure
            that it is between the range of the length of the horse data table and greater than
            or equal to zero
            */
            System.out.println("The entered ID is not in the horse table, try again...");
            return null;
        }
        String[][] newHorseData = new String[horseData.length-1][horseData[0].length];
        int idx=0;
        for (String[] horseDatum : horseData) {
            if (!horseDatum[0].equals(u_inp)) {
                newHorseData[idx] = horseDatum;
                idx++;
            }
        }
        vhd ViewObj = new vhd(newHorseData);
        ViewObj.printTable();
        return newHorseData;

    }
}

class srd // Selecting 4 random horses from each of the 4 groups
{
    private final String[][] horseData;

    // Setting the SRD constructor to take in horse data as an input whenever its initialized
    public srd(String[][] horseData){
        this.horseData = horseData;
    }

    public String[][] selectRandomHorses(){
        validation v_obj = new validation(horseData);
        Alert alert = new Alert(Alert.AlertType.WARNING);
        if (v_obj.group_checker()){
            alert.setTitle("Missing Information");
            alert.setHeaderText(null);
            alert.setContentText("There are too little horses in a group to carry this function out, try again!");
            alert.showAndWait();
            return null;
        } else if (!v_obj.tbl_count_check()){
            alert.setTitle("Missing Information");
            alert.setHeaderText(null);
            alert.setContentText("There are too few values in the horse data file to run this function, add some more horses to run the function!");
            alert.showAndWait();
            return null;
        }
        /*
        The logic in SRD is as follows:
        - There are four new groups which each consist of the horse groups (varying from A~D)
        - The function here first separates each horse into their group array and then stores it there
        - Another group is initiated called rd_horse, of each of these groups a single horse is randomly
          selected and put into the rd_horse group as to where it is then saved (if the user chooses to
          do so) in the "random_horses.txt" file.
        - A switch statement is referred to here to pick out the index of the group in the horseData table
        */
        String[][] groupA = new String[5][8];
        String[][] groupB = new String[5][8];
        String[][] groupC = new String[5][8];
        String[][] groupD = new String[5][8];
        String[][] rd_horse = new String[4][8];
        int indexA = 0, indexB = 0, indexC = 0, indexD = 0;
        for (String[] horseDatum : horseData) {
            switch (horseDatum[1]) {
                case "A":
                    groupA[indexA] = horseDatum;
                    indexA++;
                    break;

                case "B":
                    groupB[indexB] = horseDatum;
                    indexB++;
                    break;

                case "C":
                    groupC[indexC] = horseDatum;
                    indexC++;
                    break;

                case "D":
                    groupD[indexD] = horseDatum;
                    indexD++;
                    break;

                default:
                    break;
            }
        }
        Random rand = new Random();// Initiating the random object
        for (int i = 0; i < 4; i++) {
            /*
            What happens here is that as the loop goes up and the value i is incremented
            , a switch statement is referred to in order to change the temp group to one of
            the four groups. Then using java's in built random function it replaces the data
            in the rd_group array with a random value from the curr_group array.
            */
            String[][] curr_group = switch (i) {
                case 0 -> groupA;
                case 1 -> groupB;
                case 2 -> groupC;
                case 3 -> groupD;
                default -> null;
            };
            int rand_idx =rand.nextInt(5);
            rd_horse[i]=curr_group[rand_idx];
        }
        vhd VHD = new vhd(rd_horse);
        VHD.printTable();
        return rd_horse;
    }

}

class swh // Selecting the winning horse details
{
    private final String[][] randData;

    public swh(String[][] randData) {
        this.randData = randData;
    }

    public String[][] SWH() {
        String[][] winningHorses = new String[4][2]; // Only two columns for owner name and random value
        Random rand = new Random();
        for (int i = 0; i < randData.length; i++) {
            int rand_time = rand.nextInt(90) + 1; // Ensure random value is between 1 and 90
            winningHorses[i][0] = randData[i][2]; // Owner name
            winningHorses[i][1] = Integer.toString(rand_time); // Random value
        }
        for (String[] winningHors : winningHorses) {
            for (String winningHor : winningHors) {
                System.out.print(winningHor+" ");
            }
            System.out.println();
        }
        return winningHorses;
    }
}