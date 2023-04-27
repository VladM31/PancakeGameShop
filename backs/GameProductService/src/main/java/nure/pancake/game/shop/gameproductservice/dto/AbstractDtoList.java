package nure.pancake.game.shop.gameproductservice.dto;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@SuperBuilder
public abstract class AbstractDtoList<T> {
    private Long offset;
    private Integer pageNumber;
    private Integer pageSize;
    private Long totalElements;
    private Integer totalPages;
    private List<T> content;

    protected AbstractDtoList(Page<T> pages) {
        this.offset = pages.getPageable().getOffset();
        this.pageNumber = pages.getNumber();
        this.pageSize = pages.getSize();
        this.totalElements = pages.getTotalElements();
        this.totalPages = pages.getTotalPages();
        this.content = pages.getContent();
    }

}
