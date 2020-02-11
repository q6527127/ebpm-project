package com.ebpm.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ebpm.common.bean.LoginByPasswordIVO;
import com.ebpm.common.bean.LoginByPasswordOVO;
import com.ebpm.common.bean.Result;

@RestController
public class LoginController {
    @PostMapping(value = "/login/loginByPassword")
    Result<LoginByPasswordOVO> loginByPassword(@RequestBody LoginByPasswordIVO ivo) {
        LoginByPasswordOVO ovo = new LoginByPasswordOVO();
        ovo.setPassword(ivo.getPassword());
        ovo.setUserName(ivo.getUserName());
        ovo.setTelephone("180****0000");
        ovo.setUserId("1001");
        Result<LoginByPasswordOVO> result = new Result<LoginByPasswordOVO>();
        result.setData(ovo);
        return result;
    }
}
