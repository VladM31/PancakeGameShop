package nure.pancake.game.shop.gameproductservice.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import nure.pancake.game.shop.gameproductservice.dataobjects.User;
import nure.pancake.game.shop.gameproductservice.dto.BuyContentRequest;
import nure.pancake.game.shop.gameproductservice.mappers.BuyContentMapper;
import nure.pancake.game.shop.gameproductservice.services.BuyService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@ResponseBody
@RequiredArgsConstructor
@RequestMapping("/payment")
public class PaymentController {
    private final BuyContentMapper buyContentMapper;

    private final BuyService buyService;
    @PostMapping("/buy")
    public boolean buyContent(@AuthenticationPrincipal User user, @Valid @RequestBody BuyContentRequest request){
        return buyService.buy(buyContentMapper.toBuyContent(request, user.getId()));
    }
}
