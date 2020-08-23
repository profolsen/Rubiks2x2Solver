import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * A subset of the pieces of a rubik's cube
 *
 * @author Paul Olsen
 * @version 2
 */
public class Surface {
    Piece[] corners;  //the four corners
    Piece[] edges;    //the eight unsolved edges if top layer is solved.

    /** The state of a solved cube. */
    public static final Surface SOLVED = new Surface(
        new Piece[] {
                Piece.solved(0, 4, 3),
                Piece.solved(1, 4, 3),
                Piece.solved(2, 4, 3),
                Piece.solved(3, 4, 3),
        }, new Piece[] {
            Piece.solved(0, 8, 2),
            Piece.solved(1, 8, 2),
            Piece.solved(2, 8, 2),
            Piece.solved(3, 8, 2),
            Piece.solved(4, 8, 2),
            Piece.solved(5, 8, 2),
            Piece.solved(6, 8, 2),
            Piece.solved(7, 8, 2),
        }
    );

    /** Creates a new surface from 4 corner pieces and 8 edges pieces.
     * These pieces are 4 edges on the last layer and 4 side edges.
     * @param corners the four unsolved corners of a cube.
     * @param edges the 8 unsolved edges of a cube.
     */
    public Surface(Piece[] corners, Piece[] edges) {
        this.corners = corners;
        this.edges = edges;
    }

    /**
     * Returns the surface under the r operation (see readme file).
     * @return the surface under the r operation (see readme file).
     */
    public Surface r() {
        Piece[] resultC = new Piece[4];
        resultC[0] = corners[0].clockwiseRotation();
        resultC[1] = corners[1];
        resultC[2] = corners[3];
        resultC[3] = corners[2].counterClockwiseRotation();

        Piece[] resultE = new Piece[8];
        resultE[0] = edges[0];  //tl corner
        resultE[1] = edges[4].clockwiseRotation();
        resultE[2] = edges[2];
        resultE[3] = edges[3];
        resultE[4] = edges[7];
        resultE[5] = edges[1];
        resultE[6] = edges[6];
        resultE[7] = edges[5].clockwiseRotation();
        return new Surface(resultC, resultE);
    }

    /**
     * Returns the surface under a clockwise rotation of the cube.
     * @return the surface under a clockwise rotation of the cube.
     */
    public Surface c() {
        Piece[] resultC = new Piece[4];
        resultC[0] = corners[3];
        resultC[1] = corners[0];
        resultC[2] = corners[1];
        resultC[3] = corners[2];

        Piece[] resultE = new Piece[8];
        resultE[0] = edges[6];
        resultE[1] = edges[7];
        resultE[2] = edges[0];
        resultE[3] = edges[1];
        resultE[4] = edges[2];
        resultE[5] = edges[3];
        resultE[6] = edges[4];
        resultE[7] = edges[5];
        return new Surface(resultC, resultE);
    }

    public HashMap<Surface, String> bfs() {
        LinkedList<Surface> queue = new LinkedList<Surface>();
        HashMap<Surface, String> answer = new HashMap<Surface, String>();
        queue.add(this);
        answer.put(this, "");
        while(!queue.isEmpty()) {
            Surface n = queue.removeFirst();
            if(! answer.containsKey(n.r())) {
                answer.put(n.r(), answer.get(n) + "r");
                queue.addLast(n.r());
            }
            if(! answer.containsKey(n.c())) {
                answer.put(n.c(), answer.get(n) + "c");
                queue.addLast(n.c());
            }
            /*if(! answer.containsKey(n.c2())) {
                answer.put(n.c2(), answer.get(n) + "c2");
                queue.addLast(n.c2());
            }*/
        }
        return answer;
    }

    @Override
    public String toString() {
        return Arrays.toString(corners) + "." + Arrays.toString(edges);
    }

    @Override
    public int hashCode() {
        int cornersHash = 0;
        int edgesHash = 0;

        for(int i = 0; i < 4; i++) {
            cornersHash += corners[i].hashCode();
            cornersHash *= 12;
        }
        for(int i = 0; i < 8; i++) {
            edgesHash += edges[i].hashCode();
            edgesHash *= 8;
        }
        return cornersHash + edgesHash * 24*3*3*3*3;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Surface) {
            return corners[0].equals(((Surface) o).corners[0]) &&
                    corners[1].equals(((Surface) o).corners[1]) &&
                    corners[2].equals(((Surface) o).corners[2]) &&
                    corners[3].equals(((Surface) o).corners[3]) &&
                    edges[0].equals(((Surface) o).edges[0]) &&
                    edges[1].equals(((Surface) o).edges[1]) &&
                    edges[2].equals(((Surface) o).edges[2]) &&
                    edges[3].equals(((Surface) o).edges[3]) &&
                    edges[4].equals(((Surface) o).edges[4]) &&
                    edges[5].equals(((Surface) o).edges[5]) &&
                    edges[6].equals(((Surface) o).edges[6]) &&
                    edges[7].equals(((Surface) o).edges[7]);
        }
        return false;
    }
}
