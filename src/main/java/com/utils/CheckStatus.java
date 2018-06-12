package com.utils;

public class CheckStatus {

    public CheckStatus() {

    }

    public String getStatus(int status){
        if (status == 1)
            return "管理员";
        else if (status == 2)
            return "审核员";
        else
            return "员工";
    }
}
