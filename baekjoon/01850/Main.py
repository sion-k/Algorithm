def gcd(a, b):
    if b == 0 : return a
    return gcd(b, a % b)
A, B = map(int, input().split())
print("1" * gcd(A, B))
