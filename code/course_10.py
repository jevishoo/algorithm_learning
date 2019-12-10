"""
    *args and **kwargs
"""
a_string = "This is a global variable"


def foo():
    a_string = "test"  # 1
    print(locals())
    return 1


print(foo())
print(a_string)


# 闭包
def outer():
    x = 1

    def inner():
        print(x)  # 1

    return inner


foo = outer()
print(foo.__closure__)
print(foo)

''' 装饰器'''


# 1.装饰器说明
def outer(some_func):
    def inner():
        print("before some_func")
        ret = some_func()  # 1
        return ret + 1

    return inner


'''
def foo():
    return 1


decorated = outer(foo)  # 2
print(decorated())
'''


# 等同于
@outer
def foo():
    return 1


print(foo())


# 2.实例1
def wrapper(func):
    def checker(a, b):
        print(a, b)
        if a.x < 0 or a.y < 0:
            a = Coordinate(a.x if a.x > 0 else 0, a.y if a.y > 0 else 0)
        if b.x < 0 or b.y < 0:
            b = Coordinate(b.x if b.x > 0 else 0, b.y if b.y > 0 else 0)
        ret = func(a, b)
        if ret.x < 0 or ret.y < 0:
            ret = Coordinate(ret.x if ret.x > 0 else 0, ret.y if ret.y > 0 else 0)
        return ret

    return checker


class Coordinate(object):
    def __init__(self, x, y):
        self.x = x
        self.y = y

    def __repr__(self):
        return "Coord: " + str(self.__dict__)


@wrapper
def add(a, b):
    return Coordinate(a.x + b.x, a.y + b.y)


@wrapper
def sub(a, b):
    return Coordinate(a.x - b.x, a.y - b.y)


print(add(Coordinate(1, 2), Coordinate(2, 1)))
print(sub(Coordinate(1, 2), Coordinate(2, 1)))


# 3.实例2
def logger(func):
    def inner(*args, **kwargs):  # 1
        print("Arguments were: %s, %s" % (args, kwargs))
        return func(*args, **kwargs)  # 2

    return inner


@logger
def foo1(x, y=1):
    return x * y


@logger
def foo2():
    return 2


print(foo1(5, 4))
print(foo1(1))
print(foo2())
