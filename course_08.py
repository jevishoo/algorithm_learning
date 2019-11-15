"""
    迭代器和生成器
"""

"""
    生成器是一种特殊的迭代器
    生成器表达式是列表推导式的生成器版本
"""
'''
def something():
    result = []
    for ... in ...:
        result.append(x)
    return result

可以替换成生成器
def iter_something():
    for ... in ...:
        yield x
'''

L = [x * x for x in range(10)]
print(L)
g = (x * x for x in range(10))
print(g)


def generator():
    g = (x * x for x in range(10))
    # return g
    yield g


print(generator())


name = (1, 2, 3)
# print("hi there %s" % name)  # error
print("hi there %s" % (name,))
print("hi there {}".format(name))
