package nure.pancake.game.shop.gameproductservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DownloadGameRequest {
    @NotNull(message = "Game id mustn't be null")
    private Long gameId;
    @NotBlank(message = "Platform mustn't be blank")
    private String platform;
}
