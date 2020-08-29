/**
 * A piece on a Rubik's cube.
 *
 * @author Paul Olsen
 * @Version 2
 */
public class Piece {

    /**
     * How a piece is oriented within a rubik's cube.
     */
    public static class Cycle {
        private int orientation;  //the orientation of the cube, or
                    // the number of counter-clockwise rotations
                    // it would take to put the piece in the correct orientation
        private int numOrientations;  //the number of orientations the piece could have..

        /**
         * Creates a new orientation.
         * @param orientation the orientation of the piece.
         * @param numOrientations the number of orientations the piece could have.
         */
        public Cycle(int orientation, int numOrientations) {
            this.orientation = orientation % numOrientations;
            this.numOrientations = numOrientations;
        }

        /**
         * Returns The resulting orientation after a clockwise rotation.
         * @return the resulting orientation after a clockwise rotation.
         */
        public Cycle clockwiseRotation() {
            return new Cycle(orientation + 1, numOrientations);
        }

        /**
         * Returns the resulting orientation after a counterclockwise rotation.
         * @return the resulting orientation after a counterclockwise rotation.
         */
        public Cycle counterClockwiseRotation() {
            return new Cycle(orientation + 1, numOrientations);
        }

        /**
         * Returns the orientation of the piece.
         * @return the orientation of the piece.
         */
        public int orientation()   {   return orientation;  }

        @Override
        public boolean equals(Object other) {
            if(other instanceof Cycle) {
                return orientation == ((Cycle) other).orientation &&
                        numOrientations == ((Cycle) other).numOrientations;
            }
            return false;
        }
    }

    protected Cycle target;  //the position of the piece.
    protected Cycle orientation;  //the orientation of the piece.

    /**
     * Creates a piece with the given target and orientation.
     * @param target where the piece should ultimately go
     * @param orientation how the piece is currently oriented.
     */
    public Piece(Cycle target, Cycle orientation) {
        this.target = target;
        this.orientation = orientation;
    }

    /**
     * Creates a piece with the given target, number of targets, orientation and number of orientations.
     * @param position the target position of this piece.
     * @param numPositions the number of targets total for pieces of this type.
     * @param orientation the orientation of this piece.
     * @param numOrientations the number of orientations this piece can be in.
     */
    public Piece(int position, int numPositions, int orientation, int numOrientations) {
        this(new Cycle(position, numPositions), new Cycle(orientation, numOrientations));
    }

    /**
     * Returns the piece under a clockwise rotation.
     * @return the piece under a clockwise rotation.
     */
    public Piece clockwiseRotation() {
        return new Piece(target, orientation.clockwiseRotation());
    }

    /**
     * Returns the piece under a counterclockwise rotation.
     * @return the piece under a counterclockwise rotation.
     */
    public Piece counterClockwiseRotation() {
        return new Piece(target, orientation.counterClockwiseRotation());
    }

    /**
     * Returns the piece under a clockwise rotation of the cube.
     * @return the piece under a clockwise rotation of the cube.
     */
    public Piece shiftRight() {
        return new Piece(target.clockwiseRotation(), orientation);
    }

    @Override
    public String toString() {
        return "(" + target.orientation + "/" + target.numOrientations +
                ", " + orientation.orientation + "/" + orientation.numOrientations + ")";
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Piece) {
            return target.equals(((Piece) o).target) &&
                    orientation.equals(((Piece) o).orientation);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return target.orientation * orientation.numOrientations + orientation.orientation;
    }
}
