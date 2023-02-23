/* EightPuzzleSingleFileTest class
@author Anwar Hossain Zahid
 */

package poai572.lab1;

import aima.core.environment.eightpuzzle.EightPuzzleBoard;
import poai572.util.Util;

import java.util.concurrent.*;

public class EightPuzzleSingleFileTest {
    private static final String FILE_PATH = "D:\\Study\\ISU\\Spring 2023\\COM 572\\Lab - 1\\Part2\\Part2\\S4.txt";

    public static void main(String[] args) {
        EightPuzzleBoard board = Util.getEightPuzzleBoardFromFile(FILE_PATH);

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> future = executor.submit(new Task(board));

        if (!Util.isSolvable(board)) {
            return;
        }

        Util.executeTimeout(executor, future);

        System.out.println("Solving Eight Puzzle with Breadth First Search:");
        EightPuzzleWithBreadthFirstSearch.breadthFirstSearchForEightPuzzle(board);
        System.out.println("\n\n");

        System.out.println("Solving Eight Puzzle with A* Search (Misplaced Tiles Heuristic):");
        EightPuzzleWithHeuristicSearch.misplacedTilesHeuristicForEightPuzzle(board);
        System.out.println("\n\n");

        System.out.println("Solving Eight Puzzle with A* Search (Manhattan Distance Heuristic):");
        EightPuzzleWithHeuristicSearch.manhattanDistanceHeuristicForEightPuzzle(board);
        System.out.println("\n\n");

        System.out.println("Solving Eight Puzzle with A* Search (Direct Adjacent Tile Reversal Heuristic):");
        EightPuzzleWithHeuristicSearch.linearConflictHeuristicForEightPuzzle(board);
        System.out.println("\n\n");

    }
}

class Task implements Callable<String> {
    EightPuzzleBoard board;

    Task(EightPuzzleBoard board) {
        this.board = board;
    }
    @Override
    public String call() throws Exception {
        System.out.println("Solving Eight Puzzle with Iterative Deepening Search:");
        EightPuzzleWithIterativeDeepeningSearch.iterativeDeepeningSearchForEightPuzzle(board);
        System.out.println("\n\n");
        return "Stop Execution!";
    }
}
