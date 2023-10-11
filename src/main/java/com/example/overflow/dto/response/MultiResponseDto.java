package com.example.overflow.dto.response;

import com.example.overflow.dto.response.PageInfo;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
public class MultiResponseDto<T> {
    private List<T> result;
    private PageInfo pageInfo;

    public MultiResponseDto(List<T> result, Page page) {
        this.result = result;
        this.pageInfo = new PageInfo(page.getNumber() + 1,
                page.getSize(), page.getTotalElements(), page.getTotalPages());
    }

    
}