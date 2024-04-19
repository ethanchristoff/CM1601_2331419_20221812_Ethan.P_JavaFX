package com.example.cm1601_2331419_20221812;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.application.Platform;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
class ConsoleRedirect {
    private final TextArea textArea;
    private final PrintStream consoleStream;

    public ConsoleRedirect(TextArea textArea) {
        this.textArea = textArea;
        consoleStream = System.out;
        OutputStream out = new OutputStream() {
            @Override
            public void write(int b) {
                appendText(String.valueOf((char) b));
            }

            @Override
            public void write(byte[] b, int off, int len) {
                appendText(new String(b, off, len));
            }

            @Override
            public void write(byte[] b) {
                write(b, 0, b.length);
            }
        };
        PrintStream customStream = new PrintStream(out, true);
        System.setOut(customStream);
        System.setErr(customStream);
    }

    private void appendText(String text) {
        Platform.runLater(() -> textArea.appendText(text));
    }

    public void restoreConsole() {
        System.setOut(consoleStream);
        System.setErr(consoleStream);
    }
}

public class Rapid_Race_Controller implements Initializable {

    // Initiating the file object so that files can be read, written into and printed
    file_manip f_obj = new file_manip();
    validation v_obj = new validation(f_obj.read_from_file(f_obj.find_f_path("horse_data.txt")));

    public static TextArea staticTxtArea;
    private Stage stage;
    private Scene scene;
    private Parent root;

    // Each of the scene switchers takes in an Action event as an argument/parameter
    public void switchToFuncs(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Functions_Pg.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToMain(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Welcome_Page.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToVHD(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("VHD.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToAHD(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AHD.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToUHD(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("UHD.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToDHD(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("DHD.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToSRH(ActionEvent event) throws IOException {
        if (!v_obj.tbl_count_check()){
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Missing Information");
            alert.setHeaderText(null);
            alert.setContentText("Ensure that your horse data table has 20 horses with 5 horses per group!");
            alert.showAndWait();
        }else{
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("SRH.fxml")));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    public void switchToSWH(ActionEvent event) throws IOException {
        if (!v_obj.tbl_count_check()){
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Missing Information");
            alert.setHeaderText(null);
            alert.setContentText("Ensure that your horse data table has 20 horses with 5 horses per group!");
            alert.showAndWait();
        }else{
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("SWH.fxml")));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    public void switchToVWH(ActionEvent event) throws IOException {
        if (!v_obj.tbl_count_check()){
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Missing Information");
            alert.setHeaderText(null);
            alert.setContentText("Ensure that your horse data table has 20 horses with 5 horses per group!");
            alert.showAndWait();
        }else{
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("VWH.fxml")));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    private Button help_btn;

    public void help(){
        // Disables the button to prevent multiple clicks
        help_btn.setDisable(true);

        help help_obj = new help();
        help_obj.getHelp();
    }

    public void exitProgram() {
        System.exit(1);
    }// Exits the program when called

    @FXML
    private ChoiceBox<String> columnSort;

    private final String[] headers = {"id", "group", "horse name", "owner", "age", "breed", "performance", "Images"};


    private void test(ActionEvent event){
        run_vhd(columnSort.getValue());
    }

    // Argument taken in by run_vhd is the header to sort by
    @FXML
    Label horse_counter,GA,GB,GC,GD;

    private void run_vhd(String head){
        horse_counter.setVisible(true);
        int horse_count = v_obj.horse_counter();
        int[] group_count = v_obj.group_counter();
        horse_counter.setText(Integer.toString(horse_count));
        GA.setText(Integer.toString(group_count[0]));
        GB.setText(Integer.toString(group_count[1]));
        GC.setText(Integer.toString(group_count[2]));
        GD.setText(Integer.toString(group_count[3]));
        vhd v_o = new vhd(f_obj.read_from_file(f_obj.find_f_path("horse_data.txt")));
        v_o.run_vhd(head);
    }
    // Initializing the image view function

    @FXML
    ImageView horse_view;
    @FXML
    Button next_img,prev_img;// Initiating the previous image and next image buttons
    @FXML
    Label horse_img_name;

    int clickCount;

    public void displayimg(){
        prev_img.setDisable(true);
        String[][] horseData = f_obj.read_from_file(f_obj.find_f_path("horse_data.txt"));
        if (clickCount>=horseData.length){
            System.out.println("No more horses!");
            next_img.setDisable(true);
            prev_img.setDisable(false);
        }else{
            Image img = new Image(Objects.requireNonNull(getClass().getResourceAsStream("Horse_Images/"+horseData[clickCount][7])));
            horse_img_name.setText(horseData[clickCount][2]);
            horse_view.setImage(img);
            clickCount++;
        }

    }

    public void displayimgprev(){

        String[][] horseData = f_obj.read_from_file(f_obj.find_f_path("horse_data.txt"));
        if (clickCount>0){
            clickCount--;
            Image img = new Image(Objects.requireNonNull(getClass().getResourceAsStream("Horse_Images/"+horseData[clickCount][7])));
            horse_img_name.setText(horseData[clickCount][2]);
            horse_view.setImage(img);
        }else{
            System.out.println("No more horses!");
            prev_img.setDisable(true);
            next_img.setDisable(false);
        }
    }

    private final String WHD_f_name = "winning_horses.txt";
    private final String SRH_f_name = "random_horses.txt";

    // Initiating the drag and drop function for the imageView to add images to the horse table
    private String img_name;

    @FXML
    private ImageView AHD_imgView;

    @FXML
    void imageViewDragDropped(DragEvent event) {
        Dragboard d_board = event.getDragboard();
        if (d_board.hasImage() || d_board.hasFiles()) {
            try {
                List<File> files = d_board.getFiles();
                File sourceFile = files.get(0);
                String fileName = sourceFile.getName();

                File destFile = new File("src/main/resources/com/example/cm1601_2331419_20221812/Horse_Images/placeHolderFile" + fileName);

                // Copies the file to the specified folder
                Files.copy(sourceFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

                img_name = destFile.getName();
                System.out.println("Image saved as: " + destFile);

                // Sets the current imageview to display the dragged image
                AHD_imgView.setImage(new Image(new FileInputStream(destFile)));
            } catch (IOException e) {
                System.out.println("There was an issue!");
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void imageViewDragOver(DragEvent event) {
        Dragboard d_board = event.getDragboard();
        if (d_board.hasFiles() || d_board.hasImage()) {
            event.acceptTransferModes(TransferMode.COPY);
        }

        event.consume();
    }


    private String[][] rowToAdd;

    // Initiating variables for the text fields for the AHD function:
    @FXML
    private TextField horseID,horseName,owner,age,breed,performance;

    // Initializing the variables for the radio buttons
    @FXML
    private RadioButton grpA,grpB,grpC,grpD;

    String group;

    // Initiating method to get group from each button
    public void getGRP(){
        if(grpA.isSelected()){
            group="A";
        } else
        if(grpB.isSelected()){
            group="B";
        } else
        if(grpC.isSelected()){
            group="C";
        } else
        if(grpD.isSelected()) {
            group = "D";
        }
    }

    String[] row_to_add = new String[8];

    private void run_ahd(){
        String[][] horseData = f_obj.read_from_file(f_obj.find_f_path("horse_data.txt"));
        ahd ahd_obj = new ahd(horseData);
        rowToAdd= ahd_obj.AHD(row_to_add);
    }


    public void submit(){
        // Adding an error that is prompted when there is no image added/included
        if (this.img_name == null || this.img_name.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("There is no image!");
            alert.showAndWait();
            return;
        }
        String inp_horseID = horseID.getText();
        // Check if inp_ID is numeric
        try {
            int id = Integer.parseInt(inp_horseID);
            if (id < 1 || id > 20) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Invalid Input");
            alert.setHeaderText(null);
            alert.setContentText("The horse ID must be a number between 001 and 020");
            alert.showAndWait();
            return; // Stops further execution
        }
        String inp_grp = group != null ? group : "";
        /*
        Since the inp_grp uses radial inputs it must be clicked to have an empty string appended to it
        , so since some users may not click it it may not have any value appended to it. So to sort this
        issue out a ternary argument is referred to in order ro see if the value is null. If it is null, it
        will append a null string value which follows the following format of "" instead of null. If it has
        been clicked, the value will have the group variable appended to it.
        */
        String inp_horseName = horseName.getText();
        String inp_owner = owner.getText();
        String inp_age=age.getText();
        String inp_breed = breed.getText();
        String inp_performance = performance.getText();
        // Check if any of the fields are empty
        if (inp_horseID.isEmpty() || inp_grp.isEmpty() || inp_horseName.isEmpty() ||
                inp_owner.isEmpty() || inp_age.isEmpty() || inp_breed.isEmpty() || inp_performance.isEmpty()) {
            // Show an alert if any field is empty
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Missing Information");
            alert.setHeaderText(null);
            alert.setContentText("Please enter data into all fields.");
            alert.showAndWait();
            return; // Stop further execution
        }

        row_to_add= new String[]{inp_horseID, inp_grp, inp_horseName, inp_owner, inp_age, inp_breed, inp_performance,img_name};
        run_ahd();

    }

    // Initiating the save file function
    public void save() {
        file_manip file_obj = new file_manip();
        file_obj.write_to_file(file_obj.find_f_path("horse_data.txt"), rowToAdd);
        System.out.println("The data has been saved!");
    }

    // Initializing the UHD functions:

    private String[][] run_uhd(String h_id, int valToEdit, String newVal){
        uhd uhd_obj = new uhd();
        return uhd_obj.UHD(h_id, valToEdit, newVal);
    }

    String[][] tempdata;

    @FXML
    private TextField UHDhorseID,UHDValToEdit,UHDNewVal;

    // Initializing the drag and drop function for images to be updated in the UHD function
    private String UHD_img_name;

    @FXML
    private ImageView UHD_imgView;

    @FXML
    public void UHD_imageViewDropped(DragEvent event){
        Dragboard d_board = event.getDragboard();
        if (d_board.hasImage() || d_board.hasFiles()){
            try {
                List<File> files = d_board.getFiles();
                File sourceFile = files.get(0);
                String fileName = sourceFile.getName();

                File destFile = new File("src/main/resources/com/example/cm1601_2331419_20221812/Horse_Images/placeHolderFile" + fileName);

                // Copies the file to the specified folder
                Files.copy(sourceFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

                UHD_img_name = destFile.getName();
                System.out.println("Image saved as: " + destFile);

                // Sets the current imageview to display the dragged image
                UHD_imgView.setImage(new Image(new FileInputStream(d_board.getFiles().get(0))));
            } catch (IOException e) {
                System.out.println("There was an issue!");
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    public void UHD_imageViewDragOver(DragEvent event){
        Dragboard d_board = event.getDragboard();
        if (d_board.hasImage() || d_board.hasFiles()){
            event.acceptTransferModes(TransferMode.COPY);
        }
        event.consume();
    }

    public void UHD_submit(){
        String[][] horseData = f_obj.read_from_file(f_obj.find_f_path("horse_data.txt"));
        if (horseData.length == 0){
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Empty Table");
            alert.setHeaderText(null);
            alert.setContentText("The table is empty! There are no fields to update...");
            alert.showAndWait();
        }else{
            String inp_ID = UHDhorseID.getText();
            String inp_Val = UHDValToEdit.getText();
            String inp_New_Val = UHDNewVal.getText();
            if (inp_ID.isEmpty() || inp_Val.isEmpty() || inp_New_Val.isEmpty()){
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Missing Information");
                alert.setHeaderText(null);
                alert.setContentText("Ensure that all the fields are filled");
                alert.showAndWait();
                return; // Stop further execution
            }
            // Check if inp_ID is numeric
            try {
                int id = Integer.parseInt(inp_ID);
                if (id < 1 || id > 20) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Invalid Input");
                alert.setHeaderText(null);
                alert.setContentText("The horse ID must be a number between 001 and 020");
                alert.showAndWait();
                return; // Stops further execution
            }
            String[] headers = {"id","group","horse name","owner","age","breed","performance","image"};
            int col = -1; // Initialize to an invalid value
            for (int i = 0; i < headers.length; i++) {
                if (inp_Val.equalsIgnoreCase(headers[i])) {
                    col = i;
                    break; // Exit the loop once a match is found
                }
            }
            if (col == -1) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Invalid Input");
                alert.setHeaderText(null);
                alert.setContentText("There is no such column!");
                alert.showAndWait();
            }else if ((col == 7) && ((this.UHD_img_name == null) || (this.UHD_img_name.isEmpty()))){
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Missing Data");
                alert.setHeaderText(null);
                alert.setContentText("Ensure that there is an image!");
                alert.showAndWait();
            }else if (col==7)
                tempdata=run_uhd(inp_ID, col, UHD_img_name);
            else
                tempdata=run_uhd(inp_ID, col, inp_New_Val);
        }
    }

    public void uhd_save(){
        f_obj.write_to_file(f_obj.find_f_path("horse_data.txt"),tempdata);
        System.out.println("The data has been saved!");
    }

    // Initiating the code for the DHD function
    @FXML
    private TextField horseID_DHD;

    public void DHD_submit(){
        String[][] horseData = f_obj.read_from_file(f_obj.find_f_path("horse_data.txt"));
        if (horseData.length == 0){
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Empty Table");
            alert.setHeaderText(null);
            alert.setContentText("The table is empty! There are no values to delete...");
            alert.showAndWait();
        }else{
            String horseID = horseID_DHD.getText();
        /*
        A try/catch error handling method is referred to here to make it such that only numeric values
        may be entered into the horseID field
         */
            Alert alert = new Alert(AlertType.WARNING);
            try{
                if (horseID.isEmpty()){

                    alert.setTitle("Missing Information");
                    alert.setHeaderText(null);
                    alert.setContentText("Ensure that the field is filled");
                    alert.showAndWait();
                    return; // Stop further execution
                } else if (Integer.parseInt(horseID) > horseData.length || Integer.parseInt(horseID) <= 0) {
                    alert.setTitle("Out Of Range");
                    alert.setHeaderText(null);
                    alert.setContentText("Ensure that the ID is in the table");
                    alert.showAndWait();
                    return; // Stop further execution
                }
                dhd DHD = new dhd(horseData);
                tempdata=DHD.DHD(horseID);
            }
            catch (Exception e){
                alert.setTitle("None Numeric Value");
                alert.setHeaderText(null);
                alert.setContentText("The value entered is not Numeric or does not have the form of an id (eg: 020) ");
                alert.showAndWait();
                return; // Stop further execution
            }
        }
    }

    public void save_DHD(){
        f_obj.write_to_file(f_obj.find_f_path("horse_data.txt"),tempdata);
        System.out.println("The data has been saved!");
    }

    // Initializing the Save Random Horses function

    // Simple method that is referred to by the "yes" button on the SRH page
    public void SRH_submit(){
        String[][] horseData = f_obj.read_from_file(f_obj.find_f_path("horse_data.txt"));
        srd SRD = new srd(horseData);
        tempdata=SRD.selectRandomHorses();

    }

    // Save file method for the srh page that checks and sees if the file exists, if it doesn't a new file is created
    public void save_SRD(){
        String randDataPath = f_obj.find_f_path(SRH_f_name);
        File randDataFile = new File(randDataPath);
        if (randDataFile.exists() && !randDataFile.isDirectory()){
            System.out.println("The file exists!");
        } else{
            System.out.printf("The file does not exist and will be saved to %s\n",randDataPath);
        }
        f_obj.write_to_file(randDataPath,tempdata);
        System.out.println("The data has been saved!");
    }

    // Initiating the Select Winning Horses function
    public void SWH_submit(){
        String randDataPath = f_obj.find_f_path(SRH_f_name);
        File randDataFile = new File(randDataPath);
        if (randDataFile.exists() && !randDataFile.isDirectory()){
            System.out.println("The file exists!");
            String[][] randData = f_obj.read_from_file(f_obj.find_f_path(SRH_f_name));
            swh SWH = new swh(randData);
            tempdata=SWH.SWH();
        } else{
            System.out.println("The file 'random_horses' does not exist! Run the SRD function first!");
        }


    }

    public void save_SWH(){
        f_obj.write_to_file(f_obj.find_f_path(WHD_f_name),tempdata);
        System.out.println("The data has been saved!");
    }

    // initializing the VWH function
    @FXML
    private BarChart<String,Integer> VWH_barchart; // Initializing the barchart variable from the VWH.fxml file

    @FXML
    private Button VWH_btn;
    public void generateGraph(){
        // Disable the button to prevent multiple clicks
        VWH_btn.setDisable(true);

        String winningDataPath = f_obj.find_f_path(WHD_f_name);
        File winningDataFile = new File(winningDataPath);
        if (winningDataFile.exists() && !winningDataFile.isDirectory()){
            System.out.println("The 'winning_horses' file exists!");
            String[][] winningData = f_obj.read_from_file(f_obj.find_f_path(WHD_f_name));

            // The XYchart function is utilized here to insert data as a series into the barchart
            // The sequence is set to take in a String value and then an Integer Value

            XYChart.Series<String,Integer> series1 = new XYChart.Series<>();
            series1.setName(winningData[0][0]);
            series1.getData().add(new XYChart.Data<>("",Integer.parseInt(winningData[0][1])));

            XYChart.Series<String,Integer> series2 = new XYChart.Series<>();
            series2.setName(winningData[1][0]);
            series2.getData().add(new XYChart.Data<>("",Integer.parseInt(winningData[1][1])));

            XYChart.Series<String,Integer> series3 = new XYChart.Series<>();
            series3.setName(winningData[2][0]);
            series3.getData().add(new XYChart.Data<>("",Integer.parseInt(winningData[2][1])));

            XYChart.Series<String,Integer> series4 = new XYChart.Series<>();
            series4.setName(winningData[3][0]);
            series4.getData().add(new XYChart.Data<>("",Integer.parseInt(winningData[3][1])));

            VWH_barchart.getData().addAll(series1,series2,series3,series4);
        } else{
            System.out.println("The file 'winning_horses' does not exist! Run the SWH function first!");
        }

    }

    @FXML
    private TextArea text_output_area;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (columnSort != null) {
            columnSort.getItems().addAll(headers);
            String value = columnSort.getValue();
            columnSort.setOnAction(this::test);
        }
        staticTxtArea = text_output_area;
        new ConsoleRedirect(text_output_area);
        staticTxtArea = text_output_area;
    }
}