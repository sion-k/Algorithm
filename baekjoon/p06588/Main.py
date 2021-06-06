import sys
input = sys.stdin.readline
print = sys.stdout.write
S = [False, False] + [True] * 1000000
P = []
for i in range(2, 1000001):
    if S[i] :
        P.append(i)
        for j in range(2 * i, 1000001, i) : S[j] = False

def f(x):
    for a in P:
        if a < x and S[x - a] : return a
    return 0

n = int(input())
while n != 0:
    a = f(n)
    print("%d = %d + %d\n" % (n, a, n - a))
    n = int(input())
