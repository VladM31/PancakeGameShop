package nure.pancake.game.shop.authorizationservice.dataobjects.telegram;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TextStyle {
    private Integer length;
    private Integer offset;
    private String type;
    private String url;
}
