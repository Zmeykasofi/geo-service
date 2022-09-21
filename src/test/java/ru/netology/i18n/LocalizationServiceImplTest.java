package ru.netology.i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;


class LocalizationServiceImplTest {

    @Test
    void locale() {
        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();
        String expectedMessage = "Welcome";
        String actualMessage = localizationService.locale(Country.GERMANY);
        Assertions.assertEquals(expectedMessage, actualMessage);
    }
}