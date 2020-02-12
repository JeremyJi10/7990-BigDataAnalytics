size = int(input('Enter the number of size: '))

for r in range(size,0,-1):
    for c in range(r):
        print('*',end='')
    print()
