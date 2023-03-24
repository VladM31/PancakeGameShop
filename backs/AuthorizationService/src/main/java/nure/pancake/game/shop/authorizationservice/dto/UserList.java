package nure.pancake.game.shop.authorizationservice.dto;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nure.pancake.game.shop.authorizationservice.dataobjects.Role;
import nure.pancake.game.shop.authorizationservice.dataobjects.UserState;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.lang.NonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserList {
    private List<UserDto> items;
    private Integer size;
    private Integer number;
    private Integer totalList;
    private String sortColumn;
    private boolean desc;


    public UserList(@NonNull Page<UserDto> pages) {
        this(pages.stream().collect(Collectors.toList()),
                pages.getSize(),
                pages.getNumber(),
                pages.getTotalPages(),
                pages.getSort());
    }

    public UserList(@NonNull List<UserDto> items, Integer size, Integer number, Integer totalList, Sort sort) {
        if (sort != null) {
            sort.stream().findFirst().ifPresent(o -> {
                this.setDesc(Sort.Direction.DESC.equals(o.getDirection()));
                this.setSortColumn(o.getProperty());
            });
        }
        this.size = size;
        this.number = number;
        this.totalList = totalList;
        this.items = items;
    }

    public UserList sortColumn(String sortColumn) {
        this.sortColumn = sortColumn;
        return this;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserDto {
        private Long id;
        private String phoneNumber;
        private String nickname;
        private String firstName;
        private String lastName;
        private LocalDate birthDate;
        private Boolean active;
        private String selectedCurrency;
        private String userState;
        private String email;
        private LocalDateTime dateRegistration;
        private String role;
    }
}
