package life.cch.community.community.provider;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.http.HttpMethodName;
import com.qcloud.cos.model.*;
import com.qcloud.cos.region.Region;
import com.tencent.cloud.CosStsClient;
import life.cch.community.community.exception.CustomizeErrorCode;
import life.cch.community.community.exception.CustomizeException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.TreeMap;
import java.util.UUID;

/**
 * Created by codedrinker on 2020-01-08 17:04:06
 */
@Service
public class TCloudProvider {

    @Value("${tcloud.ufile.secretId}")
    private String secretId;
    @Value("${tcloud.ufile.secretKey}")
    private String secretKey;
    @Value("${tcloud.ufile.durationSeconds}")
    private Integer durationSeconds;
    @Value("${tcloud.ufile.bucket}")
    private String bucket;
    @Value("${tcloud.ufile.region}")
    private String region;
    @Value("${tcloud.ufile.allowPrefix}")
    private String allowPrefix;
    @Value("${tcloud.ufile.baseUrl}")
    private String baseUrl;


    /**
     * 上传文件
     *
     * @param fileName 文件服务器下的根路径,即key,如: doc/picture.jpg
     * @param fileStream
     * @param contentType
     * @return 成功返回文件路径,失败返回null
     */
    public String upload(String fileName, InputStream fileStream, String contentType,Integer fileSize){
        //首先获取临时密匙
        JSONObject temp = getTempKey();
        String tmpSecretId = temp.getJSONObject("credentials").getString("tmpSecretId");
        String tmpSecretKey = temp.getJSONObject("credentials").getString("tmpSecretKey");
        String sessionToken = temp.getJSONObject("credentials").getString("sessionToken");

        // 1 初始化用户身份信息（secretId, secretKey）。
        COSCredentials cred = new BasicCOSCredentials(tmpSecretId, tmpSecretKey);
        // 2 设置 bucket 的区域
        ClientConfig clientConfig = new ClientConfig(new Region(region));
        // 3 生成 cos 客户端。
        COSClient cosClient = new COSClient(cred, clientConfig);
        // 指定要上传到的存储桶
        String bucketName = bucket;

        String key = UUID.randomUUID().toString()+fileName;
        // 设置 x-cos-security-token header 字段
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setSecurityToken(sessionToken);
        objectMetadata.setContentType(contentType);
        objectMetadata.setContentLength(fileSize);
        // 上传 object, 建议 20M 以下的文件使用该接口
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName,key,fileStream,objectMetadata);
        String rtValue = null;
        try {
            PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
            // 成功：putobjectResult 会返回文件的 etag
            if(putObjectResult != null && putObjectResult.getETag()!=null){
                /*GeneratePresignedUrlRequest req = new GeneratePresignedUrlRequest(bucketName,key, HttpMethodName.GET);
                Date expirationTime = new Date(System.currentTimeMillis() + 3L * 60 * 60 * 1000);
                req.setExpiration(expirationTime);
                URL url = cosClient.generatePresignedUrl(req);*/
                rtValue = baseUrl + key;
            }else {
                throw new CustomizeException(CustomizeErrorCode.FILE_UPLOAD_FAIL);
            }

        }catch (CosServiceException e) {
            //失败，抛出 CosServiceException
            throw new CustomizeException(CustomizeErrorCode.FILE_UPLOAD_FAIL);
        } catch (CosClientException e) {
            //失败，抛出 CosClientException
            throw new CustomizeException(CustomizeErrorCode.FILE_UPLOAD_FAIL);
        }finally {
            // 关闭客户端
            cosClient.shutdown();
            //返回文件的网络访问url
            return rtValue;
        }
    }

    public JSONObject getTempKey(){
        TreeMap<String, Object> config = new TreeMap<String, Object>();
        try {
            // 替换为您的 SecretId
            config.put("SecretId", secretId);
            // 替换为您的 SecretKey
            config.put("SecretKey", secretKey);

            // 临时密钥有效时长，单位是秒，默认1800秒，最长可设定有效期为7200秒
            config.put("durationSeconds", durationSeconds);

            // 换成您的 bucket
            config.put("bucket", bucket);
            // 换成 bucket 所在地区
            config.put("region", region);

            // 这里改成允许的路径前缀，可以根据自己网站的用户登录态判断允许上传的具体路径，例子：a.jpg 或者 a/* 或者 * 。
            config.put("allowPrefix", allowPrefix);

            // 密钥的权限列表。简单上传、表单上传和分片上传需要以下的权限
            String[] allowActions = new String[] {
                    // 简单上传
                    "name/cos:PutObject",
                    // 表单上传、小程序上传
                    "name/cos:PostObject",
                    // 分片上传
                    "name/cos:InitiateMultipartUpload",
                    "name/cos:ListMultipartUploads",
                    "name/cos:ListParts",
                    "name/cos:UploadPart",
                    "name/cos:CompleteMultipartUpload"
            };
            config.put("allowActions", allowActions);

            JSONObject credential = CosStsClient.getCredential(config);
            //成功返回临时密钥信息，如下打印密钥信息
            //System.out.println(credential);
            return credential;
        } catch (Exception e) {
            //失败抛出异常
            throw new IllegalArgumentException("no valid secret !");
        }
    }
}
