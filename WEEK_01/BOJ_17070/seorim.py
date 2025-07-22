import sys
from collections import deque

input = sys.stdin.readline

n = int(input())
board = [list(map(int, input().split())) for _ in range(n)]
dp = [[[-1] * 3 for _ in range(n)] for _ in range(n)]

# 0 가로
# 1 세로
# 2 대각선

move = [
    [(0,1,0), (1,1,2)],
    [(1,0,1), (1,1,2)],
    [(0,1,0), (1,0,1), (1,1,2)]
]

ans = 0
def dfs(x, y, d):
    global n, ans
    
    if x == n-1 and y == n-1:
        return 1
    
    if dp[x][y][d] != -1:
        return dp[x][y][d]
    
    cnt = 0
    for dx, dy, nd in move[d]:
        nx, ny = x + dx, y + dy
        
        if not (0 <= nx and nx < n and 0 <= ny and ny < n):
            continue
        
        if nd == 2:  # 대각선
            if board[nx][ny] == 0 and board[nx-1][ny] == 0 and board[nx][ny-1] == 0:
                cnt += dfs(nx, ny, nd)
        else:  # 가로, 세로
            if board[nx][ny] == 0:
                cnt += dfs(nx, ny, nd)
    
    dp[x][y][d] = cnt
    return cnt

print(dfs(0, 1, 0))