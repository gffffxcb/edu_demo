package com.mgh.cms.entity.vo;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;

/**
 * @author MGH
 * @create 2022-0216 7:21 下午
 */
@Data
@ToString
public class OneSubject {
    private String id;
    private String title;
    private ArrayList<TwoSubject> children;
}
