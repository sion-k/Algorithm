import sys
input = sys.stdin.readline
print = sys.stdout.write
def range2(i, j) : return (j - i + 1) * (i + j) // 2
def range3(S, i, j) : return S[j] - S[i - 1]
def left(i) : return range3(P, i, N) - range2(i, i + range3(C, i, N) - 1)
def right(i) : return range2(i - range3(C, 1, i - 1), i - 1) - range3(P, 1, i - 1)
TC = int(input())
for tc in range(TC):
	N = int(input())
	S = "." + input().rstrip()
	P = [0] * (N + 1); C = [0] * (N + 1)
	for i in range(1, N + 1):
		P[i] = P[i - 1] + (i if S[i] == "*" else 0)
		C[i] = C[i - 1] + (1 if S[i] == "*" else 0)
	ret = min(left(1), right(N))
	for i in range(2, N) : ret = min(ret, left(i) + right(i))
	print(str(ret))
	print("\n")
