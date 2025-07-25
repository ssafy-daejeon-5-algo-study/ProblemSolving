import java.io.*;

public class Main {
    static int[][] map = new int[10][10];
    static int[] paper = {0, 5, 5, 5, 5, 5};
    static int min = Integer.MAX_VALUE;
    
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        
        //10x10 입력 받기
        for (int i = 0; i < 10; i++) {
        	String[] input = bf.readLine().split(" "); // 공백 기준으로 입력 받기
        	for (int j = 0; j < 10; j++) {
        		map[i][j] = Integer.parseInt(input[j]);
        	}
        }
        
        dfs(0, 0, 0); // x, y, 색종이 사용 개수
        
        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
    }
    
    static void dfs(int x, int y, int used) {
    	if (x >= 10) { 
    		min = Math.min(min, used);
    		return;
    	}
    	
    	if (y >= 10) {
    		dfs(x+1, 0, used);
    		return;
    	}
    	
    	if (map[x][y] == 1) {
    		for (int size = 5; size >= 1; size--) {
    			if (canAttach(x, y, size) && paper[size] > 0) {
    				attach(x, y, size, 0);
    				paper[size]--;
    				dfs(x, y + 1, used + 1);
    				attach(x, y, size, 1);
    				paper[size]++;
    			}
    		}
    	} else {
    		dfs(x, y + 1, used);
    	}
    }
    
    static boolean canAttach(int x, int y, int size) {
    	if (x + size > 10 || y + size > 10) return false;
    	
    	for (int i = x; i < x + size; i++) {
    		for (int j = y; j < y + size; j++) {
    			if (map[i][j] != 1) return false;
    		}
    	}
    	
    	return true;
    }
    
    static void attach(int x, int y, int size, int value) {
    	for (int i = x; i < x + size; i++) {
    		for (int j = y; j < y + size; j++) {
    			map[i][j] = value;
    		}
    	}
    }
        
}
