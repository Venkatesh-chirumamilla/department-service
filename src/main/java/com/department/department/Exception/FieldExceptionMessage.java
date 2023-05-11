package com.department.department.Exception;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FieldExceptionMessage {
    private String field;
    private String message;
}
