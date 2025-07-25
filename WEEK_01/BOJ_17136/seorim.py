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

def find_next(pos):
    for x in range(pos, 100):
        if paper[x//10][x%10]:
            return x//10, x%10
        
    return -1, -1

def bt(pos, cnt):
    global n, ans
    if cnt >= ans:
        return
    
    nx, ny = find_next(pos)
    if nx == -1:
        ans = min(ans, cnt)
    
    for paper_size in range(5, 0, -1):
        if left_paper[paper_size] > 0 and canAttach(nx, ny, paper_size):
            # attach
            updatePaper(nx, ny, paper_size, 0)
            left_paper[paper_size] -= 1
            
            bt(nx * 10 + ny, cnt+1)
            
            # detach
            updatePaper(nx, ny, paper_size, 1)
            left_paper[paper_size] += 1

bt(0, 0)
print(ans if ans != 100 else -1)