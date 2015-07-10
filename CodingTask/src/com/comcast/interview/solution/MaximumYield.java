package com.comcast.interview.solution;

import java.util.Arrays;

public class MaximumYield {
	public static void main(String args[]) {
		int[] input = null, optimalSquares = null;
		
		input = new int[]{206, 140, 300, 52, 107};
		optimalSquares = findOptimalSquares(input);
		printOutput(input, optimalSquares);
		System.out.println();
		
		input = new int[]{147, 206, 52, 240, 300};
		optimalSquares = findOptimalSquares(input);
		printOutput(input, optimalSquares);
	}
	
	public static int[] findOptimalSquares(int[] squares) {
		int optimalSquares[] = new int[squares.length];
		Arrays.fill(optimalSquares, 0);
		optimalSquares[0] = 1;
		int lastIndex = 0;
		
		for(int i = 1; i < squares.length; i++) {
			if(lastIndex == i-1) {
				if(squares[i] > squares[i-1]) {
					lastIndex = i;
					optimalSquares[i] = 1;
					optimalSquares[i-1] = 0;
					if(i != 1) {
						int toptimalSquares[] = findOptimalSquares(Arrays.copyOf(squares, i-1));
						for(int j = 1; j < toptimalSquares.length; j++) {
							optimalSquares[j] = toptimalSquares[j];
						}
					}
				}
			} else {
				lastIndex = i;
				optimalSquares[i] = 1;
			}
		}
		
		return optimalSquares;
	}
	
	public static void printOutput(int inputArray[], int[] optimalSquares) {
		
		long totalUnits = 0;
		StringBuilder selectedSquaresInput = new StringBuilder();
		StringBuilder selectedSquaresOutput = new StringBuilder();
		
		selectedSquaresInput.append("[");
		selectedSquaresOutput.append("[");
		
		for(int i = 0; i < optimalSquares.length; i++) {
			if(optimalSquares[i] == 1) {
				selectedSquaresOutput.append(" " + inputArray[i] + ",");
				totalUnits = totalUnits + inputArray[i];
			} else {
				selectedSquaresOutput.append(" X,");
			}
			selectedSquaresInput.append(" " + inputArray[i] + ",");
		}
		selectedSquaresInput.deleteCharAt(selectedSquaresInput.length()-1);
		selectedSquaresOutput.deleteCharAt(selectedSquaresOutput.length()-1);
		selectedSquaresInput.append(" ]");
		selectedSquaresOutput.append(" ]");
		
		System.out.println("Input = " + selectedSquaresInput);
		System.out.println("Output = " + totalUnits + " units. " + selectedSquaresOutput);
		
	}
}
