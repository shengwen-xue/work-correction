package com.cxt.work.correction.entity.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author xue-sheng-wen
 * @date 2020/3/19 10:24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateBO implements Serializable {

    private Long id;

    private String name;

    private String password;

    private String phoneNumber;

    private String eMail;

    private Long studentNumber;

    @Override
    public String toString() {
        return "UserUpdateBO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", eMail='" + eMail + '\'' +
                ", studentNumber=" + studentNumber +
                '}';
    }
}
