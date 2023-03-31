package nure.pancake.game.shop.authorizationservice.controllers;

import lombok.RequiredArgsConstructor;
import nure.pancake.game.shop.authorizationservice.dto.AuthResponse;
import nure.pancake.game.shop.authorizationservice.mappers.AuthResponseMapper;
import nure.pancake.game.shop.authorizationservice.mappers.AuthResponseUserMapper;
import org.springframework.web.bind.annotation.*;

@ResponseBody
@RestController
@RequestMapping(value = "/auth-token")
@RequiredArgsConstructor
public class AuthTokenController {
    private final AuthResponseUserMapper<String> authResponseUserMapper;
    private final AuthResponseMapper<String> refreshMapper;

    @PostMapping(value = "/token-to-user")
    public AuthResponse.User toUserV1(@RequestHeader("Authorization") String authHeader) {
        return authResponseUserMapper.toAuthResponseUser(authHeader);
    }

    @PostMapping(value = "/refresh-token")
    public AuthResponse refreshTokenV1(@RequestHeader("Authorization") String authHeader) {
        return refreshMapper.toAuthResponse(authHeader);
    }
}
