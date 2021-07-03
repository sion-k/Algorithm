import sys
input = sys.stdin.readline
N, W = map(int, input().split())
S3 = [1000000000]; S5 = [1000000000]
for i in range(N):
    t, s = map(int ,input().split())
    if t == 3 : S3.append(s)
    else : S5.append(s)
S3.sort(reverse = True); S5.sort(reverse = True);
S3[0] = S5[0] = 0
for i in range(1, len(S3)) : S3[i] += S3[i - 1]
for i in range(1, len(S5)) : S5[i] += S5[i - 1]
ret = 0
for i in range(min(len(S3) - 1, W // 3) + 1):
    ret = max(ret, S3[i] + S5[min(len(S5) - 1, (W - 3 * i) // 5)])
print(ret)
