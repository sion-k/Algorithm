dp = [0, 1] + [0] * 1500000
for i in range(2, 1500001) : dp[i] = (dp[i - 1] + dp[i - 2]) % 1000000
print(dp[int(input()) % 1500000])