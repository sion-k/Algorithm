N = int(input())
i = 2
r = int(N ** 0.5)
while i <= r:
    while N % i == 0:
        print(str(i))
        N //= i
    i += 1
if N > 1 : print(str(N))
