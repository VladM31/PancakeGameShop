package nure.pancake.game.shop.authorizationservice.services;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

import java.util.UUID;
import java.util.random.RandomGenerator;

@Builder
@RequiredArgsConstructor
public class UuidCodeGenerator implements CodeGenerator {
    private final RandomGenerator ran;
    private final int codeLength;

    @Override
    public String create() {
        StringBuilder builder = new StringBuilder();

        do {
            builder.append(UUID.randomUUID().toString().replaceAll("-", ""));
        } while (builder.length() < codeLength);


        ran.ints()
                .limit(Math.round(codeLength * 35.0 / 100.0))
                .map(i -> Math.abs(i % codeLength))
                .distinct()
                .forEach(i -> builder.setCharAt(i, Character.toUpperCase(builder.charAt(i))));


        return builder.substring(0, codeLength);
    }

}
