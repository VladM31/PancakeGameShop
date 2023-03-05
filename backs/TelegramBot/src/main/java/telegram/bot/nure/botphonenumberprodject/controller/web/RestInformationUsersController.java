package telegram.bot.nure.botphonenumberprodject.controller.web;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import telegram.bot.nure.botphonenumberprodject.filter.UserUniqueFieldFilter;
import telegram.bot.nure.botphonenumberprodject.service.UserService;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

@Validated
@RestController
@RequestMapping("/information-user/v1")
@RequiredArgsConstructor
public class RestInformationUsersController {
    private final UserService serviceUser;

    @ResponseBody
    @RequestMapping(value = "/has-number", method = RequestMethod.GET)
    public boolean isConfidentNumber(@RequestParam @Valid @Pattern(regexp = "\\d{10,15}", message = "Number not correct") String phoneNumber) {
        return serviceUser.findOne(UserUniqueFieldFilter.builder().phoneNumber(phoneNumber).build()).isPresent();
    }
}
