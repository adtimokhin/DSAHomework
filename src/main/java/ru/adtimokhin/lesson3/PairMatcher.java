package ru.adtimokhin.lesson3;

/**
 * Class PairMatcher. Created by Alexander Timokhin. 02.09.2018 (19:42)
 **/
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class PairMatcher {
    // constants
    private static final int ROUND_BRACKET_OPEN = 40; //'('
    private static final int ROUND_BRACKET_CLOSE = 41;//')'
    private static final int ANGLE_BRACKET_OPEN = 60;//'<'
    private static final int ANGLE_BRACKET_CLOSE = 62;//'>'
    private static final int FIGURE_BRACKET_OPEN = 123;//'{'
    private static final int FIGURE_BRACKET_CLOSE = 125;//'}'
    private static final int SQUARE_BRACKET_OPEN = 91;//'['
    private static final int SQUARE_BRACKET_CLOSE = 93;//']'
    private static int num=0;
    private int max=0;
    private enum TYPE{round, angle, figure, square}
    private Map<Integer, Integer> characterMap = new HashMap<Integer, Integer>();
    private Stack stack = new Stack(20);
    private DataInputStream dis;


    public PairMatcher(File file) throws IOException {
        num++;
        try {
            dis = new DataInputStream(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int val = dis.read();
        while(val !=-1){
            stack.push(val);
            val = dis.read();
        }
        setMax();
        check();
        checkForErrors();

    }

    private void setMax() {
        max = stack.getHead();
    }

    public PairMatcher(String text){
        num++;
        char[] chars = text.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            stack.push(chars[i]);
        }
        setMax();
        check();
        checkForErrors();

    }

    private void checkForErrors() {
        if(characterMap.size()>0){
            StringBuilder stringBuilder = new StringBuilder();
            for (Map.Entry<Integer, Integer> pair : characterMap.entrySet()) {
                stringBuilder.append((char)(int)pair.getValue()+":"+(this.max-pair.getKey())+"\n");
            }
            throw new RuntimeException("Осталось "+characterMap.size()+" скобок, не имеющих парных открывающих. Их типы и позиции :\n"+stringBuilder);
        }else System.out.println("Ошибок не выевлено");
    }

    private void check() {
        System.out.println("PairMatcher"+num);
        int size = stack.getHead()+1;
        for (int i = 0; i <size; i++) {
            int val = stack.pop();
            if( ROUND_BRACKET_CLOSE== val)
                characterMap.put(i , ROUND_BRACKET_CLOSE);
            else if(ANGLE_BRACKET_CLOSE ==val)
                characterMap.put(i , ANGLE_BRACKET_CLOSE);
            else if(FIGURE_BRACKET_CLOSE == val)
                characterMap.put(i , FIGURE_BRACKET_CLOSE);
            else if(SQUARE_BRACKET_CLOSE == val)
                characterMap.put(i , SQUARE_BRACKET_CLOSE);
            else if(ROUND_BRACKET_OPEN == val) {
                characterMap.put(i, ROUND_BRACKET_OPEN);
                match(TYPE.round,i);
            } else if(ANGLE_BRACKET_OPEN == val){
                characterMap.put(i, ANGLE_BRACKET_OPEN);
                match(TYPE.angle, i);
            } else if(FIGURE_BRACKET_OPEN == val){
                characterMap.put(i, FIGURE_BRACKET_OPEN);
                match(TYPE.figure,i);
            }else if(SQUARE_BRACKET_OPEN == val){
                characterMap.put(i, SQUARE_BRACKET_OPEN);
                match(TYPE.square,i);
            }
        }
    }


    private void match(TYPE type, int lastTaken) {
        int openingSymbol;
        switch (type){ // проверка на нужный символ.
            case round: openingSymbol = ROUND_BRACKET_CLOSE; break;
            case angle: openingSymbol = ANGLE_BRACKET_CLOSE; break;
            case figure: openingSymbol = FIGURE_BRACKET_CLOSE;break;
            case square: openingSymbol = SQUARE_BRACKET_CLOSE; break;
            default:throw new RuntimeException("Выбран тип скобок, не представленный в спске имеющихся");
        }int max = -1;
        for (Map.Entry<Integer,Integer> pair : characterMap.entrySet()) {
            int val = pair.getValue();
            int key = pair.getKey();
            if (val == openingSymbol && max < key && key < lastTaken) {
                max = key;
            }
        }
        if(max==-1) throw new RuntimeException("отсутствует отрывающая скобка типа : "+ type+" brackets"+"\n Парная закрывающая скобка находится на позиции номер : "+ (this.max-max));
        System.out.println("pair of : "+type+" brackets\n"+((char)(int)characterMap.get(lastTaken))+":"+(this.max-lastTaken)+"\n"+((char)(int)characterMap.get(max))+":"+(this.max-max));
        characterMap.remove(lastTaken);
        characterMap.remove(max);
    }
}
