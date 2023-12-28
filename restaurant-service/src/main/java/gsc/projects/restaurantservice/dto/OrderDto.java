package gsc.projects.restaurantservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.UUID;

@Builder
@Getter
@Setter
public class OrderDto {

    private Long id;

    private UUID uuidOrder;

    private Map<String, Double> foodAndQuantity;
}
