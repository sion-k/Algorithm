S = [" "] + list(input())
ret = 0
for i in range(1, len(S)):
    if S[i] == "N" : continue
    else :
        for j in range(i, len(S), i) :
            if S[j] == "Y" : S[j] = "N"
            else : S[j] = "Y"
        ret += 1
check = True
for i in range(1, len(S)):
    if S[i] == "Y" : check = False
print(ret if check else -1)