import java.util.*;

public class Cube2x2 {

    public static final Surface SOLVED = new Surface(new Corner[] {
            new Corner(1, 0),
            new Corner(2, 0),
            new Corner(3, 0),
            new Corner(4, 0)
    });

    private static class Corner {
        int target;
        int orientation;

        public Corner(int target, int orientation) {
            this.target = target % 4 == 0 ? 4 : target % 4;
            this.orientation = orientation % 3;
        }

        public Corner rotateRight() {
            Corner answer = new Corner(target, (orientation + 1) % 3);
            return answer;
        }

        public Corner rotateLeft() {
            Corner answer = new Corner(target, (orientation + 2) % 3);
            return answer;
        }

        public Corner shiftRight() {
            Corner answer = new Corner((target + 1) % 4, orientation);
            return answer;
        }

        public String toString() {
            return "(" + (target == 0 ? 4 : target) + ", " + orientation + ")";
        }

        public boolean equals(Object other) {
            if(other instanceof Corner) {
                return target == ((Corner) other).target &&
                        orientation == ((Corner) other).orientation;
            }
            return false;
        }
    }

    private static class Surface {
        Corner[] corners;
        public Surface(Corner[] corners) {
            this.corners = corners;
        }

        public Surface r() {
            Corner[] result = new Corner[4];
            result[0] = corners[0].rotateRight();
            result[1] = corners[1];
            result[2] = corners[3];
            result[3] = corners[2].rotateLeft();
            return new Surface(result);
        }

        public Surface c() {
            Corner[] result = new Corner[4];
            result[0] = corners[3].shiftRight();
            result[1] = corners[0].shiftRight();
            result[2] = corners[1].shiftRight();
            result[3] = corners[2].shiftRight();
            return new Surface(result);
        }

        public Surface c2() {
            Corner[] result = new Corner[4];
            result[0] = corners[3];
            result[1] = corners[0];
            result[2] = corners[1];
            result[3] = corners[2];
            return new Surface(result);
        }

        public boolean equals(Object other) {
            if(other instanceof Surface) {
                return  corners[0].equals(((Surface) other).corners[0]) &&
                        corners[1].equals(((Surface) other).corners[1]) &&
                        corners[2].equals(((Surface) other).corners[2]) &&
                        corners[3].equals(((Surface) other).corners[3]);
            }
            return false;
        }

        public int hashCode() {
            int answer = 0;
            for(int i = 0; i < 4; i++) {
                answer += corners[i].target * 3 + corners[i].orientation;
                answer *= 12;
            }
            return answer;
        }

        public String toString() {
            return Arrays.toString(corners);
        }
    }

    public static Surface read(Scanner scanner) {
        Corner[] c = new Corner[4];
        System.out.println("Enter as <target1> <orientation1> ... <target4> <orientation4>");
        for(int i = 0; i < 4; i++) {
            c[i] = new Corner(scanner.nextInt() % 4, scanner.nextInt() % 3);
        }
        return new Surface(c);
    }

    public static HashMap<Surface, String> closure(Surface start) {
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

    public static void main(String[] args) {
        System.out.println(SOLVED.equals(SOLVED));
        //System.out.println(SOLVED.hashCode());
        //System.out.println(SOLVED.equals(SOLVED.r().r().r().r().r().r()));
        Surface start = new Surface(new Corner[] {
                new Corner(1, 0),
                new Corner(3, 0),
                new Corner(2, 2),
                new Corner(4, 1),
        });
        //System.out.println(start.hashCode());
        System.out.println(start);
        HashMap<Surface, String> reached = closure(start);
        System.out.println(reached.get(SOLVED));
        System.out.println(reached.size());
    }
}
