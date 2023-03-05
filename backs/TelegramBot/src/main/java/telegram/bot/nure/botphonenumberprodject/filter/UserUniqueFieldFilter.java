package telegram.bot.nure.botphonenumberprodject.filter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserUniqueFieldFilter {
    private Long chatId;
    private String phoneNumber;

    public boolean areAllFieldsNull(){
        return this.chatId == null && this.phoneNumber == null;
    }
}
