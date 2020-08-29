import java.util.HashMap;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class SurfaceTest {

    @org.junit.jupiter.api.Test
    void r() {
        Surface solved = Surface.SOLVED;
        Surface s = solved;
        for(int i = 0; i < 11; i++) {
            s = s.r();
            assertNotEquals(solved, s);
        }
        s = s.r();
        assertEquals(solved, s);
    }

    @org.junit.jupiter.api.Test
    void c() {
        Surface solved = Surface.SOLVED;
        Surface s = solved;
        for(int i = 0; i < 3; i++) {
            s = s.c();
            assertNotEquals(solved, s);
        }
        s = s.c();
        assertEquals(solved, s);
    }

    @org.junit.jupiter.api.Test
    void samePositions() {
        Surface solved = Surface.SOLVED;
        Surface r1 = solved.r();
        Surface rando = new Surface(
            new Piece[] {
                    new Piece(0, 4, 1, 3),
                    new Piece(1, 4, 2, 3),
                    new Piece(2, 4, 2, 3),
                    new Piece(3, 4, 0, 3),
            }, new Piece[] {
            new Piece(0, 8, 1, 2),
            new Piece(1, 8, 1, 2),
            new Piece(2, 8, 1, 2),
            new Piece(3, 8, 1, 2),
            new Piece(4, 8, 0, 2),
            new Piece(5, 8, 1, 2),
            new Piece(6, 8, 1, 2),
            new Piece(7, 8, 0, 2),
        });
        assertFalse(r1.samePositions(solved));
        assertFalse(solved.samePositions(r1));
        assertFalse(rando.samePositions(r1));
        assertFalse(r1.samePositions(rando));
        assertTrue(solved.samePositions(solved));
        assertTrue(rando.samePositions(rando));
        assertTrue(r1.samePositions(r1));
        assertTrue(rando.samePositions(solved));
        assertTrue(solved.samePositions(rando));
    }



    //this method
    //    (1) takes too much memory to run.
    //    (2) takes too long to run.
    @org.junit.jupiter.api.Test
    void bfs() {
        Surface solved = Surface.SOLVED;

        solved.bfs(3, true, false);
    }
}