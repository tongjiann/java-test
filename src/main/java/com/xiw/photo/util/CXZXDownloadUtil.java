package com.xiw.photo.util;

import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.http.HttpUtil;

public class CXZXDownloadUtil {
    public static void download(String filePath, String pageNo) {
        String url = CharSequenceUtil.format("https://mirrorxz.cxstar.com/newcxstarapi/api/OnlineEBook?filePath={}&readtype=pdf&pageno={}", filePath, pageNo);
        String desFilePath = CharSequenceUtil.format("/Users/xiwang/Downloads/book/tmp_{}.pdf", pageNo);
        HttpUtil.downloadFile(url, desFilePath);
        System.out.println("下载第" + pageNo + "页成功");
    }

    public static void main(String[] args) {
        String filePath = "a06010978020101ccaaffdd338f0a848ba4b052fd42a82d49788ab24a5a90f352a7b5d2cada0dd01686abea07098a1764153fdc3fb7c94ffb4c169f13c07c4622705940f8e1288e323f4fdc4e22a12c2f1c29cafb553466a0a51236f266da12d2b2710f944228062c88b86276cf5ce145f7364864cf0af2e72257bd61b64c79ab4abba0666f7baa05d57b363620a681861b9ee5aa87aec0c010708d3d4f7035f9b4c4207623918358fb8271ba46becce92d238c1ceec9d9a8449c7276ef37208d338f9c7a3e45ac5e95e8bebd9d118e78d6e854f773d71ba1884975a0a24b7cbc661c2d6c1a5d21f4bbe8a1cf95d349f21318684d1f55316473083b89da079721ae7bdf4b0f1268de1ce94055d9b381f5b3f2197222dee9cb7912fe2d7d7a2fd77eebe5bbce2bb2cfc68dacbcdea461f846e745bc358e79bb45c475ce0f5bb458bfb5c7ad53f2a096a963cf3db3b27bbb45c475ce0f5bb406e4a0a55be3dabc20f4c9811be7a5e09aaff5171f308bd48d4c8d3778b3131d24375d1e0fd3d6d4996b667fd3b31d33";
        for (int i = -1; i < 0; i++) {
            download(filePath, String.valueOf(i + 1));
        }
    }
}
