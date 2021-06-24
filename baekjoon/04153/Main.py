a, b, c = map(int, input().split())
while a:
    a, b, c = sorted((a, b, c))
    print("right" if a ** 2 + b ** 2 == c ** 2 else "wrong")
    a, b, c = map(int, input().split())
