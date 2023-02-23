package poai572.lab1;

import aima.core.agent.Action;
import aima.core.environment.eightpuzzle.EightPuzzleBoard;
import aima.core.environment.eightpuzzle.EightPuzzleFunctions;
import aima.core.search.framework.SearchForActions;
import aima.core.search.framework.problem.GeneralProblem;
import aima.core.search.framework.problem.Problem;
import aima.core.search.framework.qsearch.GraphSearch;
import aima.core.search.informed.AStarSearch;
import poai572.util.Util;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class EightPuzzleWithHeuristicSearch {
    /* Run Misplaced Tiles Heuristic on Eight Puzzle Problem
        @param eight puzzle board
     */
    public static void misplacedTilesHeuristicForEightPuzzle(EightPuzzleBoard board) {
        long start, timeTaken;

        Problem<EightPuzzleBoard, Action> problem = new GeneralProblem<>(board, EightPuzzleFunctions::getActions,
                EightPuzzleFunctions::getResult, Predicate.isEqual(EightPuzzleFunctions.GOAL_STATE));

        start = System.currentTimeMillis();
        SearchForActions<EightPuzzleBoard, Action> search = new AStarSearch<>(new GraphSearch<>(),
                EightPuzzleFunctions::getNumberOfMisplacedTiles);
        Optional<List<Action>> actions = search.findActions(problem);
        timeTaken = System.currentTimeMillis() - start;

        System.out.println("Total nodes generated: " + (Integer.parseInt(search.getMetrics().get("nodesExpanded"))
                + Integer.parseInt(search.getMetrics().get("queueSize"))));
        System.out.println("Total time taken: " + Util.convertMillisecond(timeTaken));
        System.out.println("Path length: " + search.getMetrics().get("pathCost"));
        System.out.println("Path: " + Util.getPathFromActionList(actions.get()));
    }

    /* Run Manhattan Distance Heuristic on Eight Puzzle Problem
        @param eight puzzle board
     */
    public static void manhattanDistanceHeuristicForEightPuzzle(EightPuzzleBoard board) {
        long start, timeTaken;

        Problem<EightPuzzleBoard, Action> problem = new GeneralProblem<>(board, EightPuzzleFunctions::getActions,
                EightPuzzleFunctions::getResult, Predicate.isEqual(EightPuzzleFunctions.GOAL_STATE));

        start = System.currentTimeMillis();
        SearchForActions<EightPuzzleBoard, Action> search = new AStarSearch<>(new GraphSearch<>(),
                EightPuzzleFunctions::getManhattanDistance);
        Optional<List<Action>> actions = search.findActions(problem);
        timeTaken = System.currentTimeMillis() - start;

        System.out.println("Total nodes generated: " + (Integer.parseInt(search.getMetrics().get("nodesExpanded"))
                + Integer.parseInt(search.getMetrics().get("queueSize"))));
        System.out.println("Total time taken: " + Util.convertMillisecond(timeTaken));
        System.out.println("Path length: " + search.getMetrics().get("pathCost"));
        System.out.println("Path: " + Util.getPathFromActionList(actions.get()));
    }

    /*Run number of direct adjacent tile reversals Heuristic on Eight Puzzle Problem

    @param eight puzzle board
     */
    public static void linearConflictHeuristicForEightPuzzle(EightPuzzleBoard board) {
        long start, timeTaken;

        Problem<EightPuzzleBoard, Action> problem = new GeneralProblem<>(board, EightPuzzleFunctions::getActions,
                EightPuzzleFunctions::getResult, Predicate.isEqual(EightPuzzleFunctions.GOAL_STATE));

        start = System.currentTimeMillis();
        SearchForActions<EightPuzzleBoard, Action> search = new AStarSearch<>(new GraphSearch<>(),
                EightPuzzleFunctions::getLinearConflict);
        Optional<List<Action>> actions = search.findActions(problem);
        timeTaken = System.currentTimeMillis() - start;

        System.out.println("Total nodes generated: " + (Integer.parseInt(search.getMetrics().get("nodesExpanded"))
                + Integer.parseInt(search.getMetrics().get("queueSize"))));
        System.out.println("Total time taken: " + Util.convertMillisecond(timeTaken));
        System.out.println("Path length: " + search.getMetrics().get("pathCost"));
        System.out.println("Path: " + Util.getPathFromActionList(actions.get()));
    }
}
