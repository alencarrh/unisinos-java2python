class TesteClass:
    nome = None
    quantidade = None

    def method1(): # returns VOID
        a = 1
        b = 10

        i = 50
        while (i<0):
            i+=1
            a=a+b

    def method2(a = None, b = None): # returns STRING
        print("teste123")
        return"asdasd"

        while (a<b):
            a+=1

        if (b==a):
            return"teste"

    def method3(a = None, b = None): # returns VOID

        if (a==1):
            a=a*2

        if (a==b):
            b=b*3

            if (b==10):
                a=2
                c = a+b

                if (c==15):
                    c*=2

                i = c
                while (i<0):
                    i -= 1
                    a=a+b

    def method4(a = None, b = None, c = None, d = None, tipo = None): # returns VOID
        g = c

        if (c==b):

            i = 0
            while (i<b):
                i += 1
                c=c+b
                x = 1
                x=c*2

                l = 0
                while (l<x):
                    l += 1
                    b+=1

        while (g>0):
            b=b-c
            g=g-b

from enum import Enum
class TipoTeste(Enum):
    TESTE1 = 0
    TESTE2 = 1
    TESTE3 = 2
    TESTE4 = 3

