# ThoughtWorks-Trains

###Problem one: Trains
 
The local commuter railroad services a number of towns in Kiwiland.  Because of monetary concerns, all of the tracks are 'one-way.'  That is, a route from Kaitaia to Invercargill does not imply the existence of a route from Invercargill to Kaitaia.  In fact, even if both of these routes do happen to exist, they are distinct and are not necessarily the same distance!
 
The purpose of this problem is to help the railroad provide its customers with information about the routes.  In particular, you will compute the distance along a certain route, the number of different routes between two towns, and the shortest route between two towns.
 
Input:  A directed graph where a node represents a town and an edge represents a route between two towns.  The weighting of the edge represents the distance between the two towns.  A given route will never appear more than once, and for a given route, the starting and ending town will not be the same town.
 
## Output
        Output: For test input 1 through 5, if no such route exists, output 'NO SUCH ROUTE'.  Otherwise, follow the route as given; do not make any extra stops!  For example, the first problem means to start at city A, then travel directly to city B (a distance of 5), then directly to city C (a distance of 4).
        The distance of the route A-B-C.
        The distance of the route A-D.
        The distance of the route A-D-C.
        The distance of the route A-E-B-C-D.
        The distance of the route A-E-D.
        The number of trips starting at C and ending at C with a maximum of 3 stops.  In the sample data below, there are two such trips: C-D-C (2 stops). and C-E-B-C (3 stops).
        The number of trips starting at A and ending at C with exactly 4 stops.  In the sample data below, there are three such trips: A to C (via B,C,D); A to C (via D,C,D); and A to C (via D,E,B).
        The length of the shortest route (in terms of distance to travel) from A to C.
        The length of the shortest route (in terms of distance to travel) from B to B.
        The number of different routes from C to C with a distance of less than 30.  In the sample data, the trips are: CDC, CEBC, CEBCDC, CDCEBC, CDEBC, CEBCEBC, CEBCEBCEBC.
         
## Test Input:
        For the test input, the towns are named using the first few letters of the alphabet from A to D.  A route between two towns (A to B) with a distance of 5 is represented as AB5.
        Graph: AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7
        Expected Output:
        Output #1: 9
        Output #2: 5
        Output #3: 13
        Output #4: 22
        Output #5: NO SUCH ROUTE
        Output #6: 2
        Output #7: 3
        Output #8: 9
        Output #9: 9
        Output #10: 7
        
        
# Application 

## Setup

       Extract files into a directory.
        
       Import application into IntelliJ.

<p align="center">
  <img src="https://github.com/MadMouse/ThoughtWorks-Trains/blob/master/images/1_import.png" width="350"/>
</p>    

        Select the gradle file.
<p align="center">
  <img src="https://github.com/MadMouse/ThoughtWorks-Trains/blob/master/images/2_select_gradle.png" width="350"/>
</p>    
       Make sure the JVM is 1.8 
<p align="center">
  <img src="https://github.com/MadMouse/ThoughtWorks-Trains/blob/master/images/3_gradle_jvm.png" width="350"/>
</p>    
        Select Ok on Import into Gradle
<p align="center">
  <img src="https://github.com/MadMouse/ThoughtWorks-Trains/blob/master/images/4_select_import_gradle.png" width="350"/>
</p>    
        There will be a box that appears in the bottom right corner select "Enable Auto-Import.
<p align="center">
  <img src="https://github.com/MadMouse/ThoughtWorks-Trains/blob/master/images/5_select_auto_import.png" width="350"/>
</p>    
        Navigate to the Train.java move down to Main method.
<p align="center">
  <img src="https://github.com/MadMouse/ThoughtWorks-Trains/blob/master/images/6_select_trains.png" width="350"/>
</p>    
        Click on the run icon nect to the method name.
<p align="center">
  <img src="https://github.com/MadMouse/ThoughtWorks-Trains/blob/master/images/7_run_app.png" width="350"/>
</p>    

       The excution will fail as the command line argument is missing, edit run configuration and 
       add command line argument to "validroutes.txt" as per the exceution section.
## Architecture       
    Folder
    
        Applicaction classes are stored below src folder.
        --> src
            Trains              - Main Execution Class.
            TrainRouteHelper    - TrainRouteHelper called from Main class to call all functions.
             
        Test classes are stored below test folder.
        --> test
            TrainTestHelperTest - TrainRouteHelper Tester class. 
    
        validroutes.txt - file containing valid routes array. 

## Execution
    
    --> First Parameter is the Valid Routes file. 
    Add args to the IntelliJ

<p align="center">
  <img src="https://github.com/MadMouse/ThoughtWorks-Trains/blob/master/images/intllij-args.png" width="350"/>
</p>    

## Console Output
        ThoughtWorks Train Problem
        ----- Loading Valid Routes 
            AB5,BC4,CD8,DC8,DE6,AD5,CE2,EB3,AE7,
        ----- Loading Valid Routes 
        The distance of the route A-B-C
        Output #1 : 9
        The distance of the route A-D
        Output #2 : 5
        The distance of the route A-D-C
        Output #3 : 13
        The distance of the route A-E-B-C-D
        Output #4 : 22
        The distance of the route A-E-D
        Output #5 : NO SUCH ROUTE
        The number of trips starting at C and ending at C with a maximum of 3 stops
        Output #6 : 2
        The number of trips starting at A and ending at C with exactly 4 stops
        Output #7 : 3
        The length of the shortest route (in terms of distance to travel) from A to C
        Output #8 : 9
        The length of the shortest route (in terms of distance to travel) from B to B
        Output #9 : 9
        The number of different routes from C to C with a distance of less than 30
        data, the trips are: CDC, CEBC, CEBCDC, CDCEBC, CDEBC, CEBCEBC, CEBCEBCEBC
        Output #10 : 7
        
        Process finished with exit code 0