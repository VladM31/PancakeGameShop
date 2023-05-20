package nure.pancake.game.shop.gameproductservice.controllers;

import lombok.RequiredArgsConstructor;
import nure.pancake.game.shop.gameproductservice.services.PromoCodeService;
import org.springframework.web.bind.annotation.*;

@RestController
@ResponseBody
@RequiredArgsConstructor
@RequestMapping("/promo-code")
public class PromoCodeController {
    private final PromoCodeService codeService;

    @GetMapping("/{code}/discount")
    public int getDiscount(@PathVariable String code){
        return codeService.getDiscountPercentage(code);
    }
}
