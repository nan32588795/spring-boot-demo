package co.jp.amazawa.training.demo.controller;

import co.jp.amazawa.training.demo.auth.JwtHelper;
import co.jp.amazawa.training.demo.auth.User;
import co.jp.amazawa.training.demo.dto.project.LoginResponse;
import co.jp.amazawa.training.demo.dto.project.RefreshTokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @Autowired
    private JwtHelper jwtHelper;

    @PostMapping("/login")
    public LoginResponse login() {
        User user = new User("aaa", "amazawa", "xxx@gmail.com"); // TODO DBアクセス、パスワード照合など省略
        String accessToken = jwtHelper.generateAccessToken(user);
        String refreshToken = jwtHelper.generateRefreshToken(user);
        // TODO refreshTokenをストレージに書き込み
        return new LoginResponse(accessToken, refreshToken);
    }

    @PostMapping("/refresh-token")
    public RefreshTokenResponse refreshToken(@RequestHeader("refreshToken") String refreshToken) {
        User user = jwtHelper.verifyRefreshToken(refreshToken);
        String accessToken = jwtHelper.generateAccessToken(user);
        // TODO ストレージを更新する？しない？
        return new RefreshTokenResponse(accessToken);
    }

}
