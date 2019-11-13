"""
    python的参数传递
    这里记住的是类型是属于对象的，而不是变量。而对象有两种,“可更改”（mutable）与“不可更改”（immutable）对象。
    在python中，strings, tuples, 和numbers是不可更改的对象，而 list, dict, set 等则是可以修改的对象。
"""


def fun(a):
    a = 2
    print(a, id(a))


a = 1
fun(a)
print(a, id(a))


def fun(a):
    a.append(1)
    print(a, id(a))


a = []
fun(a)
print(a, id(a))
