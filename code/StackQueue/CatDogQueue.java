package code.StackQueue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Date 2020/8/15 17:15
 * @Created by Jevis_Hoo
 * @Description 猫狗队列
 * 【要求】
 * 用户可以调用add 方法将cat 类或dog 类的实例放入队列中；
 * 用户可以调用pollAll 方法，将队列中所有的实例按照进队列的先后顺序依次弹出；
 * 用户可以调用pollDog 方法，将队列中dog 类的实例按照进队列的先后顺序依次弹出；
 * 用户可以调用pollCat 方法，将队列中cat 类的实例按照进队列的先后顺序依次弹出；
 * 用户可以调用isEmpty 方法，检查队列中是否还有dog 或cat 的实例；
 * 用户可以调用isDogEmpty 方法，检查队列中是否有dog 类的实例；
 * 用户可以调用isCatEmpty 方法，检查队列中是否有cat 类的实例。
 */
public class CatDogQueue {
    private Queue<PetQueue> dog;
    private Queue<PetQueue> cat;
    private int count;

    public CatDogQueue() {
        this.dog = new LinkedList<>();
        this.cat = new LinkedList<>();
        this.count = 0;
    }

    public void add(Pet pet) {
        if (pet.getPetType().equals("dog")) {
            dog.add(new PetQueue(pet, count++));
        } else {
            cat.add(new PetQueue(pet, count++));
        }
    }

    public Pet pollAll() {
        if (!isDogEmpty() && !isCatEmpty()) {
            if (dog.peek().getCount() < cat.peek().getCount()) {
                System.out.println(dog.peek().getCount());
                return dog.poll().getPet();
            } else {
                System.out.println(cat.peek().getCount());
                return cat.poll().getPet();
            }
        } else if (!dog.isEmpty()) {
            System.out.println(dog.peek().getCount());
            return dog.poll().getPet();
        } else {
            System.out.println(cat.peek().getCount());
            return cat.poll().getPet();
        }
    }

    public Dog pollDog() {
        if (!isDogEmpty()) {
            System.out.println(dog.peek().getCount());
            return (Dog) dog.poll().getPet();
        } else {
            return null;
        }
    }

    public Cat pollCat() {
        if (!isCatEmpty()) {
            System.out.println(cat.peek().getCount());
            return (Cat) cat.poll().getPet();
        } else {
            return null;
        }
    }

    public boolean isEmpty() {
        return dog.isEmpty() && cat.isEmpty();
    }

    public boolean isDogEmpty() {
        return dog.isEmpty();
    }

    public boolean isCatEmpty() {
        return cat.isEmpty();
    }

    public static void main(String[] args) {
        CatDogQueue cdQueue = new CatDogQueue();
        cdQueue.add(new Dog());
        cdQueue.add(new Dog());
        cdQueue.add(new Dog());
        cdQueue.add(new Cat());
        cdQueue.add(new Cat());
        cdQueue.add(new Dog());
        cdQueue.add(new Cat());
        cdQueue.add(new Dog());
        cdQueue.add(new Cat());

        while (!cdQueue.isEmpty()) {
            System.out.println(cdQueue.pollAll().getPetType());
//            if (!cdQueue.isCatEmpty()) {
//                System.out.println(cdQueue.pollCat().getPetType());
//            }
//            if (!cdQueue.isCatEmpty()) {
//                System.out.println(cdQueue.pollCat().getPetType());
//            }
//            if (!cdQueue.isDogEmpty()) {
//                System.out.println(cdQueue.pollDog().getPetType());
//            }
        }
    }
}

class PetQueue {
    private Pet pet;
    private int count;

    public PetQueue(Pet pet, int count) {
        this.pet = pet;
        this.count = count;
    }

    public Pet getPet() {
        return pet;
    }

    public int getCount() {
        return count;
    }

}

class Pet {
    private String type;

    public Pet(String type) {
        this.type = type;
    }

    public String getPetType() {
        return this.type;
    }
}

class Dog extends Pet {
    public Dog() {
        super("dog");
    }
}

class Cat extends Pet {
    public Cat() {
        super("cat");
    }
}