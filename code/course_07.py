"""
    Python中单下划线和双下划线
"""

"""
    __foo__：约定为python内部名称，用来区别用户自定义命名
    _foo：约定为指定私有变量的一种方式，不能用from module import *导入，
    但可通过import module获取 module._foo
    __foo：解析器会用_classname__foo来代替这个名字，以区分和其他类相同的命名，
    它无法直接向公有成员一样随便访问。需要通过  对象名._类名__xxx  方式访问
"""


class MyClass():
    def __init__(self):
        self.__superprivate = "Hello"
        self._semiprivate = ", world!"


mc = MyClass()

print(mc._semiprivate)
print(mc.__dict__)
print(mc._MyClass__superprivate)
# print(mc.__superprivate)
