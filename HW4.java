
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
public class HW4 {
	static BufferedReader br;
	public static void main(String[] args) throws IOException {
	ArrayList<String> filesToTest = new ArrayList<String>();
	for(String f: args){
		filesToTest.add(f);
	}
		
	try{
	File file = new File(filesToTest.get(0));
	br = new BufferedReader(new FileReader(file));
	
	//Gets Number of cases
	String cases = br.readLine();
	int ncases = Integer.parseInt(cases);
	
	//Main while loop for executing methods on 2-D Array
		while(ncases != 0){
			String name;
			int row;
			int col;
			String nrc = br.readLine();
			String[] split = nrc.split(" ");
			name = split[0];
			row = Integer.parseInt(split[1]);
			col = Integer.parseInt(split[2]);
			int[][] wflows = new int[row][col];
			for(int x = 0 ; x < row; x++){
				String flo = br.readLine();
				String[] flow = flo.split(" ");
				for(int y = 0; y < col; y++){
					wflows[x][y] = Integer.parseInt(flow[y]);
				}
			}
	
			//Fills the results table with 0's
			int[][] results  = new int[row][col];
			for(int c = 0 ; c < row; c++){
				for(int d = 0; d < col; d++){
					results[c][d] = 0;
				}
			}
			
			//Gets longest Sequence after recursive method longestSeq is executed
			int longestS = 0;
			for(int o = 0 ; o < row; o++){
				for(int p = 0; p < col; p++){
					int temp = longestSeq(wflows, row-1, col-1, results, o, p);
					if(temp > longestS){
						longestS = temp;
					} 
				}
			}
			
			//Prints out longest Sequence
			System.out.println(name + ": " + longestS);
		
		//Decrements number of cases 	
		ncases--;
		}
	}
	catch(FileNotFoundException s){
	System.out.println("File does Not Exist");
	}
  }
	static int longestSeq(int[][] lseq, int nrows, int ncols, int[][] results, int nrow, int ncol){
		int left = 0, right = 0, up = 0, down = 0;
		if(!left(lseq, nrow, ncol) && !right(lseq, nrow, ncol, ncols) && !down(lseq, nrow, ncol, nrows) && !up(lseq, nrow, ncol)){
			results[nrow][ncol] =  1;
			return results[nrow][ncol];
		}
		//Left
		if(left(lseq, nrow, ncol)){
			if(results[nrow][ncol-1] > 0){
				left = 1 + results[nrow][ncol-1];
			}
			else
				left = 1 + longestSeq(lseq, nrows, ncols, results, nrow, ncol-1);
		}
		//Right
		if(right(lseq, nrow, ncol, ncols)){
			if(results[nrow][ncol+1] > 0){
				right = 1 + results[nrow][ncol+1];
			}
			else
				right = 1 + longestSeq(lseq, nrows, ncols, results, nrow, ncol+1);
		}
		//Up
		if(up(lseq, nrow, ncol)){
			if(results[nrow-1][ncol] > 0){
				up = 1 + results[nrow-1][ncol];
			}
			else
				up = 1 + longestSeq(lseq, nrows, ncols, results, nrow-1, ncol);
		}
		//Down
		if(down(lseq, nrow, ncol, nrows)){
			if(results[nrow+1][ncol] > 0){
				down = 1 + results[nrow+1][ncol];
			}
			else
				down = 1 + longestSeq(lseq, nrows, ncols, results, nrow+1, ncol);
		}
		
		results[nrow][ncol] = MAX(left, right, up, down);
		return results[nrow][ncol];
	}
	//Checks to see if you can go Left
	static boolean left(int[][] seq, int r, int c){
		if(c > 0){
			if(seq[r][c] > seq[r][c-1])
				return true;
		}
		return false;
	}
	//Checks to see if you can go Right
	static boolean right(int[][] seq, int r, int c, int maxc){
		if(c < maxc){
			if(seq[r][c] > seq[r][c+1])
				return true;
		}
		return false;
	}
	//Checks to see if you can go Up
	static boolean up(int[][] seq, int r, int c){
		if(r > 0){
			if(seq[r][c] > seq[r-1][c])
				return true;
		}
		return false;
	}
	//Checks to see if you can go Down
	static boolean down(int[][] seq, int r, int c, int maxr){
		if(r < maxr){
			if(seq[r][c] > seq[r+1][c])
				return true;
		}
		return false;
	}
	//Returns Max of 4 Numbers
	static int MAX(int a, int b, int c, int d){
		int max = -1;
		if((a >= b) && (a >= c) && (a >= d)){
			max = a;
		}
		else if((b >= a) && (b >= c) && (b >= d)){
			max = b;
		}
		else if((c >= a) && (c >= b) && (c >= d)){
			max = c;
		}
		else if((d >= a) && (d >= b) && (d >= c)){
			max = d;
		}
		return max;
	}
}

