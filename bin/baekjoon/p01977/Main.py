M = int(input())
N = int(input())
S = [i * i for i in range(101) if M <= i * i <= N]
print("%d\n%d" % (sum(S), S[0]) if S else -1)
