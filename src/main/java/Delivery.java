import enums.CargoSize;
import enums.LoadingDeliveryService;

public class Delivery {

    public static double getCostDelivery(
            int distance,
            CargoSize cargoSize,
            boolean isFragile,
            LoadingDeliveryService loadingDeliveryService
    ){
        double minSum = 400;
        double result = 0;

        if(cargoSize == null) throw new NullPointerException("cargoSize недолжен быть null");
        if(loadingDeliveryService == null) throw new NullPointerException("loadingDeliveryService не должен быть null");
        if(isFragile && distance >= 30) throw new IllegalArgumentException("Хрупкие грузы нельзя возить на расстояние более 30 км");
        if(distance <= 0) throw new IllegalArgumentException("Дистанция должна быть больше 0");

        if (distance < 2) result += 50;
        else if(distance < 10) result += 100;
        else if(distance < 30) result += 200;
        else result += 300;

        switch (cargoSize){
            case BIG:
                result += 200;
                break;
            case SMALL:
                result += 100;
                break;
        }

        if(isFragile) result += 300;

        switch (loadingDeliveryService){
            case VERY_HIGH:
                result *= 1.6;
                break;
            case HIGH:
                result *= 1.4;
                break;
            case AVERAGE:
                result *= 1.2;
                break;
        }

        return Math.max(result, minSum);

    }
}


