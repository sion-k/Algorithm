solved = [False] * 11
value = [0] * 11

def dp(n):
    if n == 0: return 1
    if solved[n]: return value[n]

    ret = 0

    for i in range(1, 3 + 1):
        if n - i >= 0: ret += dp(n - i)

    solved[n] = True
    value[n] = ret

    return value[n]

tc = int(input())
for _ in range(tc):
    n = int(input())
    print(dp(n))
