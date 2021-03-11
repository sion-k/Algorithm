import sys
print = sys.stdout.write
N = int(input())
for i in range(2, N + 1):
    while N % i == 0:
        print(str(i))
        print("\n")
        N /= i
    if N == 1: break
