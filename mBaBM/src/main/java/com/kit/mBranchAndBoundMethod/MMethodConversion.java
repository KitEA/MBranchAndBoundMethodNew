package com.kit.mBranchAndBoundMethod;

//import org.apache.commons.lang3.math.Fraction;

import org.apache.commons.math3.fraction.Fraction;

import javax.swing.*;
import java.util.List;


public class MMethodConversion {

    private int[][] xMas;
    private int[] xMasResCon;
    private int[] mMas;
    private int minCon;
    private int[][] masNewBasisCoordinates;
    int newBasisCoordinates = 0;
    int minBasisString = 0;
    int minBasisStringNumber = 0;
    boolean stillNegative = true;

    //private Fraction

    public void convertToM(List<JSpinner> list, int conditionNumberN, int variableNumberN) {
        xMas = new int[conditionNumberN + 1][variableNumberN + conditionNumberN + 1];
        int[][] masCoordinates = new int[conditionNumberN][2];
        int countBasisProof = 0;
        int neededBasis = 3;
        int countThem = 0;
        boolean itIsBasisAlready = false;
        boolean alreadyCounted = false;

        for (int i = 0; i < conditionNumberN + 1; i++) {
            for (int j = 0; j <= variableNumberN; j++) {
                if (j % 4 == 0 && j != 0) {
                    xMas[i][j + conditionNumberN] = (Integer) list.get(countThem).getValue();
                } else {
                    xMas[i][j] = (Integer) list.get(countThem).getValue();
                }
                countThem++;
            }
        }

        for (int i = 0; i < variableNumberN; i++) {
            for (int j = 0; j < conditionNumberN; j++) {
                if (xMas[j][i] == 0) {
                    countBasisProof++;
                } else if (xMas[j][i] == 1 && !alreadyCounted) {
                    countBasisProof++;
                    masCoordinates[j][0] = j;
                    masCoordinates[j][1] = i;
                    alreadyCounted = true;
                }
            }
            if (countBasisProof == conditionNumberN) {
                neededBasis--;
            }
            alreadyCounted = false;
            countBasisProof = 0;
        }

        int c = 0;

        for (int i = 0; i < conditionNumberN && neededBasis > 0; i++) {
            for (int j = variableNumberN + c; j < variableNumberN + conditionNumberN - neededBasis; j++) {
                if (masCoordinates[i][0] == 0 && !itIsBasisAlready) {
                    xMas[i][j] = 1;
                    itIsBasisAlready = true;
                    c++;
                    neededBasis--;
                }
            }
            itIsBasisAlready = false;
        }

        xMasResCon = new int[variableNumberN + conditionNumberN + 1];
        mMas = new int[variableNumberN + conditionNumberN + 1];

        for (int i = 0; i < conditionNumberN; i++) {
            if (masCoordinates[i][0] == 0) {
                mMas[variableNumberN + conditionNumberN] += xMas[i][conditionNumberN + variableNumberN];
            }
        }

        for (int j = 0; j < variableNumberN; j++) {
            xMasResCon[j] = xMas[conditionNumberN][j] * (-1);
        }

        for (int i = 0; i < variableNumberN; i++) {
            for (int j = 0; j < conditionNumberN; j++) {
                if (masCoordinates[j][0] == 0) {
                    mMas[i] += xMas[j][i];
                }
            }
        }
    }

    public void calculateM(int xMas[][], int xMasResCon[], int mMas[] , int conditionNumberN, int variableNumberN) {
        masNewBasisCoordinates = new int[conditionNumberN][2];

        do {
            stillNegative = false;
            Fraction fractionMinBasisStringNumber;

            for (int j = 0; j < variableNumberN + conditionNumberN; j++) {
                if (xMasResCon[newBasisCoordinates] > xMasResCon[j]) {
                    minCon = xMasResCon[j];
                    newBasisCoordinates = j;
                    //masNewBasisCoordinates[0][j] = j;
                }
            }

            for (int i = 0; i < conditionNumberN; i++) {
                if (i == 0) {
                    fractionMinBasisStringNumber = new Fraction(xMas[i][variableNumberN + conditionNumberN], xMas[i][newBasisCoordinates]);
                    //minBasisStringNumber = xMas[i][variableNumberN + conditionNumberN] / xMas[i][newBasisCoordinates];
                    minBasisString = i;
                }
                if (minBasisStringNumber > (xMas[i][variableNumberN + conditionNumberN] / xMas[i][newBasisCoordinates])) {
                    minBasisStringNumber = xMas[i][variableNumberN + conditionNumberN] / xMas[i][newBasisCoordinates];
                    minBasisString = i;
                }
            }

            for (int i = 0; i <= variableNumberN + conditionNumberN; i++) {
                if (i != newBasisCoordinates) {
                    xMas[minBasisString][i] = xMas[minBasisString][i] / xMas[minBasisString][newBasisCoordinates];
                }
            }

            for (int i = 0; i < conditionNumberN; i++) {
                for (int j = newBasisCoordinates; j <= newBasisCoordinates; j++) {
                    if (i == minBasisString && j == newBasisCoordinates) {
                        xMas[i][j] = 1;
                    } else {
                        xMas[i][j] = 0;
                    }
                }
            }

            for (int i = 0; i < conditionNumberN; i++) {
                for (int j = 0; j < variableNumberN + conditionNumberN; j++) {
                    if (i != minBasisString && j != newBasisCoordinates) {
                        xMas[i][j] = (xMas[minBasisString][newBasisCoordinates] * xMas[i][j] - xMas[minBasisString][j] * xMas[i][newBasisCoordinates]) / xMas[minBasisString][newBasisCoordinates];
                    }
                }
            }

            for (int i = 0; i < conditionNumberN; i++) {
                for (int j = 0; j < variableNumberN + conditionNumberN; j++) {
                    if (i != minBasisString && j != newBasisCoordinates) {
                        xMasResCon[j] = (xMas[minBasisString][newBasisCoordinates] * xMasResCon[j] - xMas[minBasisString][j] * xMasResCon[newBasisCoordinates]) / xMas[minBasisString][newBasisCoordinates];
                        mMas[j] = (xMas[minBasisString][newBasisCoordinates] * mMas[j] - xMas[minBasisString][j] * mMas[newBasisCoordinates]) / xMas[minBasisString][newBasisCoordinates];
                    }
                }
            }
            for (int i = 0; i < variableNumberN + conditionNumberN; i++) {
                if (xMasResCon[i] < 0 && mMas[i] != 0) {
                    stillNegative = true;
                }
            }
        } while (stillNegative);
    }

    public int[][] getXMas() {
        return xMas;
    }

    public int[] getXMasResCon() {
        return xMasResCon;
    }

    public int[] getMMas() {
        return mMas;
    }
}
