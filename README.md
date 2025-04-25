# Talabia Chess
![Alt Text](https://media2.giphy.com/media/v1.Y2lkPTc5MGI3NjExaDBzNGJ4MGY4cGFrM2pubHlqYTl3MnRpbjUzYW9nZzlyeXUybXAwZyZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/EQebCHpvHYERG1iHfW/giphy.gif)

Talabia Chess is a variant of chess played on a 7x6 board.

## Instructions
These instructions assume that the user already has Java installed on their respective operating system. Instructions are the same for Windows, Mac and Linux operating systems.

If you have JavaSDK installed, run the command `javac Game.java` to compile the files. Then, run `java Game` to run the game.
If you do not have JavaSDK installed, download TalabiaChessCompiled.zip and run `java Game` to run the game.

## Pieces
**Point** ⬆️

The Point piece can only move forward, 1 or 2 steps. If it reaches the end of the board, it turns around and starts heading back the other way. It cannot skip over other pieces.

**Hourglass** ⏳

The Hourglass piece moves in a 3x2 L shape in any orientation (kind of like the knight in standard chess.) This is the only piece that can skip over other pieces.

**Time** ✖️

The Time piece can only move diagonally but can go any distance. It cannot skip over other pieces.

**Plus** ➕

The Plus piece can move horizontally and vertically only but can go any distance. It cannot skip over other pieces.

**Sun** ☀️

The Sun piece can move only one step in any direction. The game ends when the Sun is captured by the other side.

## Other rules
After 2 turns (counting one yellow move and one blue move as one turn), all Time pieces will turn into Plus pieces, and all Plus pieces will turn into Time pieces.
