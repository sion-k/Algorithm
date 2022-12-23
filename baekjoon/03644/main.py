import sys
sys.setrecursionlimit(100000)

n = 3
cache = [[-1] * n for i in range(2)]

def dp(i: int, j: bool) -> int:
    if i >= n: return 1
    if cache[j][i] != -1: return cache[j][i]
    
    sum = dp(i + 1, j)
    if i != n - 1 or not j:
        sum += dp(i + 2, i == 0 or j)

    cache[j][i] = sum
    return cache[j][i]

a =  map(int, sys.stdin.readlines())

for x in a:
    n = x
    cache = [[-1] * n for i in range(2)]

    print(dp(0, False))
