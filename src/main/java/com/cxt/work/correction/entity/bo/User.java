package com.cxt.work.correction.entity.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xue-sheng-wen
 * @date 2020/3/28 20:59
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String userName;

    private String password;

    private String eMail;

    private String phoneNumber;

    private Long studentNumber;

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", eMail='" + eMail + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", studentNumber=" + studentNumber +
                '}';
    }
}
