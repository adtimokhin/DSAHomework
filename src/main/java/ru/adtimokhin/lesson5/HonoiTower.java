package ru.adtimokhin.lesson5;

import java.util.ArrayList;
import java.util.List;

/**
 * Class HonoiTower. Created by adtimokhin. 08.09.2018 (22:15)
 **/
public class HonoiTower {
    private final int SIZE;
    private int[] rod1;
    private int[] rod2;
    private int[] rod3;
    private int[] currentRod;
    private int[] moveRod1;
    private int[] moveRod2;
    private boolean odd = false;
    private List<int[]> rods = new ArrayList<>();

    public HonoiTower(int size) {
        SIZE = size;
        rod1 = new int[SIZE];
        rod2 = new int[SIZE];
        rod3 = new int[SIZE];
        initRods();
        rods.add(rod1);
        rods.add(rod2);
        rods.add(rod3);
        printRods();
        // как я пытался решить данную задачу. Этот цикл работал только до 4-ого элемента, а потом он переставал нести практическую пользу.
//        move(1);
        if(SIZE%2 ==1)odd = true;
        if(!odd)
        CorrectMove(0,1);
        else CorrectMove(0,2);
    }

    private void CorrectMove(int rod1, int rod2) {
        if(complete()){
            System.out.println("complete!");
            return;
        }
        int way1 = getTop(rods.get(rod1)), way2 = getTop(rods.get(rod2));
        if(way1 == 0){
            basicMove(rods.get(rod1), way2, rods.get(rod2));
        }else if(way2 ==0){
            basicMove(rods.get(rod2), way1, rods.get(rod1));
        }else {
            if(way1 < way2) basicMove(rods.get(rod2), way1, rods.get(rod1));
            else basicMove(rods.get(rod1), way2, rods.get(rod2));
        }
            if(odd){
                if(rod1 == 0 && rod2 ==2)CorrectMove(0,1);
                else if(rod1 == 0 && rod2 == 1)CorrectMove(1,2);
                else CorrectMove(0,2);
            }else{
                if(rod1 == 0 && rod2 ==1)CorrectMove(0,2);
                else if(rod1 == 0 && rod2 == 2)CorrectMove(1,2);
                else CorrectMove(0,1);
            }

    }

    private void move(int n) {
        if (complete()) {
            System.out.println("complete!");
            return;
        }
        if (n == SIZE + 1) move(1);
        else {
            setAuxiliaryRod(n);
            if (currentRod != null) {
                if (getTop(moveRod1) == 0 || getTop(moveRod1) > n) {
                    if (getTop(moveRod2) == 0 || getTop(moveRod2) > n) {
                        checkNextStep(n, n + 1, moveRod1, moveRod2, currentRod);
                    } else {
                  /*  moveOnTopOfRod(moveRod1, n);
                    deleteFromRod(currentRod);*/
                        basicMove(moveRod1, n, currentRod);

                    }
                } else if (getTop(moveRod2) == 0 || getTop(moveRod2) > n) {
//                moveOnTopOfRod(moveRod2, n);
//                deleteFromRod(currentRod);
                    basicMove(moveRod2, n, currentRod);
                }
                resetAuxiliaryRod();
            }
            move(n + 1);
            return;
        }
    }

    private boolean complete() {
        boolean needToCheck = false;
        for (int i = 0; i < SIZE; i++) {
            if (rod2[i] != i + 1) {
                needToCheck = true;
            }
        }
        if (!needToCheck)
            return true;
        for (int i = 0; i < SIZE; i++) {
            if (rod3[i] != i + 1) {
                return false;
            }
        }
        return true;

    }

    private void resetAuxiliaryRod() {
        currentRod = null;
        moveRod1 = null;
        moveRod2 = null;
    }

    private void setAuxiliaryRod(int n) {
        if (getTop(rod1) == n) {
            currentRod = rod1;
            moveRod1 = rod2;
            moveRod2 = rod3;
        } else if (getTop(rod2) == n) {
            currentRod = rod2;
            moveRod1 = rod1;
            moveRod2 = rod3;
        } else if (getTop(rod3) == n) {
            currentRod = rod3;
            moveRod1 = rod2;
            moveRod2 = rod1;
        } else resetAuxiliaryRod();
    }

    private void checkNextStep(int current, int next, int[] rod1, int[] rod2, int[] currentRod) {
        int choice1 = getTop(rod1), choice2 = getTop(rod2);
//        System.out.println("//"+current+" "+choice1+" "+choice2);
        boolean isNextInRod = false;
        for (int i = 0; i < SIZE; i++) {
            if (currentRod[i] == next && this.currentRod[i - 1] == current) {
                isNextInRod = true;
                break;
            }
        }
        if (choice1 == 0 && choice2 == 0) {
            basicMove(rod1, current, currentRod);
            return;
        }

        if (isNextInRod) {
            if (choice1 == next + 1) {
                basicMove(rod2, current, currentRod);
                return;
            } else if (choice2 == next + 1) {
                basicMove(rod1, current, currentRod);
                return;
            }
        }
            if (choice1 != 0 && choice2 != 0) {
                if (choice1 - current < choice2 - current) {
                    basicMove(rod1, current, currentRod);
                } else {

                    basicMove(rod2, current, currentRod);
                }
            } /*else if(choice1 == 0) {
                if(choice2 == current+1)basicMove(rod2, current, currentRod);
                else basicMove(rod1, current,currentRod);
            }else {
                if(choice1 == current+1)basicMove(rod1, current, currentRod);
                else basicMove(rod2, current,currentRod);
            }*/else if(choice1==0){
                basicMove(rod2, current, currentRod);
            }else {
                basicMove(rod1, current, currentRod);
            }

    }


    private void basicMove(int[] moveRod, int current, int[] currentRod) {
        moveOnTopOfRod(moveRod, current);
        deleteFromRod(currentRod);
        printRods();
    }


    private void deleteFromRod(int[] rod) {
        for (int i = 0; i < SIZE; i++) {
            if (rod[i] != 0) {
                rod[i] = 0;
                return;
            }
        }
    }

    private void moveOnTopOfRod(int[] rod, int n) {
        for (int i = SIZE; i > 0; i--) {
            if (rod[i - 1] == 0) {
                rod[i - 1] = n;
                return;
            }
        }
    }

    private void printRods() {
        for (int i = 0; i < SIZE; i++) {
            System.out.printf("%2d %2d %2d\n", rod1[i], rod2[i], rod3[i]);
        }
        System.out.println();
    }

    private void initRods() {
        for (int i = 0; i < SIZE; i++) {
            rod1[i] = i + 1;
        }
    }

    private int getTop(int[] rod) {
        for (int i = 0; i < rod.length; i++) {
            if (rod[i] != 0) return rod[i];
        }
        return 0;

    }

}
