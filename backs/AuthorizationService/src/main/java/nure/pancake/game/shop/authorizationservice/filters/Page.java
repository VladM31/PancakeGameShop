package nure.pancake.game.shop.authorizationservice.filters;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.function.Function;

@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Page<SC> {
    private Integer page;
    private Integer size;
    private SC sortField;
    private boolean desc;

    public Integer getPageOrDefault(Integer def) {
        return page == null ? def : page;
    }

    public Integer getSizeOrDefault(Integer def) {
        return size == null ? def : size;
    }

    public SC getSortFieldOrDefault(SC def) {
        return sortField == null ? def : sortField;
    }

    public Pageable toPageable(Integer defPage, Integer defSize, SC defSortColumn) {
        return this.toPageable(defPage, defSize, defSortColumn, Object::toString);
    }

    public Pageable toPageable(Integer defPage, Integer defSize, SC defSortColumn, Function<SC, String> toString) {
        return PageRequest.of(
                getPageOrDefault(defPage),
                getSizeOrDefault(defSize),
                Sort.by(
                        isDesc() ? Sort.Direction.DESC : Sort.Direction.ASC,
                        toString.apply(getSortFieldOrDefault(defSortColumn))));
    }
}

