N, K = map(int, input().split())
S = [()] * (N + 1)
for i in range(1, N + 1) :
    S[i] = tuple(map(int, input().split()))
print(S)
dp = ((0, ) * (K + 1), ) * 2

print(dp)
