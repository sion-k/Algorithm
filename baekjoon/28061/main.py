n = int(input())
a = list(map(int, input().split()))
print(max(x - (n - i) for i, x in enumerate(a)))
