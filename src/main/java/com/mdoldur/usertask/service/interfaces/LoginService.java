package com.mdoldur.usertask.service.interfaces;

import com.mdoldur.usertask.dto.LoginResultDTO;

public interface LoginService {

    Boolean isLoggedIn(LoginResultDTO loginResultDTO);

}
