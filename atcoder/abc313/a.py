n = int(input())
a = list(map(int, input().split()))

if n == 1:
    print(0)
else:
    print(max(0, max(a[1:]) - a[0] + 1))
