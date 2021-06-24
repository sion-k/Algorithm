S = input().split()
sum = 0
for i in range(5):
    sum = (sum + int(S[i]) ** 2) % 10
print(sum)
