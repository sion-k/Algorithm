import sys
input = sys.stdin.readline
N = int(input())
print("\n".join(map(str, sorted([int(input()) for i in range(N)])[-1::-1])))
