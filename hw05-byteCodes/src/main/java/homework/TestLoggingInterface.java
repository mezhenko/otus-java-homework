package homework;

import homework.annotations.Log;

public interface TestLoggingInterface {

    @Log
    void calculation(int param);
    @Log
    void calculation(int param, boolean param2);
    @Log
    void calculation2(int param, boolean param2, long param3);
    void calculation3(int param);
}