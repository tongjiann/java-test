package com.xiw.photo.util;

import cn.hutool.core.io.FileUtil;
import com.xiw.photo.constant.Constant;
import com.xiw.photo.exception.SelfException;
import org.apache.commons.lang3.StringUtils;

import java.io.File;

public class SelfUtil {

    /**
     * 判断地址是否为目录并返回目录
     */
    public static File checkAndGetDir(String dir) {
        if (StringUtils.isBlank(dir) || !FileUtil.isDirectory(dir)) {
            throw new SelfException("【" + dir + "】目录非法");
        }
        return new File(dir);
    }

    public static File checkAndGetDir(File file) {
        if (!FileUtil.isDirectory(file)) {
            return null;
        }
        return file;
    }

    /**
     * 获取工作区
     */
    public static File getWorkDir() {
        String workDir = Constant.WORK_DIR;
        if (!FileUtil.exist(workDir) || !FileUtil.isDirectory(workDir)) {
            throw new SelfException("当前工作区【" + workDir + "】异常");
        }
        return new File(workDir);
    }

    public static File mkdir(File file) {
        boolean mkdir = file.mkdir();
        if (!mkdir) {
            throw new SelfException("创建【" + Constant.CURRENT_PHOTO_PROJECT_NAME + "】，失败");
        }
        return file;
    }

    public static File mkdir(String path) {
        return mkdir(new File(path));
    }
}
