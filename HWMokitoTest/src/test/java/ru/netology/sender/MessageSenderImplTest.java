package ru.netology.sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationServiceImpl;
import java.util.HashMap;
import java.util.Map;
import static ru.netology.entity.Country.RUSSIA;
import static ru.netology.entity.Country.USA;
import static ru.netology.geo.GeoServiceImpl.MOSCOW_IP;
import static ru.netology.geo.GeoServiceImpl.NEW_YORK_IP;
import static ru.netology.sender.MessageSenderImpl.IP_ADDRESS_HEADER;

public class MessageSenderImplTest {

    @Test
    void sendRussianMessageTest(){
        String expectedMessageToRussia = "Добро пожаловать";
        Map<String, String> headers = new HashMap<>();
        headers.put(IP_ADDRESS_HEADER, MOSCOW_IP);

        Location moscow = new Location("Moscow", RUSSIA, "Lenina", 15);
        LocalizationServiceImpl localizationService = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationService.locale(RUSSIA)).thenReturn(expectedMessageToRussia);

        GeoServiceImpl geoService = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoService.byIp(MOSCOW_IP)).thenReturn(moscow);

        MessageSenderImpl messageSender = Mockito.spy(new MessageSenderImpl(geoService,localizationService));
        String message = messageSender.send(headers);
        Assertions.assertEquals(expectedMessageToRussia, message);
    }

    @Test
    void sendUSAMessageTest(){
        String expectedMessageToUSA = "Welcome";
        Map<String, String> headers = new HashMap<>();
        headers.put(IP_ADDRESS_HEADER, NEW_YORK_IP);

        Location newYork = new Location("New York", USA, " 10th Avenue", 32);
        LocalizationServiceImpl localizationService = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationService.locale(USA)).thenReturn(expectedMessageToUSA);

        GeoServiceImpl geoService = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoService.byIp(NEW_YORK_IP)).thenReturn(newYork);

        MessageSenderImpl messageSender = Mockito.spy(new MessageSenderImpl(geoService,localizationService));
        String message = messageSender.send(headers);
        Assertions.assertEquals(expectedMessageToUSA, message);
    }

    @Test
    void sendThrowsExceptionTest(){
        Map<String, String> headers = new HashMap<>();
        headers.put(null, null);
        LocalizationServiceImpl localizationService = Mockito.mock(LocalizationServiceImpl.class);
        GeoServiceImpl geoService = Mockito.mock(GeoServiceImpl.class);
        MessageSenderImpl messageSender = Mockito.spy(new MessageSenderImpl(geoService,localizationService));
        Assertions.assertThrows(NullPointerException.class, () -> messageSender.send(headers));
    }
}
