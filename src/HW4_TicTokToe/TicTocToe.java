package HW4_TicTokToe;


import java.util.Random;
import java.util.Scanner;

public class TicTocToe {// Создание двумерного символьного массива размером 3x3/
    // в каждом масссиве могут находиться символы: '0' 'X' '.'
    private static char[][] map;
    private static final int SIZE = 5;
    public static final int DOTS_TO_WIN = 4;
    // именные константы
    public static final char DOT_EMPTY = '•';
    public static final char DOT_X = 'X';
    public static final char DOT_O = 'O';

    public static Scanner sc = new Scanner(System.in);
    public static Random rand = new Random();

    public static void main(String[] args) {
        //Основной игровой цикл

        initMap();
        printMap();
        while (true) {
            humanTurn();
            printMap();

            if (checkEnd(DOT_X, "Пользователь победил!"))
                break;

            aiTurn();
            printMap();
            if (checkEnd(DOT_O, "Компьютер победил!"))
                break;
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }
        }
        System.out.println("Игра закончена");
    }

    // Инициализация поля
    public static void initMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }

    }

    // Вывод поля в консоль.
    public static void printMap() {
        for (int i = 0; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // Ход человека.


    public static void humanTurn() {
        int x, y;
        do {
            System.out.println("Введите координату по горизонтали");
            x = sc.nextInt() - 1;
            System.out.println("Введите координату по вертикале:");
            y = sc.nextInt() - 1;
        } while (!isCellValid(x, y));
        map[y][x] = DOT_X;
    }


    //isCellValid() проверяет возможность установки фишки в указанную ячейку.

    public static boolean isCellValid(int x, int y) {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) return false;
        if (map[y][x] == DOT_EMPTY) return true;
        return false;
    }

    //Ход компьютера
    public static void aiTurn() {
        int x, y;
        do {
            x = rand.nextInt(SIZE);
            y = rand.nextInt(SIZE);
        } while (!isCellValid(x, y));
        System.out.println("Компьютер походил в точку " + (x + 1) + " " + (y + 1));
        map[y][x] = DOT_O;
    }

    //Проверка победы.
    public static boolean checkWin(char symb) {
        for (int i = 0; i < SIZE; i++) {
            int rowCounter = 0;
            int colCounter = 0;
            for (int j = 0; j < SIZE; j++) {
                rowCounter = (map[i][j] == symb) ? rowCounter + 1 : 0;
                colCounter = (map[i][j] == symb) ? colCounter + 1 : 0;
                if (rowCounter == DOTS_TO_WIN || colCounter == DOTS_TO_WIN) {
                    return true;
                }
            }
        }
        int mainDiagCounter = 0;
        int sideDiagCounter = 0;
        for (int i = 0; i < SIZE ; i++) {
            mainDiagCounter = (map[i][i] == symb) ? mainDiagCounter + 1 :0;
            sideDiagCounter = (map[i][map.length-1-i] == symb) ? sideDiagCounter + 1:0;
            if (mainDiagCounter == DOTS_TO_WIN|| sideDiagCounter == DOTS_TO_WIN){
                return true;
            }

        }
        return false;
    }

    private static boolean checkEnd(char symb, String winMessage) {
        if (checkWin(symb)) {
            System.out.println(winMessage);
            return true;
        }
        if (isMapFull()) {
            System.out.println("Ничья!");
            return true;
        }
        return false;
    }





    // isMapFull
    public static boolean isMapFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) return false;
            }
        }
        return true;
    }

}








