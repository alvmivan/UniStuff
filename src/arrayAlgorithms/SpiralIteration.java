package arrayAlgorithms;

public class SpiralIteration {

    private static final int[] DIRX = {0, 1, 0, -1};
    private static final int[] DIRY = {1, 0, -1, 0};
    private static final int RIGHT = 0;
    private static final int DOWN = 1;
    private static final int LEFT = 2;
    private static final int UP = 3;


    public static <T> void run(T[][] array) {
        if (array.length == 0) return;

        int sizeX = array.length;
        int sizeY = array[0].length;

        int x = (sizeX - 1) / 2;
        int y = (sizeY - 1) / 2;

        int totalElements = sizeX * sizeY;
        int dir = RIGHT;
        boolean increaseDistanceNow = false;

        int directionDistance = 1;
        int directionDistanceConsumer = directionDistance;

        for (int i = 0; i < totalElements; i++) {

            System.out.print(array[x][y]);

            x += DIRX[dir];
            y += DIRY[dir];
            directionDistanceConsumer--;

            if (directionDistanceConsumer == 0 || x < 0 || y < 0 || x >= sizeX || y >= sizeY) {
                if (increaseDistanceNow) {
                    directionDistance++;
                }
                directionDistanceConsumer = directionDistance;
                dir = (dir + 1) % 4;
                increaseDistanceNow = !increaseDistanceNow;
            }
        }
    }

    private static void singleTest(String[][] matrix, String desiredResult) {
        System.out.println("Should Print");
        System.out.println(desiredResult);
        System.out.println("It Prints");
        run(matrix);
        System.out.println("");
        System.out.println("--------------");
    }

    public static void test() {
        singleTest(new String[][]{
                {"A", "B", "C", "D", "E"},
                {"F", "G", "H", "I", "J"},
                {"K", "L", "M", "N", "O"},
                {"P", "Q", "R", "S", "T"},
        }, "HINMLGBCDEJOTSRQPKFA");
        singleTest(new String[][]{
                {"A", "B"},
                {"F", "G"},
        }, "ABGF");

        singleTest(new String[][]{
                {"A"},
        }, "A");


    }


}
