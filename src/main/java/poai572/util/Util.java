/* Utility class
@author Anwar Hossain Zahid
 */

package poai572.util;

import aima.core.agent.Action;
import aima.core.environment.eightpuzzle.EightPuzzleBoard;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.*;

public class Util {

    /* Get Eight Puzzle Board. Use file path to read the file. After reading file
    Return a Eight Puzzle Board
    @param file path
    @return Eight Board Puzzle object
     */
    public static EightPuzzleBoard getEightPuzzleBoardFromFile(String filePath) {
        EightPuzzleBoard eightPuzzleBoard = null;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
            int[] state = new int[9];
            String line;
            String[] lines;

            for (int i = 0; i < 3; ++i) {
                line = bufferedReader.readLine();
                lines = line.trim().split("\\s+");

                for (int j = i * 3; j < (i + 1) * 3; j++) {
                    if (lines[j % 3].equals("_")) {
                        state[j] = 0;
                    } else {
                        state[j] = Integer.parseInt(lines[j % 3]);
                    }
                }
            }

            eightPuzzleBoard = new EightPuzzleBoard(state);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return eightPuzzleBoard;
    }


    /* Check whether the puzzle solvable or not

    @param eight puzzle board
    @return true if the problem is solvable or vice-versa
     */
    public static boolean isSolvable(EightPuzzleBoard board) {
        int[] state = board.getState();
        int numberOfInversion = 0;

        for (int i = 0; i < 9; ++i) {
            for (int j = i + 1; j < 9; ++j) {
                if (state[i] == 0)
                    break;
                if (state[j] == 0)
                    continue;
                if (state[i] > state[j])
                    numberOfInversion += 1;
            }
        }

        if (numberOfInversion % 2 == 0)
            return true;

        System.out.println("The inputted puzzle is not solvable: ");
        System.out.println(board);

        return false;
    }

    /* Convert millisecond to human readable time

    @param time in  millisecond
    @return human readable time
     */
    public static String convertMillisecond(long timeInMillisecond) {
        String humanReadableTime = "";
        long minutes, seconds;
        minutes = timeInMillisecond / (60 * 1000);
        timeInMillisecond -= minutes * 60 * 1000;
        seconds = timeInMillisecond / 1000;
        timeInMillisecond -= seconds * 1000;

        if (minutes != 0)
            humanReadableTime += minutes + " min ";
        if (seconds != 0)
            humanReadableTime += seconds + " sec ";
        if (timeInMillisecond != 0)
            humanReadableTime += timeInMillisecond * 1000 + " microSec";

        return humanReadableTime;
    }

    /* Get path string from action list

    @param action list
    @return path string containing the first alphabet of each action name
     */
    public static String getPathFromActionList(List<Action> actionList) {
        StringBuilder path = new StringBuilder();

        for (Action action: actionList) {
            if (action.toString().equals("Action[name=Down]"))
                path.append("U");
            else if (action.toString().equals("Action[name=Up]"))
                path.append("D");
            else if (action.toString().equals("Action[name=Right]"))
                path.append("L");
            else
                path.append("R");
        }

        return path.toString();
    }


    /*
    Get list of file path in a folder

    @param folder path
    @return list of file path
     */
    public static List<String> getFilePathListFromFolder(File folder) {
        List<String> filePathList = new ArrayList<>();

        for (File fileEntry : Objects.requireNonNull(folder.listFiles())) {
            if (fileEntry.isFile())
                filePathList.add(fileEntry.getPath());
        }

        return filePathList;
    }

    /* Stop the execution after 15 minutes
    @param ExecutorService
    @param future
     */
    public static void executeTimeout(ExecutorService executor, Future<String> future) {
        try {
            future.get(15, TimeUnit.MINUTES);
        } catch (TimeoutException | InterruptedException | ExecutionException e) {
            future.cancel(true);
            System.out.println("Total nodes generated: <<??>>");
            System.out.println("Total time taken: >15 min");
            System.out.println("Path length: Timed out!");
            System.out.println("Path: Timed out!");
            System.out.println("\n\n");
        }
        executor.shutdown();
    }

}
