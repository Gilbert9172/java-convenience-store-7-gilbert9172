package store.controller;

import java.time.LocalDateTime;
import java.util.List;
import store.model.dto.PreOrderDTO;
import store.service.ConvenienceService;

public class ConvenienceController {

    private final ConvenienceService convenienceService;

    public ConvenienceController(final ConvenienceService convenienceService) {
        this.convenienceService = convenienceService;
    }

    public void run() {

        PreOrderDTO preOrderDTO1 = PreOrderDTO.of("콜라", 10, LocalDateTime.now());
        PreOrderDTO preOrderDTO2 = PreOrderDTO.of("오렌지주스", 1, LocalDateTime.now());
        List<PreOrderDTO> orderDTOS = List.of(preOrderDTO1, preOrderDTO2);

        convenienceService.generateOrders(orderDTOS);

    }
}
