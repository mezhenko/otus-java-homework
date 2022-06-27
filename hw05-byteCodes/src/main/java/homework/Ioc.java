package homework;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import homework.annotations.Log;

class Ioc {
    private Ioc() {}
    static TestLoggingInterface createTestLogging() {
        InvocationHandler handler = new DemoInvocationHandler(new TestLogging());
        return (TestLoggingInterface) Proxy.newProxyInstance(Ioc.class.getClassLoader(),
                new Class<?>[]{TestLoggingInterface.class}, handler);
    }

    static class DemoInvocationHandler implements InvocationHandler {
        private final TestLoggingInterface testLogging;
        private final List<Method> methods;

        DemoInvocationHandler(TestLoggingInterface testLogging) {
            this.testLogging = testLogging;
            this.methods = Arrays.stream(TestLogging.class.getDeclaredMethods())
                    .filter(m -> m.isAnnotationPresent(Log.class)).toList();
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            for (Method m : this.methods) {
                if (!m.getName().equals(method.getName()) || m.getParameters().length != method.getParameters().length) {
                    continue;
                }

                Parameter[] mParameters = m.getParameters();
                Parameter[] methodParameters = method.getParameters();
                boolean allParametersHaveSameType = true;
                for (int i = 0; i < mParameters.length; i++) {
                    if (!mParameters[i].getType().equals(methodParameters[i].getType())) {
                        allParametersHaveSameType = false;
                        break;
                    }
                }

                if (allParametersHaveSameType) {
                    System.out.println("method params: " + (Objects.isNull(args) ? "null" :
                            Arrays.stream(args).map(Object::toString).collect(Collectors.joining(", "))));
                }

            }

            return method.invoke(testLogging, args);
        }

        @Override
        public String toString() {
            return "DemoInvocationHandler{" +
                    "testLogging=" + testLogging +
                    '}';
        }
    }
}
