N = int(input())
for i in range(N):
    for j in range(N):
        print("@", end = "")
    for j in range(N, 4 * N):
        print(" ", end = "")
    for j in range(N):
        print("@", end = "")
    print()
for i in range(N):
    for j in range(N):
        print("@", end = "")
    for j in range(N, 3 * N):
        print(" ", end = "")
    for j in range(N):
        print("@", end = "")
    print()
for i in range(N):
    for j in range(3 * N):
        print("@", end = "")
    print()
for i in range(N):
    for j in range(N):
        print("@", end = "")
    for j in range(N, 3 * N):
        print(" ", end = "")
    for j in range(N):
        print("@", end = "")
    print()
for i in range(N):
    for j in range(N):
        print("@", end = "")
    for j in range(N, 4 * N):
        print(" ", end = "")
    for j in range(N):
        print("@", end = "")
    print()