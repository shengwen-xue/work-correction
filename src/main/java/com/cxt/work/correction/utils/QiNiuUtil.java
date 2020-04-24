package com.cxt.work.correction.utils;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

import java.io.FileInputStream;

/**
 * <p>
 * 七牛云工具类
 * </p>
 *
 * @author xue-sheng-wen
 * @date 2020/3/21 10:12
 */
public class QiNiuUtil {

    /**
     * 上传文件
     *
     * @param file      需要上传的文件
     * @param key       文件名
     * @param accessKey 公钥
     * @param secretKey 私钥
     * @param bucket    文件目录名
     * @param path      路径
     * @return 上传后的文件路径
     */
    public static String uploadFile(FileInputStream file, String key, String accessKey, String secretKey, String bucket, String path) {
        // 构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone0());
        // 其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        // 生成上传凭证，然后准备上传
        try {
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);
            try {
                Response response = uploadManager.put(file, key, upToken, null, null);
                // 解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                return path + "/" + putRet.key;
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    //ignore
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 删除七牛云文件
     *
     * @param accessKey  公钥
     * @param secretKey  私钥
     * @param bucketName 上传文件目录名
     * @param key        需要删除的文件名
     * @return
     */
    public static String deleteQiNiuYunFile(String accessKey, String secretKey, String bucketName, String key) {
        Auth auth = Auth.create(accessKey, secretKey);
        Configuration config = new Configuration(Zone.autoZone());
        BucketManager bucketMgr = new BucketManager(auth, config);
        try {
            Response delete = bucketMgr.delete(bucketName, key);
            delete.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "文件删除成功";
    }
}
