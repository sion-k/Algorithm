N = int(input())
dp = [0] + [0] * N
for i in range(1, N + 1) :
    j = 1
    dp[i] = i
    while i - j * j >= 0 :
        if dp[i] > 1 + dp[i - j * j] or dp[i] == 0:
            dp[i] = 1 + dp[i - j * j]
        j += 1
print(dp[N])