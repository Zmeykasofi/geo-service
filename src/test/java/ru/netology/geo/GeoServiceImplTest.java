package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import static ru.netology.geo.GeoServiceImpl.NEW_YORK_IP;


class GeoServiceImplTest {

    @Test
    void byExactIp() {
        GeoServiceImpl geoServiceImpl = new GeoServiceImpl();
        String exampleIp = "172.164.64.111";
        Location actualLocation = geoServiceImpl.byIp(exampleIp);
        Country expectedCountry = Country.RUSSIA;
        Assertions.assertEquals(expectedCountry, actualLocation.getCountry());
    }

    @Test
    void byIp() {
        GeoServiceImpl geoServiceImpl = new GeoServiceImpl();
        Location actualLocation = geoServiceImpl.byIp(NEW_YORK_IP);
        Country expectedCountry = Country.USA;
        Assertions.assertEquals(expectedCountry, actualLocation.getCountry());
    }
}