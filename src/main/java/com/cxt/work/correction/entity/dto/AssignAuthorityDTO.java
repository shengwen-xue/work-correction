package com.cxt.work.correction.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author xue-sheng-wen
 * @date 2020/4/8 19:37
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignAuthorityDTO implements Serializable {

    private Long id;

    private String permissionName;

    @Override
    public String toString() {
        return "AssignAuthorityDTO{" +
                "id=" + id +
                ", permissionName='" + permissionName + '\'' +
                '}';
    }
}
