import java.util.*;

public class WaterJugMain {
    private static ArrayList<Object> listOfAllJugs = new ArrayList<>();
    private static ArrayList<Integer> listOfJugs = new ArrayList<>();
    private static int targetJugValue;

    public static void setJugValues(){
        ReadFromFile read = new ReadFromFile();
        listOfAllJugs = read.readFile();
        listOfJugs = (ArrayList<Integer>) listOfAllJugs.get(0);
        targetJugValue = (int) listOfAllJugs.get(1);
    }

    public static List<List<Integer>> getFutureStates(List<Integer> state, ArrayList<Integer> capacities) {
        List<List<Integer>> nextStates = new ArrayList<>();
        // fill the pitcher with water
        for (int i = 1; i < state.size(); i++) {
            List<Integer> newState = new ArrayList<>(state);
            if (!state.get(i).equals(capacities.get(i))) {
                newState.set(i, capacities.get(i));
                nextStates.add(newState);
            }
        }
        // empty the pitcher into the ground
        for (int i = 1; i < state.size(); i++) {
            if (state.get(i) > 0) {
                List<Integer> newState = new ArrayList<>(state);
                newState.set(i, 0);
                nextStates.add(newState);
            }
        }
        // pour water from one pitcher to another
        for (int i = 1; i < state.size(); i++) {
            for (int j = 1; j < state.size(); j++) {
                if (i != j && state.get(i) > 0) {
                    List<Integer> newState = new ArrayList<>(state);
                    int transfer = Math.min(state.get(i), capacities.get(j) - state.get(j));
                    newState.set(i, state.get(i) - transfer);
                    newState.set(j, state.get(j) + transfer);
                    nextStates.add(newState);
                }
            }
        }
        // empty the pitcher into the "infinite" pitcher
        for (int i = 1; i < state.size(); i++) {
            if (state.get(i) > 0) {
                List<Integer> newState = new ArrayList<>(state);
                newState.set(0, state.get(0) + state.get(i));
                newState.set(i, 0);
                nextStates.add(newState);
            }
        }
        return nextStates;
    }

    public static double calculateHeuristics(int cost, List<Integer> currentJugState, int targetValue) {
        int a = 1;
        if (targetValue<500){
            a = 9;
        }
        else if(targetValue<2000){
            a = 6;
        }
        else if(targetValue<10000){
            a = 4;
        }
        else{
            a = 1;
        }
        List<Integer> intermediateTotal = new ArrayList<>();
        int requiredAmount = targetValue - currentJugState.get(0);
        for (int i = 1; i < currentJugState.size(); i++) {
            intermediateTotal.add(Math.abs(currentJugState.get(i) - requiredAmount));
        }
        return (double) ((Collections.min(intermediateTotal))/a) + cost;
    }

    public static int aStarSearch(ArrayList<Integer> capacities, int targetValue) {
        PriorityQueue<Map.Entry<List<Integer>, Double>> openStates = new PriorityQueue<>(Map.Entry.comparingByValue());
        List<List<Integer>> closedStates = new ArrayList<>();
        List<Integer> initialState = new ArrayList<>();
        List<List<Integer>> path = new ArrayList<>();
        int goalState = targetValue;
        for(int i = 0; i < capacities.size()+1; i++){
            initialState.add(0);
        }
        openStates.offer(new AbstractMap.SimpleEntry<>(initialState,0.0));
        while (!openStates.isEmpty()) {
//            System.out.println("heuristics: " + openStates.peek().getValue());
            List<Integer> lowestCostState = openStates.poll().getKey();
            int currentCost = lowestCostState.get(lowestCostState.size()-1);
            lowestCostState.remove(lowestCostState.size()-1);
            closedStates.add(lowestCostState);
            path.add(lowestCostState);
            if (lowestCostState.get(0).equals(goalState)) {
//                System.out.println(path);
                return currentCost;
            }
            List<List<Integer>> nextStates = getFutureStates(lowestCostState, capacities);
            for (List<Integer> nextState : nextStates) {
                if (!closedStates.contains(nextState) && nextState.get(0) <= targetValue) {
                    double heuristic = calculateHeuristics(currentCost, nextState, targetValue);
//                    System.out.println(nextState + " Heuristic: "+ heuristic);
                    nextState.add(currentCost+1);
                    openStates.offer(new AbstractMap.SimpleEntry<>(nextState,heuristic));
                }
            }
        }
//        System.out.println(path);
        return -1;
    }

    public static void main(String[] args){
        setJugValues();
        System.out.println(aStarSearch(listOfJugs,targetJugValue));
        }

    }

