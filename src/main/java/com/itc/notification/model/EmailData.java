package com.itc.notification.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Md. Tofazzal Hossain\nDate : 07-07-2021
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailData implements Serializable{

    private static final long serialVersionUID = 1744050117179344127L;

    private String appName;
    private String email;
    private String message;

}
