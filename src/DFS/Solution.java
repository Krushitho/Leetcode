package DFS;

import java.util.Collections;

/*
Let's play the minesweeper game (Wikipedia, online game)!

You are given an m x n char matrix board representing the game board where:

'M' represents an unrevealed mine,
'E' represents an unrevealed empty square,
'B' represents a revealed blank square that has no adjacent mines (i.e., above, below, left, right, and all 4 diagonals),
digit ('1' to '8') represents how many mines are adjacent to this revealed square, and
'X' represents a revealed mine.
You are also given an integer array click where click = [clickr, clickc] represents the next click position among all the unrevealed squares ('M' or 'E').

Return the board after revealing this position according to the following rules:

If a mine 'M' is revealed, then the game is over. You should change it to 'X'.
If an empty square 'E' with no adjacent mines is revealed, then change it to a revealed blank 'B' and all of its adjacent unrevealed squares should be revealed recursively.
If an empty square 'E' with at least one adjacent mine is revealed, then change it to a digit ('1' to '8') representing the number of adjacent mines.
Return the board when no more squares will be revealed.


Example 1:


Input: board = [["E","E","E","E","E"],["E","E","M","E","E"],["E","E","E","E","E"],["E","E","E","E","E"]], click = [3,0]
Output: [["B","1","E","1","B"],["B","1","M","1","B"],["B","1","1","1","B"],["B","B","B","B","B"]]
Example 2:


Input: board = [["B","1","E","1","B"],["B","1","M","1","B"],["B","1","1","1","B"],["B","B","B","B","B"]], click = [1,2]
Output: [["B","1","E","1","B"],["B","1","X","1","B"],["B","1","1","1","B"],["B","B","B","B","B"]]


Constraints:

m == board.length
n == board[i].length
1 <= m, n <= 50
board[i][j] is either 'M', 'E', 'B', or a digit from '1' to '8'.
click.length == 2
0 <= clickr < m
0 <= clickc < n
board[clickr][clickc] is either 'M' or 'E'.
 */
class Minesweeper_529 {
 public char[][] updateBoard(char[][] board, int[] click) {
        int[][] directions = {{0,1},{1,0},{0,-1},{-1,0},{1,1},{-1,-1},{1,-1},{-1,1}};
        if(board[click[0]][click[1]]=='M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }
        fill(board,click[0],click[1],directions);
        return board;
    }
    private void fill(char[][] board, int i, int j,int[][] directions) {
        //need to update only 'E'
        if(i<0 || j<0 || i>=board.length || j>=board[0].length || board[i][j]!='E') {
            return;
        }
        int count = 0;
        for(int[] direction : directions) {
            int x = i+direction[0];
            int y = j+direction[1];
            if(x<0 || y<0 || x>=board.length || y>=board[0].length) continue;
            //count mines only
            if(board[x][y]=='M') {
                count = count+1;
            }
        }
        if(count==0) {
            board[i][j]='B';
            //update adjacent cells only if current cell is 'B'
            for(int[] direction : directions) {
                int x = i+direction[0];
                int y = j+direction[1];
                fill(board,x,y,directions);
            }
        }
        else {
            board[i][j] = (char)(count+'0');
        }
    }
}
