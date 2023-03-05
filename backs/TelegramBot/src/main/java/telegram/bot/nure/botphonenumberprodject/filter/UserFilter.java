package telegram.bot.nure.botphonenumberprodject.filter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Collection;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserFilter {
    private Collection<Long> chatIds;
    private String phoneNumber;
    private Boolean active;
    private Range<LocalDateTime> dateOfCreation;
}
