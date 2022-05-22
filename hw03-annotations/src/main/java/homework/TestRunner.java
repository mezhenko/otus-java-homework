package homework;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import homework.annotations.After;
import homework.annotations.Before;
import homework.annotations.Test;

public class TestRunner {
    private final Class<?> testingClass;
    private final List<Method> beforeMethods;
    private final List<Method> afterMethods;
    private final List<Method> testMethods;

    private TestRunner(Class<?> testingClass, List<Method> beforeMethods, List<Method> afterMethods, List<Method> testMethods) {
        this.testingClass = testingClass;
        this.beforeMethods = beforeMethods;
        this.afterMethods = afterMethods;
        this.testMethods = testMethods;
    }

    public static TestRunner create(Class<?> testingClass) {
        List<Method> befores = Arrays.stream(testingClass.getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(Before.class)).toList();

        List<Method> afters = Arrays.stream(testingClass.getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(After.class)).toList();

        List<Method> tests = Arrays.stream(testingClass.getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(Test.class)).toList();

        return new TestRunner(testingClass, befores, afters, tests);
    }

    @Override
    public String toString() {
        return "TestRunner{" +
                "class=" + testingClass.getName() +
                "} + ";
    }

    public void run() {
        int count = 0;
        int failed = 0;
        for (Method testMethod : this.testMethods) {
            Object instance = null;

            try {
                instance = this.testingClass.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }

            boolean beforeFailed = runBefore(instance);
            if (beforeFailed) {
                this.runAfter(instance);
                failed++;
                break;
            }

            try {
                testMethod.invoke(instance);
            } catch (Exception e) {
                failed++;
                e.printStackTrace();
            }

            boolean afterFailed = this.runAfter(instance);

            if (afterFailed) {
                break;
            }

            count++;
        }

        getReport(count, failed);
    }

    private Boolean runBefore(Object instance) {
        try {
            for (Method beforeMethod : this.beforeMethods) {
                beforeMethod.invoke(instance);
            }
        } catch (Exception e) {
            System.out.println("Before methods failed!");
            e.printStackTrace();
            return true;
        }
        return false;
    }

    private Boolean runAfter(Object instance) {
        try {
            for (Method afterMethod : this.afterMethods) {
                afterMethod.invoke(instance);
            }
        } catch (Exception e) {
            System.out.println("After methods failed!");
            e.printStackTrace();
            return true;
        }
        return false;
    }

    private void getReport(int count, int failed) {
        System.out.println("Test run{" +
                "count=" + count +
                ", failed=" + failed +
                '}');
    }
}
