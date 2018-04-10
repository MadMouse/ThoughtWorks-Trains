package com.thoughtworks;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *      Assumptions
 *      1.) if valid route is specified more than once the last one will be used.
 *      2.) Each Station would be represented by a single Character
 *      3.) The distance between each station would always be less than 10;
 *      4.) The route would always be represented by 3 characters <start><end><Distance>
 *      5.) All Route distances are Integers.
 *
 */
public class Trains {

    private static String parseRouteDistance(Integer distance){
        if(distance == -1){
            return "NO SUCH ROUTE";
        }
        return Integer.toString(distance);
    }


    private static String[] readValidRoutesFile(String fileName){
        String text = "";
        try {
            Scanner scanner = new Scanner(new File(fileName));
            while(scanner.hasNextLine()){
                text = scanner.next();
            }
        } catch (FileNotFoundException e){
            System.out.print("Route file not found!!!!!");
            return null;
        }
        return text.split(",");
    }

    public static void main(String[] args) {

        if (args.length == 0)
        {
            System.err.println ("No arguments Supplied!");
            System.err.println (" <Routes File>");
            System.exit(0);
        }

        String[] routes =  readValidRoutesFile(args[0].toString());


        System.out.println("ThoughtWorks Train Problem");
        TrainRouteHelper trainHelper = new TrainRouteHelper(routes);

        System.out.println("The distance of the route A-B-C");
        System.out.println("Output #1 : " + parseRouteDistance(trainHelper.getRouteDistance(new Character[]{'A','B','C'})));

        System.out.println("The distance of the route A-D");
        System.out.println("Output #2 : " + parseRouteDistance(trainHelper.getRouteDistance(new Character[]{'A','D'})));

        System.out.println("The distance of the route A-D-C");
        System.out.println("Output #3 : " + parseRouteDistance(trainHelper.getRouteDistance(new Character[]{'A','D','C'})));

        System.out.println("The distance of the route A-E-B-C-D");
        System.out.println("Output #4 : " + parseRouteDistance(trainHelper.getRouteDistance(new Character[]{'A','E','B','C','D'})));

        System.out.println("The distance of the route A-E-D");
        System.out.println("Output #5 : " + parseRouteDistance(trainHelper.getRouteDistance(new Character[]{'A','E','D'})));

        System.out.println("The number of trips starting at C and ending at C with a maximum of 3 stops");
        System.out.println("Output #6 : " + trainHelper.findRouteStops('C','C',3,false));

        System.out.println("The number of trips starting at A and ending at C with exactly 4 stops");
        System.out.println("Output #7 : " + trainHelper.findRouteCountForStops('A','C',4));

        System.out.println("The length of the shortest route (in terms of distance to travel) from A to C");
        System.out.println("Output #8 : " + parseRouteDistance(trainHelper.findShortestDistance('A','C')));

        System.out.println("The length of the shortest route (in terms of distance to travel) from B to B");
        System.out.println("Output #9 : " + parseRouteDistance(trainHelper.findShortestDistance('B','B')));

        System.out.println("The number of different routes from C to C with a distance of less than 30");
        System.out.println("data, the trips are: CDC, CEBC, CEBCDC, CDCEBC, CDEBC, CEBCEBC, CEBCEBCEBC");
        System.out.println("Output #10 : " + parseRouteDistance(trainHelper.findRoutesInRange(new String[]{"CDC", "CEBC", "CEBCDC", "CDCEBC", "CDEBC", "CEBCEBC", "CEBCEBCEBC"},
                30)));
    }
}
