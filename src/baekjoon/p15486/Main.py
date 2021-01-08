import sys
N = int(input())
S = [[]] * (N + 1)
for i in range(1, N + 1) :
    S[i] = list(map(int, sys.stdin.readline().rstrip().split()))
dp = [0] * (N + 2)
for i in range(N, 0, -1) :
    m = 0
    # 상담 가능한 경우 선택
    next = i + S[i][0]
    if next <= N + 1 :
        m = max(m, S[i][1] + dp[next])
    # 선택하지 않는 경우
    m = max(m, dp[i + 1])
    dp[i] = m
print(dp[1])
