import sys

input = sys.stdin.readline

n, m, d = map(int, input().split())
input_map = [list(map(int, input().split())) for _ in range(n)]
enemy = {}
for i in range(n):
    for j in range(m):
        if input_map[i][j] == 1:
            enemy[(i, j)] = True
            
archers = []
ans = -1
def bt(start):
    global ans, n, m
    
    if len(archers) == 3:
        ans = max(ans, play_game())
        return
    
    for i in range(start, m):
        archers.append(i)
        bt(start+1)
        archers.pop()

def play_game():
    global n, m, d
    
    for key in enemy.keys():
        enemy[key] = True
    
    ret = 0
    for round in range(n):
        removed = []
        for archer in archers:
            min_dist = 100
            min_y = m+1
            rx, ry = 0, 0
            for en, live in enemy.items():
                ex, ey = en
                this_round_ex = ex + round
                if this_round_ex >= n or not live:
                    continue
                
                dist = abs(n - this_round_ex) + abs(archer - ey)
                if dist <= d and dist < min_dist:
                    min_dist = dist
                    min_y = ey
                    rx, ry = ex, ey
                elif dist == min_dist and ey < min_y:
                    min_dist = dist
                    min_y = ey
                    rx, ry = ex, ey
            
            if min_dist < 100:
                removed.append((rx, ry))
                
        for rx, ry in removed:
            if enemy[(rx, ry)]:
                enemy[(rx, ry)] = False
                ret += 1
                
    return ret

bt(0)
print(ans)