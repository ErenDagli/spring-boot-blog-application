package com.dagli.springboot.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
        description = "CommentDto Model Information"
)
public class CommentDto {
    private long id;
    //name should not be null or empty
    @Schema(
            description = "Blog Comment Name"
    )
    @NotEmpty(message = "Name should not be null or empty")
    private String name;

    // email should not be null or empty
    // email field validation
    @Schema(
            description = "Blog Comment Email"
    )
    @Email
    @NotEmpty(message = "Email should not be null or empty")
    private String email;

    // body should not be null or empty
    // body must be minimum 10 characters
    @Schema(
            description = "Blog Comment Body"
    )
    @NotEmpty
    @Size(min = 10,message = "body must be minimum 10 characters")
    private String body;
}
