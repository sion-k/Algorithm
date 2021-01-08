# [i, )이후로 얻을 수 있는 최대 수익
def dp(i) :
    global N, cache, S
    if i >= N + 1 : return 0
    if cache[i] != 0 : return cache[i]
    m = 0
    # i번째 상담을 선택할 수 있으면 선택하는 경우
    if i + S[i][0] - 1 <= N :
        m = max(m, S[i][1] + dp(i + S[i][0]))
    # i번째 상담을 선택하지 않는 경우
    m = max(m, dp(i + 1))
    cache[i] = m
    return cache[i]

import sys
sys.setrecursionlimit(1500000)
N = int(sys.stdin.readline().rstrip())
cache = [0] * (N + 1)
S = [[]] * (N + 1)
for i in range(1, N + 1) :
    S[i] = list(map(int, sys.stdin.readline().rstrip().split()))
print(dp(1))
