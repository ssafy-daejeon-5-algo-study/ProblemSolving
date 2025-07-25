import sys
from math import inf

input = sys.stdin.readline

n = int(input())
formula = input().rstrip()
ans = -inf

def operate(a, b, op):
    return eval(f"{a}{op}{b}")

def dfs(pos, curr):
    global ans, n
    
    if pos > n-2:
        ans = max(ans, curr)
        return
    
    # 현재 연산자 오른쪽 괄호 안 치기
    dfs(pos+2, operate(curr, formula[pos+1], formula[pos]))
    
    # 현재 연산자 오른쪽 괄호 치기
    if pos + 3 < n:
        parenthesis = operate(formula[pos+1], formula[pos+3], formula[pos+2])
        dfs(pos+4, operate(curr, parenthesis, formula[pos]))

dfs(1, int(formula[0]))
print(ans)