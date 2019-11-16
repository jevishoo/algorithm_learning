"""
    *args and **kwargs
"""

"""
    不确定函数里将要传递多少参数时,可以用*args.例如,它可以传递任意数量的参数
"""


def print_everything(*args):
    for count, thing in enumerate(args):
        print('{0}. {1}'.format(count, thing))


print_everything('apple', 'banana', 'cabbage')

"""
    **kwargs允许使用没有事先定义的参数名
"""


def table_things(**kwargs):
    for name, value in kwargs.items():
        print('{0} = {1}'.format(name, value))


table_things(apple='fruit', cabbage='vegetable')

"""
    *args和**kwargs可以同时在函数的定义中,但是*args必须在**kwargs前面
"""


def print_three_things(a, b, c):
    print('a = {0}, b = {1}, c = {2}'.format(a, b, c))


my_list = ['aardvark', 'baboon', 'cat']
print_three_things(*my_list)
