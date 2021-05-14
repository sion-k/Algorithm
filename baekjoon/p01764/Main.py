import sys
input = sys.stdin.readline
print = sys.stdout.write
A = set()
B = set()
N, M = map(int, input().split())
for i in range(N) : A.add(input().rstrip())
for i in range(M) : B.add(input().rstrip())
C = list(A.intersection(B))
C = sorted(C)
print(str(len(C)))
print("\n")
print("\n".join(C))
print("\n")
