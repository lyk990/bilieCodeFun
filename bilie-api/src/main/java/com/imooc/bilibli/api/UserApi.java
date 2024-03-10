package com.imooc.bilibli.api;

import com.imooc.bilibili.domain.JsonResponse;
import com.imooc.bilibili.domain.User;
import com.imooc.bilibili.service.UserService;
import com.imooc.bilibili.service.util.RSAUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApi {

    @Autowired
    private UserService userService;

    @GetMapping("/rsa-pks")
    public JsonResponse<String> getRsaPublickKey() {
        String pk = RSAUtil.getPublicKeyStr();
        return new JsonResponse<>(pk);

    }

    @PostMapping("/users")
    public JsonResponse<String> addUser(@RequestBody User user) {
        userService.addUser(user);
    }
}
