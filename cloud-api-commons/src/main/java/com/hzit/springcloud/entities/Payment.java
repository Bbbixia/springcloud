package com.hzit.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * author biXia
 * create 2020-07-08-10:46
 */


 @Data
 @AllArgsConstructor
 @NoArgsConstructor
public class Payment implements Serializable {
    private Long    id;
    private String  serial;

}
