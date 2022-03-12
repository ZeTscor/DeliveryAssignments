import enums.CargoSize;
import enums.LoadingDeliveryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

public class DeliveryTest {
    @DisplayName("Test Delivery.getDeliveryCost Arguments(fragile=true, distance >30)  Exception ")
    @Tag("negative")
    @Test
    public void fragileExceptionTest() {
        IllegalArgumentException fragileException =
                Assertions.assertThrows(IllegalArgumentException.class, () -> Delivery.getDeliveryCost(30, CargoSize.SMALL, true, LoadingDeliveryService.AVERAGE));
        Assertions.assertEquals(fragileException.getMessage(), "Хрупкие грузы нельзя возить на расстояние более 30 км");
    }

    @DisplayName("Test Delivery.getDeliveryCost Argument(distance < 0)  Exception")
    @Tag("negative")
    @ValueSource(ints = {-1, 0})
    @ParameterizedTest
    public void distanceExceptionTest(int distance) {
        IllegalArgumentException distanceException =
                Assertions.assertThrows(IllegalArgumentException.class, () -> Delivery.getDeliveryCost(distance, CargoSize.SMALL, false, LoadingDeliveryService.AVERAGE));
        Assertions.assertEquals(distanceException.getMessage(), "Дистанция должна быть больше 0");
    }

    @DisplayName("Test Delivery.getDeliveryCost Argument(loadingDeliveryService=null)  NullException ")
    @Tag("negative")
    @Test
    public void loadingNullExceptionTest() {
        NullPointerException loadingException =
                Assertions.assertThrows(NullPointerException.class, () -> Delivery.getDeliveryCost(30, CargoSize.SMALL, false, null));
        Assertions.assertEquals(loadingException.getMessage(), "loadingDeliveryService не должен быть null");
    }

    @DisplayName("Test Delivery.getDeliveryCost Argument(cargoSize=null)  NullException ")
    @Tag("negative")
    @Test
    public void cargoNullExceptionTest() {
        NullPointerException cargoException =
                Assertions.assertThrows(NullPointerException.class, () -> Delivery.getDeliveryCost(30, null, false, LoadingDeliveryService.AVERAGE));
        Assertions.assertEquals(cargoException.getMessage(), "cargoSize недолжен быть null");
    }

    @DisplayName("Test Delivery.getDeliveryCost Arguments(resources = TestData.csv)  PASS ")
    @Tag("positive")
    @CsvFileSource(resources = "TestData.csv", numLinesToSkip = 1)
    @ParameterizedTest
    public void getCostDelivery(int expectedCost, int distance, String cargoSize, boolean fragile, String workloadDelivery) {
        Assertions.assertEquals(expectedCost, Delivery.getDeliveryCost(distance, CargoSize.valueOf(cargoSize), fragile, LoadingDeliveryService.valueOf(workloadDelivery)));

    }
}
