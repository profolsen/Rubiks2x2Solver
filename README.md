# Rubiks2x2Solver

A program that tells you how to solve a 2x2x2 rubik's cube (assuming you can solve one layer yourself).

## Terminology:
All the following terms assume the solved layer is facing up.
+ r -- This operation is R'BRBFB'F' in a more familiar notation.  
+ c -- a clockwise rotation of the cube.
+ target -- the correct position for a corner piece in the unsolved layer: they are numbered clockwise from top-left (i.e., top-left = 1, top-right = 2, bottom-right = 3, bottom-left = 4).
+ orientation -- the orientation of a particular corner piece it can be one of three values: correct (= 0), clockwise rotated (= 1), counter-clockwise rotated (= 2).

## Input to the program:
The input is done via the command line. 
For each corner piece, enter in first its target and then its orientation, separating all values with whitespace.
After the final value, hit enter to run the program.
Follow the instructions and watch the cube get solved.
Be careful about your orientation during the solve.

## Considerations and Future Work
It is interesting to note that a 2x2x2 cube can be solved using only two operations, one of which is just rotating the cube.
I believe, that because the code uses a BFS to generate solution instructions, that it generates the shortest possible solution in terms of number of operations (r and c both counting a single operation).
Future work would consist of extending this work to also generate solutions for the unsolved layer of a 3x3x3 (solving the top two layers is easy).

