package code.LinkedList;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @Date 2020/9/29 8:01
 * @Created by Jevis_Hoo
 * @Description Asteroid Collision
 * <p>
 * Given an array asteroids of integers representing asteroids in a row.
 * <p>
 * For each asteroid, the absolute value represents its size,
 * and the sign represents its direction (positive meaning right, negative meaning left).
 * Each asteroid moves at the same speed.
 * <p>
 * Find out the state of the asteroids after all collisions.
 * If two asteroids meet, the smaller one will explode. If both are the same size, both will explode.
 * Two asteroids moving in the same direction will never meet.
 */
public class AsteroidCollision {
    public static int[] asteroidCollision(int[] asteroids) {
        LinkedList<Integer> list = new LinkedList<>();

        for (int asteroid : asteroids) {
            if (asteroid < 0) {
                if (list.isEmpty() || list.peekLast() < 0) {
                    list.addLast(asteroid);
                } else {
                    // while peekLast is positive number
                    while (list.peekLast() <= -asteroid) {
                        // Once existing "add", break. Go next asteroid.
                        if (list.peekLast() < 0) {
                            list.addLast(asteroid);
                            break;
                        } else if (list.pollLast() == -asteroid) { // pop last element. If equal, all collisions
                            break;
                        }

                        if (list.isEmpty()){
                            list.addLast(asteroid);
                            break;
                        }
                    }
                }
            } else list.addLast(asteroid); // just add it
        }

        if (list.isEmpty()) return new int[]{};
        else {
            int[] result = new int[list.size()];
            int i = 0;
            while (!list.isEmpty()) {
                result[i++] = list.pollFirst();
            }
            return result;
        }
    }

    public static void main(String[] args) {
        int[] array1 = new int[]{5, 10, -5};
        int[] array2 = new int[]{5, -5};
        int[] array3 = new int[]{10, 2, -5};
        int[] array4 = new int[]{-2, -1, 1, 2};
        int[] array5 = new int[]{-2, -2, 1, -2};
        int[] array6 = new int[]{1, -2, -2, -2};

        System.out.println(Arrays.toString(asteroidCollision(array1)));
        System.out.println(Arrays.toString(asteroidCollision(array2)));
        System.out.println(Arrays.toString(asteroidCollision(array3)));
        System.out.println(Arrays.toString(asteroidCollision(array4)));
        System.out.println(Arrays.toString(asteroidCollision(array5)));
        System.out.println(Arrays.toString(asteroidCollision(array6)));
    }
}
