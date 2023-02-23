# COM S 572: Lab 1

### Problem Description
In this programming assignment you will write a code to solve 8-puzzle problems. The objective
of the puzzle is to rearrange a given initial configuration (starting state) of 8 numbers on a 3 x 3
board into a final configuration (goal state) with a minimum number of actions.
For this assignment our goal state is:

        1 2 3

        4 5 6

        7 8 _

### Requirements
JDK 1.8 - is the baseline JDK against which this project is developed.

## Run Program on Terminal
### Compiling the program
1. Open your terminal and go to the project repository
2. Now, type **cd aima-core\src\main\java** and press **Enter** 
3. Again, type **javac .\poai572\lab1\EightPuzzleTerminalTest.java** and press **Enter**

### Run the program
1. Type **java .\poai572\lab1\EightPuzzleTerminalTest.java <file-path> <algorithm>**

>Example: **java .\poai572\lab1\EightPuzzleTerminalTest.java "D:\COM572\Assignment\part-2\S4.txt" "h1"** </br></br>
>**Output:** Total nodes generated, Total time taken, Path length, Path
>>This program can solve Eight Puzzle Problem with file algorithms: 
>> 1. **BFS:** Breadth First Search
>> 2. **IDS:** Iterative Deepening Search
>> 3. **h1:** A* Search with Misplaced Tile Heuristic
>> 4. **h2:** A* Search with Manhattan Distance Heuristic
>> 5. **h3:** A* Search With Direct Adjacent Tile Reversal Heuristic

## Run Single Problem with All the Algorithms

>**Input:** file-path
>**Output:** Total nodes generated, Total time taken, Path length, Path (for all algorithms)

1. Open the project repository
2. Go to <b>aima-core\src\main\java\poai572\lab1\</b> repository
3. Open the **EightPuzzleSingleFileTest.java** file
4. Set your file-path to final static **FILE_PATH** variable
5. Run the file


## Run Multiple Problem with Specific algorithm

>**Input:** folder-path, algorithm </br>
>**Output:** Average Execution Time, Average Nodes Generated

1. Open the project repository
2. Go to <b>aima-core\src\main\java\poai572\lab1\</b> repository
3. Open the **EightPuzzleMultipleFileTest.java** file
4. Set your folder-path to final static **FOLDER_PATH** variable
5. Set your algorithm to final static **ALGORITHM** variable
6. Run the file



