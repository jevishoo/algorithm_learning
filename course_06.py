"""
    列表推导式
"""


def squared(x):
    return x * x


multiples = [squared(i) for i in range(10) if i % 3 is 0]
print(multiples)

"""
    字典推导式
"""

iterable = {'a': 1, 'b': 2, 'c': 3}
d = {value: key for (key, value) in iterable.items()}
print(d)

name = ["张三", "李四", "王五", "李六"]
sign = ["白羊座", "双鱼座", "狮子座", "处女座"]
d = {key: value for (key, value) in zip(name, sign)}
print(d)

"""
    集合推导式
"""
squared = {x ** 2 for x in [1, 1, 2]}
print(squared)
