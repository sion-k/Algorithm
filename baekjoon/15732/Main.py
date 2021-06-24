import sys
input = sys.stdin.readline
N, K, D = map(int, input().split())
S = [list(map(int, input().rstrip().split())) for i in range(K)]

# x번상자까지만 담으면 D개 이상 담을 수 있는지 여부
def f(x):
	sum = 0
	for A, B, C in S:
		B = min(B, x)
		if A > B : continue
		sum += ((B - A) // C + 1)
		if sum >= D : return True
	return False
	
lo = 0; hi = N
while lo + 1 < hi:
	mid = (lo + hi) // 2
	if mid != 0 and f(mid) : hi = mid
	else : lo = mid
print(hi)
