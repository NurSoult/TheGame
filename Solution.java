package Project1_The_Game;


import java.util.*;


public class Solution {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String className = input.nextLine();

        // Checking the implementation of the Position class
        if (className.equals("Position")) {
            Position p1 = new Position(input.nextInt(), input.nextInt());
            System.out.println(p1);
            p1.setX(5);
            System.out.println(p1.getX());

            Position p2 = new Position(5, 10);
            System.out.println(p1.equals(p2));
        }

        // Checking the implementation of the Map class
        else if (className.equals("Map")) {
            try {
                Map map = new Map(input);
                map.print();
                int size = map.getSize();
                System.out.println(size);
                System.out.println(map.getValueAt(size / 2, size / 2));
            } catch (Exception e) {
            }
        }

        // Checking the Player interface and the MyPlayer class
        else if (className.equals("Player")) {
            Player player = new MyPlayer();
            System.out.println(Player.class.isInterface());
            System.out.println(player instanceof Player);
            System.out.println(player instanceof MyPlayer);
        }

        // Checking the InvalidMapException class
        else if (className.equals("Exception")) {
            try {
                throw new InvalidMapException("Some message");
            } catch (InvalidMapException e) {
                System.out.println(e.getMessage());
            }
        }

        // Checking the Game class and all of its components
        else if (className.equals("Game")) {

            // Initialize player, map, and the game
            Player player = new MyPlayer();
            Game game = null;

            try {
                game = new Game(new Map(input));
            } catch (InvalidMapException e) { // custom exception
                System.out.println(e.getMessage());
                System.exit(0);
            }

            game.addPlayer(player);

            // Make the player move based on the commands given in the input
            String moves = input.next();
            char move;
            for (int i = 0; i < moves.length(); i++) {
                move = moves.charAt(i);
                switch (move) {
                    case 'R':
                        player.moveRight();
                        break;
                    case 'L':
                        player.moveLeft();
                        break;
                    case 'U':
                        player.moveUp();
                        break;
                    case 'D':
                        player.moveDown();
                        break;
                }
            }

            // Determine the final position of the player after completing all the moves above
            Position playerPosition = player.getPosition();
            System.out.println("Player final position");
            System.out.println("Row: " + playerPosition.getY());
            System.out.println("Col: " + playerPosition.getX());
        }
    }
}

//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------

class Position {
    private int x, y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    public boolean equals(Position bas) {
        if ((x == ((Position) bas).getX()) && (y == ((Position) bas).getY()))
            return true;
        else
            return false;
    }
}

class Map {
    protected Position or;
    protected int jol, bag;
    private char[][] bf;

    public void print() {
        for (char[] vre : bf) {
            for (int j = 0; j < bag; j++)
                System.out.print(vre[j] + " ");
            System.out.println();
        }
    }

    public Map(Scanner lop) throws InvalidMapException {
        int raz;
        try {
            raz = Integer.parseInt(lop.nextLine());
        } catch (Exception exc) {
            throw new InvalidMapException("Map size can not be zero");
        }
        bf = new char[raz][raz];
        String[] log;
        try {
            for (int i = 0; i < raz; i++) {
                log = lop.nextLine().split(" ");
                for (int j = 0; j < raz; j++) {
                    bf[i][j] = log[j].charAt(0);
                    if (bf[i][j] == 'P')
                        or = new Position(j, i);
                }
            }
        } catch (Exception epic) {
            throw new InvalidMapException("Not enough map elements");
        }
        this.jol = raz;
        this.bag = raz;
    }

    public char getValueAt(int jol, int bag) {
        return bf[jol][bag];
    }

    public int getSize() {
        return jol;
    }
}

class Game {
    private Map mp;
    private Player oy;

    public Game(Map mp3) {
        this.mp = mp3;
    }

    public void addPlayer(Player oy) {
        this.oy = oy;
        oy.setMap(mp);
    }
}

interface Player {
    Position getPosition();

    void setMap(Map shu);

    void moveLeft();

    void moveUp();

    void moveRight();

    void moveDown();
}

class MyPlayer implements Player {
    private Position kara;
    private Map shu;
    private int row, col;

    public void setMap(Map shu) {
        this.shu = shu;
        kara = shu.or;
    }

    public Position getPosition() {
        return kara;
    }

    public void moveDown() {
        row = (kara.getY() + 1);
        if (row < shu.jol) {
            if (!(shu.getValueAt(row, kara.getX()) == '1'))
                kara.setY(row);
        }
    }

    public void moveLeft() {
        col = (kara.getX() - 1);
        if (col >= 0) {
            if (!(shu.getValueAt(kara.getY(), col) == '1'))
                kara.setX(col);
        }
    }

    public void moveUp() {
        row = (kara.getY() - 1);
        if (row >= 0) {
            if (!(shu.getValueAt(row, kara.getX()) == '1'))
                kara.setY(row);
        }
    }

    public void moveRight() {
        col = (kara.getX() + 1);
        if (col < shu.bag) {
            if (!(shu.getValueAt(kara.getY(), col) == '1'))
                kara.setX(col);
        }
    }
}

class InvalidMapException extends Exception {
    public InvalidMapException(String sob) {
        super(sob);
    }
}


