
package tictactoe_minimax;

import java.util.ArrayList;


public class TicTacToe_MiniMax {

    final static int RED_CODE_HUMAN = 1;
    final static int YELLOW_CODE_AI = 2;
    final static int NOT_PLAYED = 0;

    final static int SCORE_MAX = 1000;
    final static int SCORE_MIN = -1000;

    final static int SCORE_WIN = 10;
    final static int SCORE_LOOSE = -10;
    final static int SCORE_DRAW = 0;

    final static int BOARD_SIZE = 3;


    public static Boolean isMovesLeft(int[][] board) {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                if (board[row][col] == NOT_PLAYED) {
                    return true;
                }
            }
        }
        return false;
    }

    public static int evaluate_board(int[][] board) {

        int[] wining_state = {0,1,2,  3,4,5,  6,7,8, 
                              0,3,6,  1,4,7,  2,5,8, 
                              0,4,8,  2,4,6};
        //0 3 6 9 12 15 18 21
        for (int i = 0 ; i < 24 ; i+= 3){
            int i1 = wining_state[i];   //0 3   6   0   1   2   0   2
            int i2 = wining_state[i+1]; //1 4   7   3   4   5   4   4
            int i3 = wining_state[i+2]; //2 5   8   6   7   8   8   6
            if ((board[i1/3][i1%3]==YELLOW_CODE_AI) && 
                    (board[i2/3][i2%3]==YELLOW_CODE_AI) && 
                    (board[i3/3][i3%3]==YELLOW_CODE_AI))
            {
                return SCORE_WIN;
            }
            if ((board[i1/3][i1%3]==RED_CODE_HUMAN) && 
                    (board[i2/3][i2%3]==RED_CODE_HUMAN) && 
                    (board[i3/3][i3%3]==RED_CODE_HUMAN))
            {
                return SCORE_LOOSE;
            }
        }
        return SCORE_DRAW;
    }

    public static int minimax(int[][] board, int depth, Boolean isMax) {

        int score = evaluate_board(board);

        if (score == SCORE_WIN) {
            return score - depth;
        }
        if (score == SCORE_LOOSE) {
            return score + depth;
        }
        if (!isMovesLeft(board)) {
            if (score == SCORE_DRAW)
                return score;
        }


        if (isMax) {
            int best = SCORE_MIN;
            for (int row = 0; row < BOARD_SIZE; row++) {
                for (int col = 0; col < BOARD_SIZE; col++) {
                    if (board[row][col] == NOT_PLAYED) {
                        board[row][col] = YELLOW_CODE_AI;

                        int val = minimax(board, depth + 1, false);
                        best = Math.max(best, val);
                        board[row][col] = NOT_PLAYED;
                    }
                }
            }
            return best;
        } else {
            int best = SCORE_MAX;
            for (int row = 0; row < BOARD_SIZE; row++) {
                for (int col = 0; col < BOARD_SIZE; col++) {
                    if (board[row][col] == NOT_PLAYED) {
                        board[row][col] = RED_CODE_HUMAN;

                        int val = minimax(board, depth + 1, true);
                        best = Math.min(best, val);
                        board[row][col] = NOT_PLAYED;
                    }
                }
            }
            return best;
        }
    }

    public static int[] findBestMove(int[][] board) {
        int bestMove_row = -1;
        int bestMove_col = -1;
        int bestMove_val = SCORE_MIN;

        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                if (board[row][col] == NOT_PLAYED) {
                    board[row][col] = YELLOW_CODE_AI;

                    int moveVal = minimax(board, 0, false);

                    board[row][col] = NOT_PLAYED;

                    if (moveVal > bestMove_val) {
                        bestMove_row = row;
                        bestMove_col = col;
                        bestMove_val = moveVal;
                    }
                }
            }
        }

        return new int[]{bestMove_row, bestMove_col, bestMove_val };
    }

    public static void print_board(int board[][]) {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                System.out.printf("%s ", (board[row][col] == 0 ? "_" : board[row][col]));
            }
            System.out.printf("\n");
        }
        System.out.println("");
    }
    
    public static void main(String[] args) {
        int board[][]
                = {
                    {2, 0, 1},
                    {0, 1, 0},
                    {0, 0, 0}
                };

        
        print_board(board);
        int[] bestMove = findBestMove(board);
        
        String m = " -->> ROW: " + bestMove[0] + 
                "  **  COL: " + bestMove[1];
        System.out.println(m);
    }
}
