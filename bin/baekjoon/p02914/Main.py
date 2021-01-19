import math
A, I = map(int, input().split())
X = 0
while math.ceil(X / A) != I:
    X += 1
print(X)
