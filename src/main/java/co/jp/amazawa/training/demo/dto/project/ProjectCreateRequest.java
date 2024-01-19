package co.jp.amazawa.training.demo.dto.project;

import jakarta.validation.constraints.Pattern;

public record ProjectCreateRequest(
        @Pattern(regexp = "^[a-zA-Z0-9]{1,8}$", message = "英数字1文字以上8文字以下")
        String name
) {
}
