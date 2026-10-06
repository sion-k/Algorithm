n, a, b = map(int, input().split())
if a == b and n <= b:
    print("Anything")
elif a < b or n > b:
    print("Bus")
else:
    print("Subway")
