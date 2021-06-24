M = 123456 * 2
prime = [False] + [False] + [True] * (M)
for i in range(2, M + 1) : 
	 if prime[i] : 
	 	for n in range(i * 2, M + 1, i) :
	 		prime[n] = False
pSum = [0] + [0] * M
for i in range(1, M + 1) : 
    pSum[i] = pSum[i - 1]
    if prime[i] : pSum[i] += 1
import sys
L = int(sys.stdin.readline())
while L != 0 :
	print(pSum[2 * L] - pSum[L])
	L = int(sys.stdin.readline())
