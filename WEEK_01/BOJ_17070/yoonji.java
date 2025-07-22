import java.util.Scanner;

public class Main {
	static int N;
	static int[][] arr;
	static int answer = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		arr = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		
		move(0, 1, 0); // 시작 (0, 1) 가로 방향
		System.out.println(answer);
	}
	
	static void move(int r, int c, int direction) {
		// (N, N)으로 이동시키는 방법의 수 구하기
		if (r == N - 1 && c == N - 1) {
			answer++;
			return;
		}
		
		// direction = 0, 1, 2 가로 세로 대각선
		if (direction == 0) {
			// 파이프가 가로이면 가로, 대각선 2가지 이동 가능
			// 가로
			if (c+1 < N && arr[r][c+1] == 0) move(r, c+1, 0);
			
			// 대각선
			if (r+1 < N && c+1 < N && arr[r+1][c+1] == 0 && arr[r+1][c] == 0 && arr[r][c+1] == 0) {
				move(r+1, c+1, 3);
			}
			
		}
		
		// 파이프가 세로이면 세로, 대각선 2가지 이동 가능
		else if (direction == 1) {
			// 세로
			if (r+1 < N && arr[r+1][c] == 0) move(r+1, c, 1);
				
			// 대각선
			if (r+1 < N && c+1 < N && arr[r+1][c+1] == 0 && arr[r+1][c] == 0 && arr[r][c+1] == 0) {
				move(r+1, c+1, 3);
			}
						
		}
		
		// 파이프가 대각선이면 가로, 세로, 대각선 3가지 가능
		else {
			if (c+1 < N && arr[r][c+1] == 0) move(r, c+1, 0);
 			// 세로
			if (r+1 < N && arr[r+1][c] == 0) move(r+1, c, 1);
									
			// 대각선
			if (r+1 < N && c+1 < N && arr[r+1][c+1] == 0 && arr[r+1][c] == 0 && arr[r][c+1] == 0) {
				move(r+1, c+1, 3);
			}
		}
	}
}
