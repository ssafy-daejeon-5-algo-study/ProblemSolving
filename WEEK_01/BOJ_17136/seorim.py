import sys

input = sys.stdin.readline

n = 10
paper = [list(map(int, input().split())) for _ in range(n)]
left_paper = [5] * 6
ans = 100

def canAttach(x, y, size):
    global n
    
    if x + size > n or y + size > n:
        return False
    
    for i in range(x, x+size):
        for j in range(y, y+size):
            if paper[i][j] == 0:
                return False
    
    return True

def updatePaper(x, y, size, toggle):
    for i in range(x, x+size):
        for j in range(y, y+size):
            paper[i][j] = toggle

def find_next(x, y):
    for j in range(y, n):
        if paper[x][j] == 1:
            return x, j
    
    for i in range(x+1, n):
        for j in range(n):
            if paper[i][j] == 1:
                return i, j
    
    return -1, -1

def bt(x, y, cnt):
    global n, ans
    if cnt >= ans:
        return
    
    nx, ny = find_next(x, y)
    if nx == -1:
        ans = min(ans, cnt)
    
    for paper_size in range(5, 0, -1):
        if left_paper[paper_size] > 0 and canAttach(nx, ny, paper_size):
            # attach
            updatePaper(nx, ny, paper_size, 0)
            left_paper[paper_size] -= 1
            
            bt(nx, ny, cnt+1)
            
            # detach
            updatePaper(nx, ny, paper_size, 1)
            left_paper[paper_size] += 1

bt(0, 0, 0)
print(ans if ans != 100 else -1)