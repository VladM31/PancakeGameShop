package telegram.bot.nure.botphonenumberprodject.repository;

import lombok.RequiredArgsConstructor;
import telegram.bot.nure.botphonenumberprodject.entities.User;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class UserPut implements DataBasePut {
    private final UserRepository userRepository;
    private final static int RESULT_SIZE = 20;
    @Override
    public int[] put() {
        int[] result = new int[RESULT_SIZE];

        for (int i = 0; i < RESULT_SIZE; i++) {
            result[i] = userRepository.save(
                    new User((long) i,"000000000" + i, LocalDateTime.now().plusMonths(i), i%5!=4));
        }

        return result;
    }
}
