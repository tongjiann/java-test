package com.xiw.photo.constant;

import java.util.Arrays;
import java.util.List;

public class Constant {

    public static final String CURRENT_PHOTO_PROJECT_NAME = "230509-西湖";

    /**
     * 待筛选目录
     */
    public static final String TO_PICK_DIR_NAME = "待筛选";


    /**
     * 存放CR3文件的目录
     */
    public static final String CR3_DIR_NAME = "CR3";
    public static final String MOVIE_DIR_NAME = "MOVIE";
    public static final String PICKED_DIR_NAME = "PICKED";

    /**
     * 工作区——最顶级
     */
    public static final String WORK_DIR = "/Users/xiwang/Documents/相册";

    public static final String CR3_SUFFIX = ".cr3";
    public static final String JPG_SUFFIX = ".jpg";
    public static final String MP4_SUFFIX = ".mp4";


    /**
     * 待创建的目录名称列表
     */
    public static final List<String> TO_CREATE_DIRECTORY_NAME_LIST = Arrays.asList("山",
            "水",
            "日落",
            "人像",
            "动物",
            "植物",
            "地标",
            "建筑",
            "天空",
            "自己",
            "杂项",
            "PICKED"
    );

}
