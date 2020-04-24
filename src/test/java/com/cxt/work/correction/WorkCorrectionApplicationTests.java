package com.cxt.work.correction;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.cxt.work.correction.pojo.TbUser;
import com.cxt.work.correction.utils.QiNiuUtil;
import com.cxt.work.correction.utils.SmsUtil;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *     Junit Test类
 * </p>
 */
@Slf4j
@SpringBootTest
class WorkCorrectionApplicationTests {

    /**
     * 生成代码Test
     */
    @Test
    void generatingCodeTest() {
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        // 输出路径
        globalConfig.setOutputDir("F://code");
        // 设置文件重写
        globalConfig.setFileOverride(true);
        // 不需要ActiveRecord特性的请改为false
        globalConfig.setActiveRecord(true);
        // XML 二级缓存
        globalConfig.setEnableCache(false);
        // XML ResultMap
        globalConfig.setBaseResultMap(true);
        // XML columList
        globalConfig.setBaseColumnList(false);
        globalConfig.setAuthor("xue-sheng-wen");

        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        globalConfig.setMapperName("%sMapper");
        globalConfig.setXmlName("%sMapper");
        globalConfig.setServiceName("%sService");
        globalConfig.setServiceImplName("%sServiceImpl");
        globalConfig.setControllerName("%sController");
        mpg.setGlobalConfig(globalConfig);

        // 数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL);
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("root");
        dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/work-correction-db?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC");
        mpg.setDataSource(dataSourceConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // 全局大写命名 ORACLE 注意
        // strategy.setCapitalMode(true);
        // 此处可以修改为您的表前缀
        strategy.setTablePrefix(new String[]{"tab_"});
        // 表名生成策略 underline_to_camel：强调驼峰
        strategy.setNaming(NamingStrategy.underline_to_camel);
        mpg.setStrategy(strategy);


        // 包配置
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent("com.cxt.work.correction")
                .setController("controller")
                .setService("service")
                .setServiceImpl("service.impl")
                .setMapper("mapper")
                .setEntity("pojo")
                .setXml("mapper");
        mpg.setPackageInfo(packageConfig);

        // 执行生成
        mpg.execute();
    }

    /**
     * 6位随机验证码Test
     */
    @Test
    void randomCodeTest() {
        for (int i = 0; i < 100; i++) {
            String randomCode = String.valueOf((int) ((Math.random() * 9 + 1) * 1000000)).substring(1);
            System.out.println(randomCode);
        }
    }

    /**
     * 阿里云短信服务Test
     */
    @Test
    void sendMessage() {
        String accessKeyId = "LTAIdEa9Jt6OykGv";
        String accessKeySecret = "8lrihtzGw1L8Zg8BAz8RJONFfPpM54";
        String sign_name = "春桎";
        String template_code = "SMS_184830112";
        String mobileNumber = "18193574241";
        JSONObject jsonObject = SmsUtil.sendMessage(mobileNumber, accessKeyId, accessKeySecret, sign_name, template_code);
        System.out.println(jsonObject);
    }

    @Autowired
    private RedisTemplate<String, String> stringRedisTemplate;

    @Autowired
    private RedisTemplate<String, Serializable> serializableRedisTemplate;

    @Test
    void redisTest() {
        stringRedisTemplate.opsForValue().set("薛胜文", "24");
        System.out.println(stringRedisTemplate.opsForValue().get("薛胜文"));

        TbUser user = new TbUser();
        user.setName("xueshengwen");
        user.setPassword("123456");
        serializableRedisTemplate.opsForValue().set("user", user);
        System.out.println(serializableRedisTemplate.opsForValue().get("user"));

        stringRedisTemplate.opsForValue().set("过期测试", "123456", 1, TimeUnit.MINUTES);
        Boolean isKey = stringRedisTemplate.hasKey("过期测试");
        if(isKey){
            System.out.println("存在");
        }
        System.out.println("不存在");
        System.out.println(serializableRedisTemplate.opsForValue().get("过期测试"));
    }

    @Value(value = "${qiniu.accessKey}")
    private String accessKey;

    @Value(value = "${qiniu.secretKey}")
    private String secretKey;

    @Value(value = "${qiniu.bucket}")
    private String bucket;

    @Value(value = "${qiniu.path}")
    private String path;

    @Test
    void uploadFIleTest() throws IOException {
        File file = new File("F:\\Java编程思想第四版\\Java编程思想第四版完整中文高清版(免费).pdf");
        FileInputStream fileInputStream = new FileInputStream(file);
        String name = file.getName();
        QiNiuUtil qiNiuUtil = new QiNiuUtil();
        String result = qiNiuUtil.uploadFile(fileInputStream, name, accessKey, secretKey, bucket, path);
        System.out.println(result);
    }

    @Test
    void dateTest() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String format1 = format.format(date);
        System.out.println(date);
        System.out.println(format1);
    }

    @Test
    void deleteQiNiuYunTest() {
        Auth auth = Auth.create("G0ZUkYgFGpCil33c_hCuDRkg2-GM-852fma-Bj9h", "yASsSwVtrCj3Qe8ZTd66Nr7WBKsSG5B1BHW4g3Kt");
        Configuration config = new Configuration(Zone.autoZone());
        BucketManager bucketMgr = new BucketManager(auth, config);
        // 指定需要删除的文件，和文件所在的存储空间
        String bucketName = "my-documents";
        String key = "0713录用通知书 -薛胜文-研发部.pdf";
        try {
            Response delete = bucketMgr.delete(bucketName, key);
            delete.close();
        }catch (Exception e){
            e.printStackTrace();
            log.error("七牛云删除文件异常");
        }
        System.out.println("结束了");
    }
}
