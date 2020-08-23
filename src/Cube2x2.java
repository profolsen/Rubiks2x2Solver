import java.util.*;

/**
 * A program that solves a rubik's cube.
 *
 * @version 1.0
 * @author Paul Olsen
 */
public class Cube2x2 {

    //represents a solved state for the unsolved layer.
    private static final Surface SOLVED = new Surface(new Corner[] {
            new Corner(1, 0),
            new Corner(2, 0),
            new Corner(3, 0),
            new Corner(4, 0)
    });

    //Does a breadth first search of all possible states of the unsolved layer to find the one
    //solved state.
    private static HashMap<Surface, String> closure(Surface start) {
        LinkedList<Surface> queue = new LinkedList<Surface>();
        HashMap<Surface, String> answer = new HashMap<Surface, String>();
        queue.add(start);
        answer.put(start, "");
        while(!queue.isEmpty()) {
            Surface n = queue.removeFirst();
            if(! answer.containsKey(n.r())) {
                answer.put(n.r(), answer.get(n) + "r --> " + n.r() + "\n");
                queue.addLast(n.r());
            }
            if(! answer.containsKey(n.c())) {
                answer.put(n.c(), answer.get(n) + "c --> " + n.c() + "\n");
                queue.addLast(n.c());
            }
            /*if(! answer.containsKey(n.c2())) {
                answer.put(n.c2(), answer.get(n) + "c2");
                queue.addLast(n.c2());
            }*/
        }
        return answer;
    }

    //the main method.  run this if you need to solve a cube.
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Surface start = new Surface(new Corner[] {
                new Corner(scan.nextInt(), scan.nextInt()),
                new Corner(scan.nextInt(), scan.nextInt()),
                new Corner(scan.nextInt(), scan.nextInt()),
                new Corner(scan.nextInt(), scan.nextInt())
        });
        System.out.println(start);
        HashMap<Surface, String> reached = closure(start);
        System.out.println(reached.get(SOLVED));
        System.out.println(reached.size());
    }
}


//a class representing one corner of a rubik's cube.
class Corner {
    int target;  //the correct place for the corner.
    int orientation;  //the current orientation of the corner.

    //creates a new corner with the given target and orientation.
    public Corner(int target, int orientation) {
        this.target = target % 4 == 0 ? 4 : target % 4;
        this.orientation = orientation % 3;
    }

    //creates a corner that would result from rotating this corner clockwise.
    public Corner rotateClockwise() {
        Corner answer = new Corner(target, (orientation + 1) % 3);
        return answer;
    }

    //creates a corner that would result from rotating this corner counterclockwise.
    public Corner rotateCounterClockwise() {
        Corner answer = new Corner(target, (orientation + 2) % 3);
        return answer;
    }

    //creates a corner that would result from rotating the cube clockwise.
    public Corner shiftRight() {
        Corner answer = new Corner((target + 1) % 4, orientation);
        return answer;
    }

    //represents a corner as (<target>, <orientation>).
    public String toString() {
        return "(" + (target == 0 ? 4 : target) + ", " + orientation + ")";
    }

    //see java.lang.Object.equals...
    public boolean equals(Object other) {
        if(other instanceof Corner) {
            return target == ((Corner) other).target &&
                    orientation == ((Corner) other).orientation;
        }
        return false;
    }
}
//a class that represents one side of a rubik's cube.
class Surface {
    Corner[] corners;  //the four corners, stored in clockwise order starting at the top left.
                        //assuming the solved layer is face up.

    //creates a new Surface with a given state.
    public Surface(Corner[] corners) {
        this.corners = corners;
    }

    //computes the result of the r operation without altering this surface.
    public Surface r() {
        Corner[] result = new Corner[4];
        result[0] = corners[0].rotateClockwise();
        result[1] = corners[1];
        result[2] = corners[3];
        result[3] = corners[2].rotateCounterClockwise();
        return new Surface(result);
    }

    //computes the result of the c operation without altering this surface.
    public Surface c() {
        Corner[] result = new Corner[4];
        result[0] = corners[3].shiftRight();
        result[1] = corners[0].shiftRight();
        result[2] = corners[1].shiftRight();
        result[3] = corners[2].shiftRight();
        return new Surface(result);
    }

    //computes the result of the c2 (currently unused) operation without altering this surface.
    public Surface c2() {
        Corner[] result = new Corner[4];
        result[0] = corners[3];
        result[1] = corners[0];
        result[2] = corners[1];
        result[3] = corners[2];
        return new Surface(result);
    }

    //again, see java.lang.Object.equals.
    public boolean equals(Object other) {
        if(other instanceof Surface) {
            return  corners[0].equals(((Surface) other).corners[0]) &&
                    corners[1].equals(((Surface) other).corners[1]) &&
                    corners[2].equals(((Surface) other).corners[2]) &&
                    corners[3].equals(((Surface) other).corners[3]);
        }
        return false;
    }

    //see java.lang.Object.hashCode.
    public int hashCode() {
        int answer = 0;
        for(int i = 0; i < 4; i++) {
            answer += corners[i].target * 3 + corners[i].orientation;
            answer *= 12;
        }
        return answer;
    }

    //prints out the surface starting with the top left cube and going clockwise around
    // (assumes the cube is solved-layer-face-up)
    public String toString() {
        return Arrays.toString(corners);
    }
}
