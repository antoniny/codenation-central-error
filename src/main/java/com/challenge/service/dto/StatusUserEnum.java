package com.challenge.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum  StatusUserEnum {

    ACTIVE("S","Active") ,
    INACTIVE("N","Inactive"),
    UNKNOWN("N" , "N/A")
    ;

    private String flag;
    private String Description;


    public static StatusUserEnum fromId(String flag) {

        for (StatusUserEnum es : StatusUserEnum.values()) {
            if (es.flag.equals(flag.toUpperCase().trim())) {
                return es;
            }
        }
        return StatusUserEnum.UNKNOWN;
    }

}
