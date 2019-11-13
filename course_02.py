"""
    元类
"""


# 创建一个Hello类，拥有属性say_hello ----二的起源
class Hello():
    def say_hello(self, name='world'):
        print('Hello, %s.' % name)


# 从Hello类创建一个实例hello ----二生三
hello = Hello()

# 使用hello调用方法say_hello ----三生万物
hello.say_hello()


class SayMetaClass(type):

    def __new__(cls, name, bases, attrs):
        attrs['say_' + name] = lambda self, value, saying=name: print(saying + ',' + value + '!')
        return type.__new__(cls, name, bases, attrs)


# 道生一：传入type
class SayMetaClass(type):

    # 传入三大永恒命题：类名称、父类、属性
    def __new__(cls, name, bases, attrs):
        # 创造“天赋”
        attrs['say_' + name] = lambda self, value, saying=name: print(saying + ',' + value + '!')
        # 传承三大永恒命题：类名称、父类、属性
        return type.__new__(cls, name, bases, attrs)


# 一生二：创建类
class Hello(object, metaclass=SayMetaClass):
    pass


# 二生三：创建实列
hello = Hello()
# 三生万物：调用实例方法
hello.say_Hello('world')


# 一生二：创建类
class Nihao(object, metaclass=SayMetaClass):
    pass


# 二生三：创建实列
n = Nihao()
# 三生万物：调用实例方法
n.say_Nihao('中华!')
