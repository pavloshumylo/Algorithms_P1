package Week1;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final boolean[][] siteState;
    private final WeightedQuickUnionUF weightedQuickUnionUF;
    private int top = 0;
    private int bottom;
    private int n;
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        siteState = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++){
                siteState[i][j] = false;
            }
        }
        weightedQuickUnionUF = new WeightedQuickUnionUF(n*n+2);
        this.n = n;
        bottom = n * n + 1;
    }
    private void checkIsCorrect (int row, int col) {
        if (row > siteState.length  || row <= 0 || col > siteState.length || col <= 0) {
            throw new IllegalArgumentException();
        }
    }
    public void open (int row, int col) {
        checkIsCorrect(row, col);
        siteState[row - 1][col - 1] = true;
        int calculatedFirstIndex = calculateIndex(row, col);
        if (row == 1) {
            weightedQuickUnionUF.union(calculatedFirstIndex, top);
        }
        if (row == n) {
            weightedQuickUnionUF.union(calculatedFirstIndex, bottom);
        }
        if(row > 1 && isOpen(row-1,col)){
            weightedQuickUnionUF.union(calculatedFirstIndex, calculateIndex(row-1, col));
        }
        if(row < n && isOpen(row+1,col)){
            weightedQuickUnionUF.union(calculatedFirstIndex, calculateIndex(row+1,col));
        }
        if(col < n && isOpen(row,col+1)){
            weightedQuickUnionUF.union(calculatedFirstIndex, calculateIndex(row, col+1));
        }
        if(col > 1 && isOpen(row,col-1)){
            weightedQuickUnionUF.union(calculatedFirstIndex, calculateIndex(row, col-1));
        }
    }

    public boolean isOpen (int row, int col){
        checkIsCorrect(row, col);
        return siteState[row-1][col-1];
    }

    public boolean isFull (int row, int col){
        checkIsCorrect(row, col);
        return weightedQuickUnionUF.connected(top, calculateIndex(row , col));
    }

    public int numberOfOpenSites(){
        int numberOfOpen = 0;
        for(int i = 0; i < n; i++ ){
            for(int j = 0; j < n; j++) {
                if (siteState[i][j]) {
                    numberOfOpen++;
                }
            }
        }
        return numberOfOpen;
    }

    public boolean percolates() {
        return weightedQuickUnionUF.connected(top, bottom);
    }

    private int calculateIndex(int i, int j) {
        return n * (i - 1) + j;
    }
}