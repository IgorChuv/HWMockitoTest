package ru.netology.geo;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import org.junit.jupiter.api.Assertions;
import static ru.netology.geo.GeoServiceImpl.MOSCOW_IP;
import static ru.netology.geo.GeoServiceImpl.NEW_YORK_IP;
import static ru.netology.geo.GeoServiceImpl.LOCALHOST;
import static ru.netology.entity.Country.USA;
import static ru.netology.entity.Country.RUSSIA;

public class GeoServiceImplTest {

    @Test
    void byIpMoscowTest(){
        Location expected = new Location("Moscow", RUSSIA, "Lenina", 15);
        GeoServiceImpl geoServiceImpl = Mockito.spy(GeoServiceImpl.class);
        Location result = geoServiceImpl.byIp(MOSCOW_IP);
        Assertions.assertEquals(result.toString(),expected.toString());
    }

    @Test
    void byIpNewYorkTest(){
        Location expected = new Location("New York", USA, " 10th Avenue", 32);
        GeoServiceImpl geoServiceImpl = Mockito.spy(GeoServiceImpl.class);
        Location result = geoServiceImpl.byIp(NEW_YORK_IP);
        Assertions.assertEquals(result.toString(),expected.toString());
    }

    @Test
    void byIpSomeWhereInNewYorkTest(){
        String cityExpected = "New York";
        Country countryExpected = USA;
        GeoServiceImpl geoServiceImpl = Mockito.spy(GeoServiceImpl.class);
        Location result = geoServiceImpl.byIp("96.");
        Assertions.assertEquals(result.getCity(),cityExpected);
        Assertions.assertEquals(result.getCountry(),countryExpected);
    }

    @Test
    void byIpSomeWhereInMoscowTest(){
        String cityExpected = "Moscow";
        Country countryExpected = RUSSIA;
        GeoServiceImpl geoServiceImpl = Mockito.spy(GeoServiceImpl.class);
        Location result = geoServiceImpl.byIp("172.");
        Assertions.assertEquals(result.getCity(),cityExpected);
        Assertions.assertEquals(result.getCountry(),countryExpected);
    }

    @Test
    void byIpThrowsExceptionWithNullArgumentTest(){
        GeoServiceImpl geoServiceImpl = Mockito.spy(GeoServiceImpl.class);
        Assertions.assertThrows(NullPointerException.class, () -> geoServiceImpl.byIp(null));
    }

    @Test
    void byIpLocalHostTest(){
        Location expected = new Location(null, null, null, 0);
        GeoServiceImpl geoServiceImpl = Mockito.spy(GeoServiceImpl.class);
        Location result = geoServiceImpl.byIp(LOCALHOST);
        Assertions.assertEquals(result.toString(),expected.toString());
    }

    @Test
    void byIpUnknownArgumentTest(){
        GeoServiceImpl geoServiceImpl = Mockito.spy(GeoServiceImpl.class);
        Location result = geoServiceImpl.byIp(Mockito.anyString());
        Assertions.assertEquals(null,result);
    }

}
