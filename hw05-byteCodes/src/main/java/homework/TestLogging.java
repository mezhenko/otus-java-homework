package homework;

import homework.annotations.Log;

class TestLogging implements TestLoggingInterface{

    @Override
    @Log
    public void calculation(int param) {
        System.out.println("calculation — int");
    }

    @Override
    @Log
    public void calculation(int param, boolean param2) {
        System.out.println("calculation — int, boolean");
    }
    @Override
    @Log
    public void calculation2(int param, boolean param2, long param3) {
        System.out.println("calculation2 — int, boolean, long");
    }

    @Override
    public void calculation2(int param, boolean param2, boolean param3) {
        System.out.println("calculation2 — int, boolean, boolean");
    }

    @Override
    public void calculation3(int param) {
        System.out.println("calculation 1 param");
    }
}