import java.io.*;
import java.util.*;

public class Main {
    static int N, M, D;
    static int[][] originMap;
    static int maxKill = 0;
    static int[] dr = {0, -1, 0}; // 좌, 상, 우
    static int[] dc = {-1, 0, 1};

    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] input = bf.readLine().split(" "); // 공백 기준으로 입력 받기
        N = Integer.parseInt(input[0]); 
        M = Integer.parseInt(input[1]);
        D = Integer.parseInt(input[2]);

        originMap = new int[N][M];
        for (int i = 0; i < N; i++) {
            input = bf.readLine().split(" ");
            for (int j = 0; j < M; j++) {
            	originMap[i][j] = Integer.parseInt(input[j]);
            }
        }


        // 궁수 위치 조합
        comb(new int[3], 0, 0);

        System.out.println(maxKill);
    }
    
    public static void comb(int[] archers, int depth, int start) {
    	if (depth == 3) {
    		simulate(archers);
    		return;
    	}
    	
    	for (int i = start; i < M; i++) {
    		archers[depth] = i;
    		comb(archers, depth + 1, i + 1);
    	}
    }
    
    public static void simulate(int[] archers) {
    	int[][] map = new int[N][M];
    	for (int i = 0; i < N; i++) map[i] = originMap[i].clone();
    	
    	int killCount = 0;
    	
    	for (int round = 0; round < N; round++) {
    		Set<String> targets = new HashSet<>();
    		
    		for (int col : archers) {
    			int[] target = bfs(N-1, col, map);
    			if (target != null) {
    				targets.add(target[0] + "," + target[1]);
    			}
    			
    		}
    		
    		// 적 제
    		for (String t : targets) {
    			String[] pos = t.split(",");
    			int r = Integer.parseInt(pos[0]);
    			int c = Integer.parseInt(pos[1]);
    			
    			if (map[r][c] == 1) {
    				map[r][c] = 0;
    				killCount++;
    			}
    		}
    		
    		// 적 한 칸 아래로 내려오기
    		for (int r = N-1; r > 0; r--) {
    			map[r] = map[r-1].clone(); //바로 위 행의 내용을 아래 행으로 복사
    		}
    		Arrays.fill(map[0], 0);
    	}
    	
    	maxKill = Math.max(maxKill, killCount);
    }

    // bfs 가장 가까운 적을 선택
    public static int[] bfs(int r, int c, int[][] map) {
    	boolean[][] visited = new boolean[N][M];
    	Queue<int[]> q = new LinkedList<>();
    	q.offer(new int[]{r, c, 1});
    	visited[r][c] = true;
    	
    	while (!q.isEmpty()) {
    		int[] cur = q.poll();
    		int x = cur[0], y = cur[1], dist = cur[2];
    		
    		if (dist > D) break;
    		
    		if (map[x][y] == 1 && dist <= D) {
    			return new int[] {x, y};
    		}
    		
    		for (int d = 0; d < 3; d++) {
    			int nr = x + dr[d];
    			int nc = y + dc[d];
    			
    			if (nr >= 0 && nr < N && nc >= 0 && nc < M && !visited[nr][nc]) {
    				visited[nr][nc] = true;
    				q.offer(new int[] {nr, nc, dist+1});
    			}
    		}
    	}
    	return null;
    }
}
