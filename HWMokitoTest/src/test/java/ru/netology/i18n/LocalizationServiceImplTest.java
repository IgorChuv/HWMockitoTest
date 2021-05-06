package ru.netology.i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static ru.netology.entity.Country.RUSSIA;
import static ru.netology.entity.Country.GERMANY;
import static ru.netology.entity.Country.BRAZIL;
import static ru.netology.entity.Country.USA;

public class LocalizationServiceImplTest {

    @Test
    void localeRussiaTest(){
        String expected = "Добро пожаловать";
        LocalizationServiceImpl localizationServiceImpl = Mockito.spy(LocalizationServiceImpl.class);
        String result = localizationServiceImpl.locale(RUSSIA);
        Assertions.assertEquals(expected, result);
    }

    @Test
    void localeOtherTest(){
        String expected = "Welcome";
        LocalizationServiceImpl localizationServiceImpl = Mockito.spy(LocalizationServiceImpl.class);
        String resultUSA = localizationServiceImpl.locale(USA);
        String resultGermany = localizationServiceImpl.locale(GERMANY);
        String resultBrazil = localizationServiceImpl.locale(BRAZIL);
        Assertions.assertEquals(expected, resultUSA);
        Assertions.assertEquals(expected, resultGermany);
        Assertions.assertEquals(expected, resultBrazil);
    }

}
