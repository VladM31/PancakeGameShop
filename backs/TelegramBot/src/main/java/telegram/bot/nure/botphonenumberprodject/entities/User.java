package telegram.bot.nure.botphonenumberprodject.entities;

import lombok.*;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(exclude = "dateRegistration")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long chatId;
    private String phoneNumber;
    private LocalDateTime dateRegistration;
    private boolean active;
}
