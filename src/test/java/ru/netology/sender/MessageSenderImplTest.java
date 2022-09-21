package ru.netology.sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.startsWith;


@ExtendWith(MockitoExtension.class)
class MessageSenderImplTest {

    @Mock
    private GeoServiceImpl geoService;

    @Mock
    private LocalizationServiceImpl localizationService;

    @ParameterizedTest
    @ValueSource(strings = {"172.164.64.111", "172.378.65.287", "172.834.99.154", "172.0.0.0"})
    void sendToRussia(String ip) {
        String expectedMessage = "Добро пожаловать";
        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ip);

        Mockito.when(geoService.byIp(startsWith("172.")))
                .thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));
        Mockito.when(localizationService.locale(Mockito.eq(Country.RUSSIA)))
                .thenReturn(expectedMessage);

        Assertions.assertEquals(expectedMessage, messageSender.send(headers));
    }

    @ParameterizedTest
    @ValueSource(strings = {"96.164.64.111", "96.378.65.287", "96.834.99.154", "96.0.0.0"})
    void sendToUsa(String ip) {
        String expectedMessage = "Welcome";
        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ip);

        Mockito.when(geoService.byIp(startsWith("96.")))
                .thenReturn(new Location("New York", Country.USA, null,  0));
        Mockito.when(localizationService.locale(Mockito.eq(Country.USA)))
                .thenReturn(expectedMessage);

        Assertions.assertEquals(expectedMessage, messageSender.send(headers));
    }
}