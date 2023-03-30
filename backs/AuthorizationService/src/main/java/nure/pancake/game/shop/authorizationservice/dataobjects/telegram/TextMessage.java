package nure.pancake.game.shop.authorizationservice.dataobjects.telegram;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Collection;

@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class TextMessage {
    private String phoneNumber;
    private String text;
    @Singular(ignoreNullCollections = true)
    private Collection<TextStyle> styles;
}
