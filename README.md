# Corre Cabrito

This is a two-player strategy mini-game developed in Java as part of the practical work for the Object-Oriented Programming course in Software Engineering at Unipampa.

## The Game

The "Corre Cabrito" game takes place on a pentagon-shaped board with a central point. One player controls the **Carcará** (the hunter) and the other controls the **Cabrito** (the prey).

### Objective

*   **Carcará Player**: Capture the Cabrito in the fewest moves possible.
*   **Cabrito Player**: Escape from the Carcará for as long as possible.

## How to Play

### Initial Setup
The game begins with the Carcará positioned on the top vertex of the pentagon and the Cabrito in the central space, as shown in the image below:


### Movement
1.  The game starts with the Cabrito's move.
2.  Players alternate turns, moving their pieces to an adjacent empty space. The lines on the board indicate the possible paths.
3.  A piece can only move to a space that is directly connected by a line.

### Cabrito's Super Jump
*   The Cabrito is entitled to a **"super jump"** once per game.
*   This special move allows the Cabrito to jump to any other empty space on the board, regardless of distance or connections.
*   Once used, the super jump cannot be used again in the same match.

### End of Game
The game ends when the Carcará moves to the same space occupied by the Cabrito. At the end, a message is displayed informing of the capture and the total number of moves made in the match.

## Interface Features

The game has a graphical interface with the following menu options:

*   **Game**
    *   `Restart`: Starts a new game, restoring the pieces to their initial positions.
    *   `Exit`: Closes the application.
*   **Authorship**
    *   `View Names`: Displays the names of the game developers.

## How to Run the Project

To compile and run the project, follow the steps below:

1.  **Prerequisites**:
    *   Java Development Kit (JDK) installed.

2.  **Compilation**:
    Open a terminal in the project's root folder and compile the source files:
    ```bash
    javac -d bin src/main/java/com/example/*.java
    ```

3.  **Execution**:
    After compilation, run the main class of the game:
    ```bash
    java -cp bin com.example.Main
    ```

Authors:  **Sidnei Correia Junior** - Graphic design and half logic  and
         **Adriano Moisés** Half logic
         
---
*This project was developed for academic purposes.*

