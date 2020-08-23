import java.util.HashMap;

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
    void bfs() {
        Surface solved = Surface.SOLVED;
        Surface s2 = solved.r();

        assertEquals(solved.bfs().keySet(), s2.bfs().keySet());
    }
}