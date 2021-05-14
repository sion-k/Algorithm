import sys
M = 10000
isPrime = [False, False, True] + [True, False] * (M // 2)
prime = []
for i in range(1, M + 1) :
    if isPrime[i] :
        for j in range(i * 2, M + 1, i) : isPrime[j] = False
        prime.append(i)
# 정수 n (4 <= n <= 10000)에 대해 골드바흐 파티션 튜플 반환
def goldBach(n):
    diff = 10000
    # (a, b) (a <= b)는 골드바흐 파티션
    a, b = 2, 2
    for i in prime :
        if not i < n : break
        j = n - i
        if not i <= j : break
        # 골드바흐 파티션을 찾았고 기존것보다 차가 작은 경우
        if isPrime[j] and (j - i) < diff :
            a, b = i, j
            diff = (j - i)
    return a, b

T = int(sys.stdin.readline())
for tc in range(T) :
    L = int(sys.stdin.readline())
    a, b = goldBach(L)
    sys.stdout.write("%d %d" % (a, b))
    sys.stdout.write("\n")
