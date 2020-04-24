package com.cxt.work.correction.entity.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author xue-sheng-wen
 * @date 2020/3/14 10:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBO implements Serializable {

    private String name;

    private String roleName;

    private String eMail;

    private String phoneNumber;

    private Long studentNumber;

    @Override
    public String toString() {
        return "UserBO{" +
                "name='" + name + '\'' +
                ", roleName='" + roleName + '\'' +
                ", eMail='" + eMail + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", studentNumber=" + studentNumber +
                '}';
    }
}
