N, K = tuple(map(int, input().split()))
S = (0, ) + tuple(map(int, input().split()))
pSum = [0] * (N + 1)
for i in range(1, N + 1) :
    pSum[i] = pSum[i - 1] + S[i]
cnt = 0
# 모든 [i, j] 구간에 대해서 구간 합을 구한다
for i in range(1, N + 1) :
    for j in range(i, N + 1) :
        if pSum[j] - pSum[i - 1] == K :
            cnt += 1
print(cnt)