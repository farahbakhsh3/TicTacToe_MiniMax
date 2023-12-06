/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
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
    static int[][] gameBoard = new int[BOARD_SIZE][BOARD_SIZE];


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
        
        for (int i = 0 ; i < 27 ; i+= 3){
            int i1 = wining_state[i];
            int i2 = wining_state[i+1];
            int i3 = wining_state[i+2];
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

//    public static int minimax(int[][] board, int depth, Boolean isMax, int alpha, int beta) {
//
//        int score = evaluateBoard(board);
//
//        if (score == SCORE_WIN) {
//            return score - depth;
//        }
//        if (score == SCORE_LOOSE) {
//            return score + depth;
//        }
//        if (!isMovesLeft(board)) {
//            if (score == DRAW)
//                return score;
//        }
//
//
//        if (isMax) {
//            int best = SCORE_MIN;
//
//            for (int row = 0; row < BOARD_SIZE; row++) {
//                for (int col = 0; col < BOARD_SIZE; col++) {
//                    if (board[row][col] == NOT_PLAYED && isNeighborN(board, row, col, N_NEIGHBOR_MAX)) {
//                        board[row][col] = YELLOW_CODE_AI;
//
//                        int val = minimax(board, depth + 1, false, alpha, beta);
//                        best = Math.max(best, val);
//                        alpha = Math.max(alpha, best);
//                        board[row][col] = NOT_PLAYED;
//
//                        if (beta <= alpha) {
//                            break;
//                        }
//                    }
//                }
//            }
//            return best;
//        } else {
//            int best = SCORE_MAX;
//
//            for (int row = 0; row < BOARD_SIZE; row++) {
//                for (int col = 0; col < BOARD_SIZE; col++) {
//                    if (board[row][col] == NOT_PLAYED && isNeighborN(board, row, col, N_NEIGHBOR_MAX)) {
//                        board[row][col] = RED_CODE_HUMAN;
//
//                        int val = minimax(board, depth + 1, true, alpha, beta);
//                        best = Math.min(best, val);
//                        beta = Math.min(beta, best);
//                        board[row][col] = NOT_PLAYED;
//
//                        if (beta <= alpha) {
//                            break;
//                        }
//                    }
//                }
//            }
//            return best;
//        }
//    }
//
//    public static Move findBestMove(int[][] board) {
//        Move bestMove = new Move();
//        bestMove.row = -1;
//        bestMove.col = -1;
//        bestMove.val = SCORE_MIN;
//
//        ArrayList<int[]> lst = new ArrayList<>();
//
//        for (int row = 0; row < BOARD_SIZE; row++) {
//            for (int col = 0; col < BOARD_SIZE; col++) {
//                DEPTH = CalcDepth(board);
//                if (board[row][col] == NOT_PLAYED && isNeighborN(board, row, col, N_NEIGHBOR_MAX)) {
//                    board[row][col] = YELLOW_CODE_AI;
//
//                    int moveVal = minimax(board, 0, false, SCORE_MIN, SCORE_MAX);
//
//                    board[row][col] = NOT_PLAYED;
//
//                    if (moveVal > bestMove.val) {
//                        bestMove.row = row;
//                        bestMove.col = col;
//                        bestMove.val = moveVal;
//                        int[] x = {bestMove.row, bestMove.col, bestMove.val};
//                        lst.clear();
//                        lst.add(x);
//                    } else if (moveVal == bestMove.val) {
//                        bestMove.row = row;
//                        bestMove.col = col;
//                        bestMove.val = moveVal;
//                        int[] x = {bestMove.row, bestMove.col, bestMove.val};
//                        lst.add(x);
//                    }
//
//                    String m = row + "," + col + ":" + (row * BOARD_SIZE + col) + "\t"
//                            + "\t depth: " + idxDepth + "\t Score: " + moveVal;
//                    System.out.println(m);
//                }
//            }
//        }
//
//        int r = rand.nextInt(lst.size());
//        bestMove.row = lst.get(r)[0];
//        bestMove.col = lst.get(r)[1];
//        bestMove.val = lst.get(r)[2];
//
//        String m = "ROW: " + bestMove.row + " COL: " + bestMove.col;
//        System.out.println(m);
//
//        return bestMove;
//    }
//
//    static void print_board(int board[][]) {
//        for (int row = 0; row < BOARD_SIZE; row++) {
//            for (int col = 0; col < BOARD_SIZE; col++) {
//                System.out.printf("%s ", (board[row][col] == 0 ? "_" : board[row][col]));
//            }
//            System.out.printf("\n");
//        }
////        System.out.printf("idxDepth:%d---s:%d\n", idxDepth, evaluateBoard(board, GOAL_SIZE, false));
//        System.out.println("");
//    }
   // Driver code
    
    public static void main(String[] args) {
        int board[][]
                = {
                    {2, 1, 1},
                    {2, 1, 1},
                    {2, 2, 0}
                };

        if (isMovesLeft(board))
            System.out.println("there is moves not played.");
        else
            System.out.println("there is no move.");

        System.out.println("Score: " + evaluate_board(board));
        
        
        
//        print_board(board);
//        Move bestMove = findBestMove(board);
//        for (int row = 0; row < BOARD_SIZE; row++) {
//            for (int col = 0; col < BOARD_SIZE; col++) {
//                if (isNPath(board, row, col)) {
//                    System.out.printf("row: %d \t col: %d \n",row, col);
//                }
//            }
//        }
//        System.out.printf("\n\nThe Optimal Move is :\n");
//        System.out.printf("ROW: %d COL: %d\n\n", bestMove.row, bestMove.col);
//        System.out.println("" + evaluate(board, GOAL_SIZE));
    }
}
