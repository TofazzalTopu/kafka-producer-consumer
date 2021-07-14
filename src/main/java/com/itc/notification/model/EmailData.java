package com.info.incentive.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Md. Tofazzal Hossain\nDate : 07-07-2021
 */

@Data
@NoArgsConstructor
public class EmailData implements Serializable {

    private static final long serialVersionUID = 1L;

    private String appName;
    private String email;
    private String message;

}
