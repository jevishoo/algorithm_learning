"""
            实例方法    类方法	        静态方法
    a = A() a.foo(number)   a.class_foo(number)	a.static_foo(number)
    A	    不可用      A.class_foo(number)	A.static_foo(number)
"""


class A(object):
    def foo(self, x):
        print("executing foo(%s,%s)" % (self, x))

    @classmethod
    def class_foo(cls, x):
        print("executing class_foo(%s,%s)" % (cls, x))

    @staticmethod
    def static_foo(x):
        print("executing static_foo(%s)" % x)


a = A()
a.foo("1")
a.class_foo('2')
A.class_foo('3')
a.static_foo('4')
A.static_foo('5')
