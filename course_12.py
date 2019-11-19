"""
    新式类和旧式类
"""


# 一个旧式类的深度优先的例子

class A():
    def foo1(self):
        print("A")


class B(A):
    def foo2(self):
        pass


class C(A):
    def foo1(self):
        print("C")


class D(B, C):
    pass


d = D()
'''
    此处，旧式类输出的深度优先 输出：A
    python3类全为新式类，根据C3算法 输出：C
'''
d.foo1()

"""
    C3算法实例
"""
'''
class A(O):pass
class B(O):pass
class C(O):pass
class E(A,B):pass
class F(B,C):pass
class G(E,F):pass

A、B、C都继承至一个基类，所以mro序列依次为[A,O]、[B,O]、[C,O]
mro(E) = [E] + merge(mro(A), mro(B), [A,B])
       = [E] + merge([A,O], [B,O], [A,B])
       
执行merge操作的序列为[A,O]、[B,O]、[A,B]

A是序列[A,O]中的第一个元素，在序列[B,O]中不出现，在序列[A,B]中也是第一个元素，
    所以从执行merge操作的序列([A,O]、[B,O]、[A,B])中删除A，合并到当前mro，[E]中。
mro(E) = [E,A] + merge([O], [B,O], [B])

再执行merge操作，O是序列[O]中的第一个元素，但O在序列[B,O]中出现并且不是其中第一个元素。
    继续查看[B,O]的第一个元素B，B满足条件，所以从执行merge操作的序列中删除B，合并到[E, A]中。
mro(E) = [E,A,B] + merge([O], [O])
       = [E,A,B,O]
'''
