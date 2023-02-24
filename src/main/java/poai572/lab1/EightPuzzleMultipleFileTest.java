package poai572.lab1;

import aima.core.agent.Action;
import aima.core.environment.eightpuzzle.EightPuzzleBoard;
import aima.core.environment.eightpuzzle.EightPuzzleFunctions;
import aima.core.search.framework.SearchForActions;
import aima.core.search.framework.problem.GeneralProblem;
import aima.core.search.framework.problem.Problem;
import aima.core.search.framework.qsearch.GraphSearch;
import aima.core.search.informed.AStarSearch;
import aima.core.search.uninformed.BreadthFirstSearch;
import aima.core.search.uninformed.IterativeDeepeningSearch;
import lombok.Getter;
import lombok.Setter;
import poai572.util.Util;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.*;
import java.util.function.Predicate;

/* This class is intended to test multiple problem with a specific algorithm
@author Anwar Hossain Zahid
 */
public class EightPuzzleMultipleFileTest {
    private static final String FOLDER_PATH = "D:\\Study\\ISU\\Spring 2023\\COM 572\\Lab - 1\\Part3\\L8";
    private static final String ALGORITHM = "IDS";

    public static void main(String[] args) {
        EightPuzzleBoard board;
        ExecutionResult executionResult = new ExecutionResult();
        long executionTime = 0;
        long nodeGenerated = 0;

        List<String> filePathList = Util.getFilePathListFromFolder(new File(FOLDER_PATH));

        for (String filePath : filePathList) {
            board = Util.getEightPuzzleBoardFromFile(filePath);

            if (ALGORITHM.equalsIgnoreCase("BFS")) {
                executionResult = eightPuzzleMultipleFileWithAlgo(board, "BFS");
            } else if (ALGORITHM.equalsIgnoreCase("IDS")) {
                executionResult = eightPuzzleMultipleFileWithAlgo(board, "IDS");
            } else if (ALGORITHM.equalsIgnoreCase("h1")) {
                executionResult = eightPuzzleMultipleFileWithAlgo(board, "h1");
            } else if (ALGORITHM.equalsIgnoreCase("h2")) {
                executionResult = eightPuzzleMultipleFileWithAlgo(board, "h2");
            } else if (ALGORITHM.equalsIgnoreCase("h3")) {
                executionResult = eightPuzzleMultipleFileWithAlgo(board, "h3");
            }


            executionTime += executionResult.getExecutionTime();
            nodeGenerated += executionResult.getNodeGenerated();
        }

        System.out.println("Average Execution Time: " + executionTime / filePathList.size());
        System.out.println("Average Nodes Generated: " + nodeGenerated / filePathList.size());
    }


    /* Finds execution result for a single problem
    @param eight puzzle board
    @param algorithm

    @return execution result
     */
    private static ExecutionResult eightPuzzleMultipleFileWithAlgo(EightPuzzleBoard board, String algorithm) {
        ExecutionResult executionResult = new ExecutionResult();
        SearchForActions<EightPuzzleBoard, Action> search = null;
        long start, timeTaken;
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<ExecutionResult> future;

        Problem<EightPuzzleBoard, Action> problem = new GeneralProblem<>(board, EightPuzzleFunctions::getActions,
                EightPuzzleFunctions::getResult, Predicate.isEqual(EightPuzzleFunctions.GOAL_STATE));

        if (algorithm.equalsIgnoreCase("IDS")) {
            future = executor.submit(new IdsTask(problem));
            try {
                return future.get(15, TimeUnit.MINUTES);
            } catch (TimeoutException | InterruptedException | ExecutionException e) {
                future.cancel(true);
                executor.shutdown();
                return new ExecutionResult();
            }
        }

        start = System.currentTimeMillis();
        if (algorithm.equalsIgnoreCase("BFS")) {
            search = new BreadthFirstSearch<>(new GraphSearch<>());
        } else if (algorithm.equalsIgnoreCase("h1")) {
            search = new AStarSearch<>(new GraphSearch<>(),
                    EightPuzzleFunctions::getNumberOfMisplacedTiles);
        } else if (algorithm.equalsIgnoreCase("h2")) {
            search = new AStarSearch<>(new GraphSearch<>(),
                    EightPuzzleFunctions::getManhattanDistance);
        } else if (algorithm.equalsIgnoreCase("h3")) {
            search = new AStarSearch<>(new GraphSearch<>(),
                    EightPuzzleFunctions::getLinearConflict);
        }
        Optional<List<Action>> actions = search.findActions(problem);
        timeTaken = System.currentTimeMillis() - start;

        executionResult.setExecutionTime(timeTaken);


        executionResult.setNodeGenerated(Long.parseLong(search.getMetrics().get("nodesExpanded")) +
                Long.parseLong(search.getMetrics().get("queueSize")));


        return executionResult;
    }

}





/* This class is intended to keep track the execution for single problem
@author Anwar Hossain Zahid
 */
@Setter
@Getter
class ExecutionResult {
    private long executionTime;
    private long nodeGenerated;

    public ExecutionResult() {
        executionTime = 0;
        nodeGenerated = 0;
    }
}





/* This class implements callable in order to cap the execution time
@author Anwar Hossain Zahid
 */
class IdsTask implements Callable<ExecutionResult> {
    Problem<EightPuzzleBoard, Action> problem;

    IdsTask(Problem<EightPuzzleBoard, Action> problem) {
        this.problem = problem;
    }

    @Override
    public ExecutionResult call() throws Exception {
        long start = System.currentTimeMillis();
        SearchForActions<EightPuzzleBoard, Action> search = new IterativeDeepeningSearch<>();
        Optional<List<Action>> actions = search.findActions(problem);
        long timeTaken = System.currentTimeMillis() - start;
        ExecutionResult result = new ExecutionResult();
        result.setExecutionTime(timeTaken);
        result.setNodeGenerated(Long.parseLong(search.getMetrics().get("nodesExpanded")));
        return result;
    }
}
