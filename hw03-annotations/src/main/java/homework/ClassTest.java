package homework;

import homework.annotations.After;
import homework.annotations.Before;
import homework.annotations.Test;

class ClassTest {
    public void ClassTest(){}

    @Before
    void before() {
        System.out.println("before");
    }

    @After
    void after() {
        System.out.println("after");
    }

    @Test
    void test() {
        System.out.println("Some test here");
    }

    @Test
    void test2() throws ExceptionInInitializerError {
        System.out.println("Some exception here");
        throw new ExceptionInInitializerError();
    }
}