package homework;

import homework.errors.CantWithdrawAmountError;
import homework.errors.UnknownBanknoteError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AtmTest {
    private AtmInterface atm;

    @BeforeEach
    void before() {
        atm = AtmImpl.createEmpty();
    }

    @Test()
    @DisplayName("Проверяем, что в пустом банкомате баланс 0")
    void checkEmptyBalance() {
        assertEquals(0, atm.getBalance());
    }
    @Test()
    @DisplayName("Проверяем, что одинаковые банкноты складываются")
    void checkAddsSameBanknotes() {
        assertDoesNotThrow(() -> {
            atm.addBanknotes(100, 3);
            atm.addBanknotes(100, 1);
        });
        assertEquals(400, atm.getBalance());
    }


    @Test()
    @DisplayName("Проверяем, что баланс считается для разных банкнот")
    void checkAmount() {
        assertDoesNotThrow(() -> {
            atm.addBanknotes(100, 4);
            atm.addBanknotes(500, 2);
            atm.addBanknotes(1000, 1);
            atm.addBanknotes(5000, 3);
        });

        assertEquals(17400, atm.getBalance());
    }

    @Test()
    @DisplayName("Проверяем, что падает ошибка при добавлении неизвестной банкноты")
    void checkAddInvalidBanknote() {
        assertThrows(UnknownBanknoteError.class, () -> atm.addBanknotes(200, 1));
    }

    @Test()
    @DisplayName("Проверяем, что падает ошибка при попытке взять сумму, которую нельзя составить")
    void getInvalidAmountSimple() {
        assertThrows(CantWithdrawAmountError.class, () -> atm.getWithdraw(200));
    }

    @Test()
    @DisplayName("Проверяем, что падает ошибка при попытке взять сумму, которую нельзя составить v2")
    void getInvalidAmount() {
            assertDoesNotThrow(() -> {
                atm.addBanknotes(100, 4);
                atm.addBanknotes(500, 2);
                atm.addBanknotes(1000, 1);
                atm.addBanknotes(5000, 3);
                atm.getWithdraw(6100);
            });

        assertThrows(CantWithdrawAmountError.class, () -> atm.getWithdraw(400));
    }
}