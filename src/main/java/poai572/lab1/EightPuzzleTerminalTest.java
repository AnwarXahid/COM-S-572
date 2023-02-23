package poai572.lab1;

import aima.core.environment.eightpuzzle.EightPuzzleBoard;
import poai572.util.Util;

import java.util.concurrent.*;

public class EightPuzzleTerminalTest {

    public static void main(String[] args) {
        String filePath = "";
        String algorithm = "";
        EightPuzzleBoard board;
        ExecutorService executor;
        Future<String> future;

        try {
            filePath = args[0];
            algorithm = args[1];
            board = Util.getEightPuzzleBoardFromFile(filePath);
        } catch (Exception ex) {
            System.out.println("Insufficient Input!");
            return;
        }

        if (!Util.isSolvable(board)) {
            return;
        }

        if (algorithm.equalsIgnoreCase("BFS")) {
            EightPuzzleWithBreadthFirstSearch.breadthFirstSearchForEightPuzzle(board);
        } else if (algorithm.equalsIgnoreCase("IDS")) {
            executor = Executors.newSingleThreadExecutor();
            future = executor.submit(new Task(board));
            Util.executeTimeout(executor, future);
        } else if (algorithm.equalsIgnoreCase("h1")) {
            EightPuzzleWithHeuristicSearch.misplacedTilesHeuristicForEightPuzzle(board);
        } else if (algorithm.equalsIgnoreCase("h2")) {
            EightPuzzleWithHeuristicSearch.manhattanDistanceHeuristicForEightPuzzle(board);
        } else if (algorithm.equalsIgnoreCase("h3")) {
            EightPuzzleWithHeuristicSearch.linearConflictHeuristicForEightPuzzle(board);
        }

    }

}
