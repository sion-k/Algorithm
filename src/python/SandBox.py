N = int(input())
dp = [0] + [0] * N
for i in range(1, N + 1) :
    dp[i] = i
    j = 1
    while j * j <= i :
        dp[i] = min(dp[i], 1 + dp[i - j * j])
        j += 1
print(dp[N])