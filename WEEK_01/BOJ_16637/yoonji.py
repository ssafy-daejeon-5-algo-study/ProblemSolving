N = int(input())
exp = input()

# 부호, 숫자 분리
ops = []
for i in range(1, N, 2):
    if exp[i] == '+' or exp[i] == '-' or exp[i] == '*':
        ops.append(exp[i])

nums = []
for i in range(0, N, 2):
    if exp[i] != '+' or exp[i] != '-' or exp[i] != '*':
        nums.append(int(exp[i]))

# 연산 계산
def calc(a, op, b):
    if op == '+': return a + b
    if op == '-': return a - b
    if op == '*': return a * b

max_result = -float('inf')
def dfs(index, result):
    global max_result
    if index >= len(ops):
        max_result = max(result, max_result)
        return

    # 괄호 x
    next_result = calc(result, ops[index], nums[index + 1])
    dfs(index + 1, next_result)

    # 괄호 O: 다음 연산을 먼저 계산
    if index + 1 < len(ops):
        temp = calc(nums[index+1], ops[index+1], nums[index+2])
        next_result = calc(result, ops[index], temp)
        dfs(index + 2, next_result)

dfs(0, nums[0])
print(max_result)