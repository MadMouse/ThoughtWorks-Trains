package com.thoughtworks;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Set;

import static org.junit.Assert.*;

public class TrainRouteHelperTest {

    TrainRouteHelper trainHelper;

    @Before
    public void setUp(){
        final String[] VALID_ROUTES = {"AB5","BC4","CD8","DC8","DE6","AD5","CE2","EB3","AE7"};
        trainHelper = new TrainRouteHelper(VALID_ROUTES);
    }
    @Test
    public void routeArray_valid_ReturnsMap(){

        Assert.assertEquals(trainHelper.getValidRoutesMap().size(), 5);

        Assert.assertEquals(trainHelper.getValidRoutesMap().get('A').size(), 3);
        //AB5
        Assert.assertEquals(new Integer( 5),trainHelper.getValidRoutesMap().get('A').get('B'));
        //AE7
        Assert.assertEquals(new Integer( 7),trainHelper.getValidRoutesMap().get('A').get('E'));


        Assert.assertEquals(trainHelper.getValidRoutesMap().get('B').size(), 1);
        //BC4
        Assert.assertEquals(new Integer( 4),trainHelper.getValidRoutesMap().get('B').get('C'));


        Assert.assertEquals(trainHelper.getValidRoutesMap().get('C').size(), 2);
        //CD8
        Assert.assertEquals(new Integer( 8),trainHelper.getValidRoutesMap().get('C').get('D'));
        //CE8
        Assert.assertEquals(new Integer( 2),trainHelper.getValidRoutesMap().get('C').get('E'));

        Assert.assertEquals(trainHelper.getValidRoutesMap().get('D').size(), 2);
        //DC8
        Assert.assertEquals(new Integer( 8),trainHelper.getValidRoutesMap().get('D').get('C'));
        //DE6
        Assert.assertEquals(new Integer( 6),trainHelper.getValidRoutesMap().get('D').get('E'));


        Assert.assertEquals(trainHelper.getValidRoutesMap().get('E').size(), 1);
        //EB3
        Assert.assertEquals(new Integer( 3),trainHelper.getValidRoutesMap().get('E').get('B'));


    }

    @Test
    public void parseArray(){
        String routeString = "AB5,BC4,CD8,DC8,DE6,AD5,CE2,EB3,AE7";
        String[] routes = routeString.split(",");

        Assert.assertEquals(9, routes.length);
    }

    @Test
    public void routeStations_valid_ReturnDistance(){

        Integer routeDistance = trainHelper.getRouteDistance(new Character[]{'A','B','C'});

        Assert.assertEquals(new Integer(9),routeDistance);

        routeDistance = trainHelper.getRouteDistance(new Character[]{'A','D'});

        Assert.assertEquals(new Integer(5),routeDistance);

        routeDistance = trainHelper.getRouteDistance(new Character[]{'A','D','C'});

        Assert.assertEquals(new Integer(13),routeDistance);

        routeDistance = trainHelper.getRouteDistance(new Character[]{'A','E','B', 'C','D'});

        Assert.assertEquals(new Integer(22),routeDistance);

        routeDistance = trainHelper.getRouteDistance(new Character[]{'A','E','D'});

        Assert.assertEquals(new Integer(-1),routeDistance);
    }

    @Test
    public void routeStations_invalid_ReturnInvalid(){

        Integer routeDistance = trainHelper.getRouteDistance(new Character[]{'A','E','D'});

        Assert.assertEquals(new Integer(-1),routeDistance);
    }

    @Test
    public void routeStations_StartEndValid_RouteList(){

        Set<String> routes = trainHelper.buildRoutes('C','C');


        Assert.assertEquals(3,routes.size());

        Assert.assertTrue(routes.contains("CDC"));

        Assert.assertTrue(routes.contains("CDEBC"));

        Assert.assertTrue(routes.contains("CEBC"));


        routes = trainHelper.buildRoutes('A','B');

        Assert.assertEquals(4,routes.size());

        Assert.assertTrue(routes.contains("AB"));

        Assert.assertTrue(routes.contains("ADCEB"));

        Assert.assertTrue(routes.contains("ADEB"));

        Assert.assertTrue(routes.contains("AEB"));
    }

    @Test
    public void routeStations_StartEndInvalid_EmptyList(){

        Set<String> routes = trainHelper.buildRoutes('A','A');

        Assert.assertTrue(routes.isEmpty());
    }

    @Test
    public void routeStations_maxStops_ReturnCount(){

        Integer routeCount = trainHelper.findRouteStops('C','C',3,false);

        Assert.assertEquals(new Integer(2), routeCount);
    }

    @Test
    public void routeStations_requiredStops_ReturnCount(){

        Integer routeCount = trainHelper.findRouteCountForStops('A','C',4);

        Assert.assertEquals(new Integer(3), routeCount);

    }

    @Test
    public void routeStations_validRoute_ReturnShortestDistance() {

        Integer shortestDistance = trainHelper.findShortestDistance('A','C');

        Assert.assertEquals(new Integer(9), shortestDistance);


        shortestDistance = trainHelper.findShortestDistance('B','B');

        Assert.assertEquals(new Integer(9), shortestDistance);

        shortestDistance = trainHelper.findShortestDistance('C','D');

        Assert.assertEquals(new Integer(8), shortestDistance);

    }

    @Test
    public void routeStations_invalidRoute_ReturnInvalid(){

        Integer shortestDistance = trainHelper.findShortestDistance('A','A');

        Assert.assertEquals(new Integer(-1), shortestDistance);
    }

    @Test
    public void routeStations_validRouteMaxDistance_ReturnCount(){
        Integer routeCount = trainHelper.findRoutesInRange(new String[]{"CDC", "CEBC", "CEBCDC", "CDCEBC", "CDEBC", "CEBCEBC", "CEBCEBCEBC"}, 30);

        Assert.assertEquals(new Integer(7), routeCount);

        routeCount = trainHelper.findRoutesInRange(new String[]{"CDCDCDCDCDC","CEBCEBCEBC"}, 30);

        Assert.assertEquals(new Integer(1), routeCount);
    }

    @Test
    public void routeStations_InvalidRouteRange_ReturnCount(){

        //First Route are Invalid
        Integer routeCount = trainHelper.findRoutesInRange(new String[]{"CADCDCDCDCDC","CEBCEBCEBC"}, 30);

        Assert.assertEquals(new Integer(1), routeCount);

        //Both Routes are Invalid
        routeCount = trainHelper.findRoutesInRange(new String[]{"CADCDCDCDCDC","ACEBCEBCEBC"}, 30);

        Assert.assertEquals(new Integer(0), routeCount);
    }

}