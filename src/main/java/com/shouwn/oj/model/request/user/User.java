package com.shouwn.oj.model.request.user;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    private String studentNumber;
    private String password;
}
