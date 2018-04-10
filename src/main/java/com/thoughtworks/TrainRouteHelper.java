package com.thoughtworks;

import com.sun.istack.internal.NotNull;

import java.util.*;

public class TrainRouteHelper {

    private Map<Character,Map<Character,Integer>> validRoutesMap = null;

    public Map<Character, Map<Character, Integer>> getValidRoutesMap() {
        return validRoutesMap;
    }

    private Map<Character,Map<Character,Integer>> parseRoutes(String[] validRoutes){
        Map<Character,Map<Character,Integer>> internalRoutesMap = new HashMap<>();

        Arrays.sort(validRoutes);

        for(int i = 0; i < validRoutes.length;i++){
            if(internalRoutesMap.containsKey(validRoutes[i].toString().charAt(0))){
                internalRoutesMap.get(validRoutes[i].toString().charAt(0)).put(validRoutes[i].toString().charAt(1),
                        Integer.parseInt(validRoutes[i].substring(2)));

            } else{
                Map<Character,Integer> nodeMap = new HashMap<>();
                nodeMap.put(validRoutes[i].toString().charAt(1), Integer.parseInt(validRoutes[i].substring(2)));
                internalRoutesMap.put(validRoutes[i].toString().charAt(0),nodeMap);
            }
        }
        return internalRoutesMap;
    }

    public Integer getRouteDistance(Character[] route){
        if (route.length > 1) {
            Integer accumlatedDistance = 0;
            for (int i = 0; i < route.length; i++) {
                if(route.length > i + 1) {
                    String currentSearch = new String(new char[]{route[i], route[i + 1]});
                    if(validRoutesMap.containsKey(route[i]) && validRoutesMap.get(route[i]).containsKey(route[i + 1])){
                        accumlatedDistance += validRoutesMap.get(route[i]).get(route[i + 1]);
                    } else {
                        return -1;
                    }
                }
            }
            return accumlatedDistance;
        }
        return -1;
    }

    public TrainRouteHelper(String[] validRoutes){
        validRoutesMap = parseRoutes(validRoutes);
    }

    private List<String> buildStopsBaseRouteDSF(Character currentStation, Character endStation, Integer stopsRequired){
        List<String> routesAvailable = new ArrayList<>();
        if(currentStation.equals(endStation) && stopsRequired == 0){
            routesAvailable.add(endStation.toString());
            return routesAvailable;
        }


        Integer stopsLeft = stopsRequired - 1;
        if(stopsLeft >= 0) {
            for (Character nextStation : validRoutesMap.get(currentStation).keySet()) {
                List<String> validRoutes = buildStopsBaseRouteDSF(nextStation, endStation, stopsLeft);
                for (String route : validRoutes) {
                    routesAvailable.add(currentStation + route);
                }
            }
        }

        return routesAvailable;
    }

    private List<String> buildRouteDSF(Character currentStation, Character endStation, @NotNull Set<Character> stationVisited){
        List<String> routesAvailable = new ArrayList<>();
        if(currentStation.equals(endStation)){
            routesAvailable.add(endStation.toString());
            return routesAvailable;
        }


        for (Character nextStation : validRoutesMap.get(currentStation).keySet()) {
            Set<Character> visitedSnapshot = new HashSet<>();
            visitedSnapshot.addAll(stationVisited);
            if(!stationVisited.contains(nextStation)) {
                visitedSnapshot.add(currentStation);
                List<String> validRoutes = buildRouteDSF(nextStation, endStation, visitedSnapshot);
                for (String route : validRoutes) {
                    routesAvailable.add(currentStation + route);
                }
            }
        }

        return routesAvailable;
    }

    public Set<String> buildRoutes(char startStation, char endStation) {
        Set<String> knownRoutes = new HashSet<>();
        for(Character nextStation : validRoutesMap.get(startStation).keySet()){
            List<String> validRoutes = buildRouteDSF(nextStation, endStation, new HashSet<>());
            for (String route : validRoutes){
                knownRoutes.add(startStation + route);
            }
        }
        return knownRoutes;
    }

    public Integer findRouteStops(Character startStation, Character endStation, Integer stops, boolean explicit) {

        Set<String> routes = buildRoutes(startStation, endStation);

        Integer stations = stops++;

        for (Iterator<String> iterator = routes.iterator(); iterator.hasNext();) {
            String route =  iterator.next();
            if(explicit){
                if(!stations.equals(route.length()))
                    iterator.remove();
            } else {
                if (route.length()  <=  stations)
                    iterator.remove();
            }
        }
        return routes.size();
    }

    public Integer findShortestDistance(Character startStation, Character endStation) {
        Set<String> routes = buildRoutes(startStation,endStation);

        Integer shortestDistance = -1;

        for(String route : routes){
            Character[] routeArray = route.chars().mapToObj(c -> (char)c).toArray(Character[]::new);
            Integer distance = getRouteDistance(routeArray);
            if(distance == -1){
                return distance;
            }
            if(shortestDistance == -1 || shortestDistance > distance){
                shortestDistance = distance;
            }
        }

        return shortestDistance;
    }

    public Integer findRoutesInRange(String[] routes, int range) {

        Integer routeCount = 0;

        for(int i = 0; i < routes.length; i++){
            Character[] routeArray = routes[i].chars().mapToObj(c -> (char)c).toArray(Character[]::new);
            Integer distance = getRouteDistance(routeArray);
            if(distance > 0 && distance <= range){
                routeCount++;
            }
        }

        return routeCount;
    }

    public Integer findRouteCountForStops(Character startStation, Character endStation, int stopsRequired) {

        Set<String> foundRoutes = new HashSet<>();
        Integer stopsLeft = stopsRequired - 1; //0 Based
        for(Character nextStation : validRoutesMap.get(startStation).keySet()){
            List<String> validRoutes = buildStopsBaseRouteDSF(nextStation, endStation, stopsLeft);
            for (String route : validRoutes){
                foundRoutes.add(startStation + route);
            }
        }

        return foundRoutes.size();

    }
}
