n = int(input())

solved = [False] * (n + 1)
value = [0] * (n + 1)

MOD = 10007

def dp(n):
    if n == 0: return 1
    if solved[n]: return value[n]

    ret = 0

    # 맨 오른쪽에 2 × 1 타일을 놓는 경우
    if n - 1 >= 0: ret += dp(n - 1)

    # 맨 오른쪽에 1 × 2 타일을 놓는 경우
    if n - 2 >= 0: ret += dp(n - 2)

    solved[n] = True
    value[n] = ret % MOD

    return value[n]


print(dp(n))
