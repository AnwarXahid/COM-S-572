/* EightPuzzleSingleFileTest class
@author Anwar Hossain Zahid
 */

package poai572.lab1;

import aima.core.environment.eightpuzzle.EightPuzzleBoard;
import poai572.util.Util;

public class EightPuzzleSingleFileTest {

    public static void main(String[] args) {
        String filePath = "";
        String algorithm = "";
        EightPuzzleBoard board;

//        try {
//            filePath = args[0];
//            algorithm = args[1];
//        } catch (Exception ex) {
//            System.out.println("Insufficient Input!");
//            return;
//        }

        filePath = "D:\\Study\\ISU\\Spring 2023\\COM 572\\Lab - 1\\Part2\\Part2\\S5.txt";
        algorithm = "h1";

        board = Util.getEightPuzzleBoardFromFile(filePath);

        if (!Util.isSolvable(board)) {
            return;
        }

        if (algorithm.equalsIgnoreCase("BFS")) {
            EightPuzzleWithBreadthFirstSearch.breadthFirstSearchForEightPuzzle(board);
        } else if (algorithm.equalsIgnoreCase("IDS")) {
            EightPuzzleWithIterativeDeepeningSearch.iterativeDeepeningSearchForEightPuzzle(board);
        } else if (algorithm.equalsIgnoreCase("h1")) {
            EightPuzzleWithHeuristicSearch.misplacedTilesHeuristicForEightPuzzle(board);
        } else if (algorithm.equalsIgnoreCase("h2")) {
            EightPuzzleWithHeuristicSearch.manhattanDistanceHeuristicForEightPuzzle(board);
        } else if (algorithm.equalsIgnoreCase("h3")) {
            EightPuzzleWithHeuristicSearch.linearConflictHeuristicForEightPuzzle(board);
        }

    }
}
