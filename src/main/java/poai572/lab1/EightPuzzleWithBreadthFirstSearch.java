package poai572.lab1;

import aima.core.agent.Action;
import aima.core.environment.eightpuzzle.EightPuzzleBoard;
import aima.core.environment.eightpuzzle.EightPuzzleFunctions;
import aima.core.search.framework.SearchForActions;
import aima.core.search.framework.problem.GeneralProblem;
import aima.core.search.framework.problem.Problem;
import aima.core.search.framework.qsearch.GraphSearch;
import aima.core.search.uninformed.BreadthFirstSearch;
import poai572.util.Util;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;


/* Using BFS technique to solve eight puzzle problem
@author Anwar Hossain Zahid
 */
public class EightPuzzleWithBreadthFirstSearch {

    /* Run BFS on Eight Puzzle Problem
        @param eight puzzle board
     */
    public static void breadthFirstSearchForEightPuzzle(EightPuzzleBoard board) {
        long start, timeTaken;

        Problem<EightPuzzleBoard, Action> problem = new GeneralProblem<>(board, EightPuzzleFunctions::getActions,
                EightPuzzleFunctions::getResult, Predicate.isEqual(EightPuzzleFunctions.GOAL_STATE));

        start = System.currentTimeMillis();
        SearchForActions<EightPuzzleBoard, Action> search = new BreadthFirstSearch<>(new GraphSearch<>());
        Optional<List<Action>> actions = search.findActions(problem);
        timeTaken = System.currentTimeMillis() - start;

        System.out.println("Total nodes generated: " + (Integer.parseInt(search.getMetrics().get("nodesExpanded"))
                + Integer.parseInt(search.getMetrics().get("queueSize"))));
        System.out.println("Total time taken: " + Util.convertMillisecond(timeTaken));
        System.out.println("Path length: " + search.getMetrics().get("pathCost"));
        System.out.println("Path: " + Util.getPathFromActionList(actions.get()));
    }

}
