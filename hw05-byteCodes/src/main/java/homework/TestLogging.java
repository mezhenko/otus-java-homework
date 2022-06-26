package homework;

class TestLogging implements TestLoggingInterface{
    public void calculation(int param) {
        System.out.println("calculation — int");
    }

    public void calculation(int param, boolean param2) {
        System.out.println("calculation — int, boolean");
    }

    public void calculation2(int param, boolean param2, long param3) {
        System.out.println("calculation2 — int, boolean, long");
    }

    public void calculation3(int param) {
        System.out.println("calculation 1 param");
    }
}