N, K = map(int, input().split())
prime = [False, False] + [True] * N
cnt = [0]
for i in range(2, N + 1):
    if prime[i]:
        cnt.append(i)
        for j in range(i * 2, N + 1, i) :
            if prime[j]:
                prime[j] = False
                cnt.append(j)
print(cnt[K])
