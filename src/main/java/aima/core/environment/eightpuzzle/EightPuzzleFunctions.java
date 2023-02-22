package aima.core.environment.eightpuzzle;

import aima.core.agent.Action;
import aima.core.search.framework.Node;
import aima.core.util.datastructure.XYLocation;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Useful functions for solving EightPuzzle problems.
 * @author Ruediger Lunde
 */
public class EightPuzzleFunctions {

	public static final EightPuzzleBoard GOAL_STATE = new EightPuzzleBoard(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 0 });

	public static List<Action> getActions(EightPuzzleBoard state) {
		return Stream.of(EightPuzzleBoard.UP, EightPuzzleBoard.DOWN, EightPuzzleBoard.LEFT, EightPuzzleBoard.RIGHT).
				filter(state::canMoveGap).collect(Collectors.toList());
	}

	public static EightPuzzleBoard getResult(EightPuzzleBoard state, Action action) {
		EightPuzzleBoard result = state.clone();

		if (result.canMoveGap(action)) {
			if (action == EightPuzzleBoard.UP)
				result.moveGapUp();
			else if (action == EightPuzzleBoard.DOWN)
				result.moveGapDown();
			else if (action == EightPuzzleBoard.LEFT)
				result.moveGapLeft();
			else if (action == EightPuzzleBoard.RIGHT)
				result.moveGapRight();
		}
		return result;
	}

	public static double getManhattanDistance(Node<EightPuzzleBoard, Action> node) {
		EightPuzzleBoard currState = node.getState();
		int result = 0;
		for (int val = 1; val <= 8; val++) {
			XYLocation locCurr = currState.getLocationOf(val);
			XYLocation locGoal = GOAL_STATE.getLocationOf(val);
			result += Math.abs(locGoal.getX() - locCurr.getX());
			result += Math.abs(locGoal.getY() - locCurr.getY());
		}
		return result;
	}

	public static int getNumberOfMisplacedTiles(Node<EightPuzzleBoard, Action> node) {
		EightPuzzleBoard currState = node.getState();
		int result = 0;
		for (int val = 1; val <= 8; val++)
			if (!(currState.getLocationOf(val).equals(GOAL_STATE.getLocationOf(val))))
				result++;
		return result;
	}

	/*
	This heuristic uses the fact that if two tiles are misplaced, in the same line,
	and their goal positions are in the same line, this will add at least two moves
	to the manhattan distance between these tiles.

	@param node in search space
	@return manhattanHeuristic + number of adjacent reversal for each tiles
	 */
	public static int getLinearConflict(Node<EightPuzzleBoard, Action> node) {
		EightPuzzleBoard currState = node.getState();
		int numberOfAdjacentReversal = 0;
		int valAdj;

		// Calculate misplaced adjacent tiles
		for (int val = 1; val <= 8; val++) {
			switch (val) {
				case 1:
					if (currState.getValueAt(new XYLocation(1,0)) == 1 &&
							currState.getValueAt(new XYLocation(0,0)) == 2) {
						numberOfAdjacentReversal++;
					} else if (currState.getValueAt(new XYLocation(0,1)) == 1 &&
							currState.getValueAt(new XYLocation(0,0)) == 4) {
						numberOfAdjacentReversal++;
					}
					break;

				case 2:
					if (currState.getValueAt(new XYLocation(0,0)) == 2 &&
							currState.getValueAt(new XYLocation(1,0)) == 1) {
						numberOfAdjacentReversal++;
					} else if (currState.getValueAt(new XYLocation(2,0)) == 2 &&
							currState.getValueAt(new XYLocation(1,0)) == 3) {
						numberOfAdjacentReversal++;
					} else if (currState.getValueAt(new XYLocation(1,1)) == 2 &&
							currState.getValueAt(new XYLocation(1,0)) == 5) {
						numberOfAdjacentReversal++;
					}
					break;

				case 3:
					if (currState.getValueAt(new XYLocation(1,0)) == 3 &&
							currState.getValueAt(new XYLocation(2,0)) == 2) {
						numberOfAdjacentReversal++;
					} else if (currState.getValueAt(new XYLocation(2,1)) == 3 &&
							currState.getValueAt(new XYLocation(2,0)) == 6) {
						numberOfAdjacentReversal++;
					}
					break;

				case 4:
					if (currState.getValueAt(new XYLocation(0,0)) == 4 &&
							currState.getValueAt(new XYLocation(0,1)) == 1) {
						numberOfAdjacentReversal++;
					} else if (currState.getValueAt(new XYLocation(1,1)) == 4 &&
							currState.getValueAt(new XYLocation(0,1)) == 5) {
						numberOfAdjacentReversal++;
					} else if (currState.getValueAt(new XYLocation(0,2)) == 4 &&
							currState.getValueAt(new XYLocation(0,1)) == 7) {
						numberOfAdjacentReversal++;
					}
					break;

				case 5:
					if (currState.getValueAt(new XYLocation(1,0)) == 5 &&
							currState.getValueAt(new XYLocation(1,1)) == 2) {
						numberOfAdjacentReversal++;
					} else if (currState.getValueAt(new XYLocation(0,1)) == 5 &&
							currState.getValueAt(new XYLocation(1,1)) == 4) {
						numberOfAdjacentReversal++;
					} else if (currState.getValueAt(new XYLocation(2,1)) == 5 &&
							currState.getValueAt(new XYLocation(1,1)) == 6) {
						numberOfAdjacentReversal++;
					} else if (currState.getValueAt(new XYLocation(1,2)) == 5 &&
							currState.getValueAt(new XYLocation(1,1)) == 8) {
						numberOfAdjacentReversal++;
					}
					break;

				case 6:
					if (currState.getValueAt(new XYLocation(2,0)) == 6 &&
							currState.getValueAt(new XYLocation(2,1)) == 3) {
						numberOfAdjacentReversal++;
					} else if (currState.getValueAt(new XYLocation(1,1)) == 6 &&
							currState.getValueAt(new XYLocation(2,1)) == 5) {
						numberOfAdjacentReversal++;
					}
					break;

				case 7:
					if (currState.getValueAt(new XYLocation(0,1)) == 7 &&
							currState.getValueAt(new XYLocation(0,2)) == 4) {
						numberOfAdjacentReversal++;
					} else if (currState.getValueAt(new XYLocation(1,2)) == 7 &&
							currState.getValueAt(new XYLocation(0,2)) == 8) {
						numberOfAdjacentReversal++;
					}
					break;

				case 8:
					if (currState.getValueAt(new XYLocation(1,1)) == 8 &&
							currState.getValueAt(new XYLocation(1,2)) == 5) {
						numberOfAdjacentReversal++;
					} else if (currState.getValueAt(new XYLocation(0,2)) == 8 &&
							currState.getValueAt(new XYLocation(1,2)) == 7) {
						numberOfAdjacentReversal++;
					}
					break;
			}
		}

		return getNumberOfMisplacedTiles(node) + numberOfAdjacentReversal;
	}
}