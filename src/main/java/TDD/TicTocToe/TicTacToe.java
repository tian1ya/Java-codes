package TDD.TicTocToe;

public class TicTacToe {

    // '\0' 表示空
    private Character[][] board = {{'\0','\0','\0'},{'\0','\0','\0'},{'\0','\0','\0'}};
    private Character lastPlayer = '\0';

    private final static int SIZE = 3;

    public String play(int x, int y) {
        chackXAxis(x);

        checkYAxis(y);

        lastPlayer = nextPlayer();
        setBoard(x, y, lastPlayer);

        boolean winner = isWinner(x,y);
        if (winner) {
            return lastPlayer + " winner";
        }
        return "no winner";
    }

    private boolean isWinner(int x, int y) {

        char diagonal1 = 0,diagonal2 = 0, vertical = 0, horizatal = 0;
        for (int i = 0; i < SIZE; i++) {
             diagonal1 += board[i][i];
             diagonal2 += board[i][SIZE - i - 1];

            vertical += board[x-1][i];
            horizatal += board[i][y-1];

            if (diagonal1 == lastPlayer * SIZE
                    || diagonal2 == lastPlayer * SIZE
                    || vertical == lastPlayer * SIZE
                    || horizatal == lastPlayer * SIZE)
                return true;
        }
        return false;
    }

    private void setBoard(int x, int y, char lastPlayer) {
        if (board[x-1][y-1] != '\0')
            throw new RuntimeException(String.format("%s, %s position is occupied",x,y));
        else
            board[x-1][y-1]=lastPlayer;
    }

    private void checkYAxis(int y) {
        if (y < 1 || y > 3) {
            throw new RuntimeException("x is outside board");
        }
    }

    private void chackXAxis(int x) {
        if (x < 1 || x > 3) {
            throw new RuntimeException("x is outside board");
        }
    }

    public char nextPlayer() {
        if (lastPlayer == 'x'){
            return 'o';
        }
        return 'x';
    }
}