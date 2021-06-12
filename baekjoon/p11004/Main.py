import sys
N, K = input().rstrip().split()
print(sorted(map(int, input().split()))[int(K) - 1])
