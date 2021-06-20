import sys
input = sys.stdin.readline
print = sys.stdout.write

def gcd(a, b):
    if b == 0 : return a
    return gcd(b, a % b)

def lcm(a, b) : return a * b // gcd(a, b)

TC = int(input())
for tc in range(TC):
    M, N, x, y = map(int, input().split())
    b = N if x % N == 0 else x % N
    k = x
    L = lcm(M, N)
    while k <= L and b != y:
        b = N if (b + M) % N == 0 else (b + M) % N
        k += M
    print(str(k if b == y else -1))
    print("\n")
