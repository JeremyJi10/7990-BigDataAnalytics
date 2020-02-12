#Yuxuan Ji Assignment5B: Mode

def CalcMode(list):
    dict = {}
    list2 = []
    num = 0
    for i in list:
        if i not in dict:
            dict[i] = list.count(i)

    for key, value in dict.items():
        if value > num:
            num = value
            list2.clear()
            list2.append(key)
        elif value == num:
            list2.append(key)

    return(list2)

print(CalcMode([1,2,3,2]))
print(CalcMode([1,2,1,2]))
print(CalcMode([]))

