package com.example.overflow.dto.response;

import com.example.overflow.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberJoinResponseDto {

    private Integer id;
    private String userName;

    public static MemberJoinResponseDto entityToDto(Member member) {
        if (member == null)
            throw new IllegalArgumentException();

        return new MemberJoinResponseDto(
                member.getId(),
                member.getUserName()
        );
    }


}