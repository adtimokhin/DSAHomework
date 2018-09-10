package ru.adtimokhin.lesson5;

/**
 * Class HorseWalk. Created by adtimokhin. 08.09.2018 (09:19)
 **/
public class HorseWalk {
    private final int X;
    private final int Y;
    private int[][] map;


    public HorseWalk(int x, int y) {
        X = x;
        Y = y;
        map = new int[X][Y];
        initMap();
        printMap();
        if (fill(1)) {
            printMap();
        } else {
            System.out.println(false);
        }

    }

    private void initMap() {
        for (int i = 0; i < X; i++) {
            for (int j = 0; j < Y; j++) {
                map[i][j] = 0;
            }
        }
    }
    private void printMap(){
        for (int i = 0; i < X; i++) {
            for (int j = 0; j < Y; j++) {
                System.out.printf("%3d",map[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }



    private boolean fill(int i) {
        if (i == X * Y + 1) return true;
        if (i == 1) {
            for (int j = 0; j < X; j++) {
                for (int k = 0; k < Y; k++) {
                    if (map[j][k] == 0)
                        map[j][k] = i;
                    if (fill(i + 1)) return true;
                    else
                    map[j][k] = 0;
                }
            }
        } else {
            for (int j = 0; j < X; j++) {
                for (int k = 0; k < Y; k++) {
                    if (inField(i - 1, j, k)) {
                        map[j][k] = i;
                        if(fill(i+1)) return true;
                        map[j][k] =0;
                    }
                }

            }
        }
        return false;
    }

    private boolean inField(int i, int currentX, int currentY) {
        int x = -1, y = -1;
        for (int j = 0; j < X; j++) {
            for (int k = 0; k < Y; k++) {
                if (map[j][k] == i) {
                    x = j;
                    y = k;
                }
            }
        }
        if(map[currentX][currentY] != 0)return false;
        if (x == -1 && y == -1) return false;
        if (currentX - x == -2 || currentX - x == 2)
            if (currentY - y == -1 || currentY - y == 1) {return true;}
        if (currentY - y ==-2||currentY-y==2)
            if (currentX - x == -1 || currentX - x == 1){return true;}
            return false;
    }

}
