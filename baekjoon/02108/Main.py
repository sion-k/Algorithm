import sys
input = sys.stdin.readline
N = int(input())
S = [0] * N
for i in range(N) : S[i] = int(input())
S = sorted(S)
M = [0] * 8001 # [-4000, 4000] -> [0, 8000] 빈도 저장
sum = 0 # 모든 원소의 합
minX, maxX = S[0], S[0] # 최소값, 최대값
for x in S:
    sum += x
    M[x + 4000] += 1
    minX = min(minX, x)
    maxX = max(maxX, x)
print("%0.0f" % (sum / N))
print(S[N // 2])
modeX = max(M) # 최빈값의 등장 빈도
mode = set() # 최빈값의 집합
for x in S:
    if M[x + 4000] == modeX : mode.add(x)
mode = sorted(list(mode))
print(str(mode[0] if len(mode) == 1 else mode[1]))
print(maxX - minX)
