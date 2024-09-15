Simple application developed for the horse race case study provided to us within the courseworks specifications. 

The functions available are as follows:

1. Adding Horse Details (AHD)
What this process executes is basically the addition of new data into the database. Each value in the table must be distinct from each other, especially the horse ID, and must not be a duplicate of each other when being entered into the table (exclusive of columns such as the horses breed to the horse’s performance since these values could potentially be the same).
Furthermore, the table is expected to have less than twenty values to ensure that there are only a total of twenty horses in the table.

3. Updating Horse Details (UHD)
The process executed here simply manipulates the data in the table by updating the data in each row by first finding the ID of the horse details. Thereafter, any changes made to the data here must be saved into the text file containing the horse details.

4. Deleting Horse Details (DHD)
With regard to what this process executes, all it simply does is delete either a complete row or a single value in the table. This offers the user the option to list out unnecessary details in the table and make room for new data and/or details that may be necessary to update.

5. Viewing Horse Details (VHD)
Regarding the VHD function, the code here is expected to present the details present in the table while ordering it by a column the user specifies. In other words, the data present in the table is simply presented using this function so that the user may be able to view the details present in the table without having to view the text file individually. 

6. Saving Horse Details (SHD)
The process executed by SHD simply saves the horse details to the text file. Any changes made to the file are saved into it from this command.

7. Selecting Different Details (SDD)
Here the code selects four different values/horses from the horse details table. Each horse that is selected is from each group as the code is expected to select four different horses from each distinct group without having values repeat.

8. Winning Horse Details (WHD)
Here the horse details are put through the process of having a value from 0¬90 seconds randomly assigned to them to have it assume how much time each horse had spend in the race. The horse with the shortest time assigned to them is the horse that wins while the horse with the longest time is the horse that comes out in the last place.

9. View Winning Horse Details (VWH)
For the final code/process, all it does is read the winning horse details file and present it using stars (*) having each star represent ten seconds. A place is further assigned to the winning horse. Refer to the following figure to see what the data is expected to come out as:

10. Consists of test cases as well (utilizing junit 5) to test each method and ensure that they work in multiple tests.
