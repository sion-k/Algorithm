S = input()
flag = True
for x in S:
    if x != S[0]:
        flag = False
if flag:
    print(-1)
else:
    if S == S[::-1]:
        print(len(S) - 1)
    else:
        print(len(S))
