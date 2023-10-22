package com.example.caselabdatabase.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserCriteria {

    private static int PAGE_SIZE = 10;

    private int currentPage;

    private int ageFilter;

    private String usernameFilter;

    private String emailFilter;

    private String isEnableFilter;

    public Map<String, Object> getCriteria() {
        Map<String, Object> criteria = new HashMap<>();
        if (usernameFilter != null) {
            criteria.put("username", usernameFilter);
        }
        if (emailFilter != null) {
            criteria.put("email", emailFilter);
        }
        if (ageFilter != 0) {
            criteria.put("age", ageFilter);
        }
        if (isEnableFilter != null) {
            criteria.put("isEnable", Boolean.valueOf(isEnableFilter));
        }
        return criteria;
    }
}