def esPrimo(a):
    if a <= 1:
        return False
    
    for i in range(2, a):
        if a % i == 0:
            return False
    return True

def primos(a, b):
    listaDePrimos = []
    for i in range(a, b+1):
        if esPrimo(i):
            listaDePrimos.append(i)
    return listaDePrimos

print(primos(10, 20))