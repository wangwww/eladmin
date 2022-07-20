package site.hyxy.service.aiui;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.java_websocket.WebSocket;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;
import org.springframework.stereotype.Service;
import site.hyxy.entity.aiui.TTSResult;

import javax.annotation.PostConstruct;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.*;

@Slf4j
@Service("aiuiService")
public class AiuiService {
    // 服务地址
    private static final String BASE_URL = "ws://wsapi.xfyun.cn/v1/aiui";
    private static final String ORIGIN = "http://wsapi.xfyun.cn";

    // 应用ID，见AIUI开放平台
    private static final String APPID = "5c6d456d";
    // APIKEY，见AIUI开放平台
    private static final String APIKEY = "f6fad331cb2b1c2ae7efda07b14599d6";
    // 每帧音频数据大小，单位字节
    private static final int CHUNCKED_SIZE = 1280;
    // 音频文件位置
    private static final String FILE_PATH = "D:\\xyf\\target\\d97fc0cf-6040-4016-bba7-ebe12d8a901f.pcm";
    // 结束数据发送标记（必传）
    private static final String END_FLAG = "--end--";
    // 配置参数
    private static final String param = "{\"result_level\":\"complete\",\"auth_id\":\"894c984bf8b1111c6728db79d3479ae1\",\"data_type\":\"text\",\"aue\":\"raw\",\"scene\":\"main_box\",\"sample_rate\":\"16000\"}";

    private CompletableFuture<TTSResult> aiuiResult;
    
    @PostConstruct
    public void init() {
        log.error("------------------ aiui task 初始化");
    }

    public synchronized TTSResult send(String msg) throws Exception {
        aiuiResult = new CompletableFuture<>();
        URI url = new URI(BASE_URL + getHandShakeParams());
        DraftWithOrigin draft = new DraftWithOrigin(ORIGIN);
        MyWebSocketClient client = new MyWebSocketClient(url, draft, aiuiResult);
        client.connect();
        while (!client.getReadyState().equals(WebSocket.READYSTATE.OPEN)) {
            log.debug("连接中：{}", url);
            Thread.sleep(50);
        }
        // 发送音频
        byte[] bytes = new byte[CHUNCKED_SIZE];

        send(client, msg);
        send(client, END_FLAG);

        try {
            return aiuiResult.get(10, TimeUnit.SECONDS);
        } catch (ExecutionException|TimeoutException e) {
            log.error("获取ttsResult结果失败");
            return null;
        }
    }

    // 拼接握手参数
    private static String getHandShakeParams() throws UnsupportedEncodingException {
        String paramBase64 = new String(Base64.encodeBase64(param.getBytes("UTF-8")));
        String curtime = System.currentTimeMillis() / 1000L + "";
        String signtype = "sha256";
        String originStr = APIKEY + curtime + paramBase64;
        String checksum = getSHA256Str(originStr);
        String handshakeParam = "?appid=" + APPID + "&checksum=" + checksum + "&curtime=" + curtime + "&param=" + paramBase64 + "&signtype=" + signtype;
        return handshakeParam;
    }

    // 发送数据
    private static void send(WebSocketClient client, String msg) {
        System.out.println("发送消息： " + msg);
        if (client.isClosed()) {
            throw new RuntimeException("client connect closed!");
        }
        client.send(msg.getBytes(StandardCharsets.UTF_8));
    }

    // 利用Apache的工具类实现SHA-256加密
    private static String getSHA256Str(String str) {
        MessageDigest messageDigest;
        String encdeStr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hash = messageDigest.digest(str.getBytes("UTF-8"));
            encdeStr = Hex.encodeHexString(hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encdeStr;
    }

    private static class MyWebSocketClient extends WebSocketClient {
        private CompletableFuture<TTSResult> completableFuture;

        public MyWebSocketClient(URI serverUri, Draft protocolDraft, CompletableFuture<TTSResult> completableFuture) {
            super(serverUri, protocolDraft);
            this.completableFuture = completableFuture;
        }

        @Override
        public void onOpen(ServerHandshake handshake) {
            //System.out.println("打开连接, code:" + handshake.getHttpStatusMessage());
        }

        @Override
        public void onMessage(String msg) {
            log.info("接收到AIUI消息: {}", msg);

            TTSResult ttsResult = JSONObject.parseObject(msg, TTSResult.class);

            if (ttsResult.getMAction().equals("result")) {
                completableFuture.complete(ttsResult);
            }
        }

        @Override
        public void onError(Exception e) {
            System.out.println("AIUI连接发生错误：" + e.getMessage() + ", " + new Date());
            e.printStackTrace();
        }

        @Override
        public void onClose(int arg0, String arg1, boolean arg2) {
            System.out.println("AIUI链接已关闭" + "," + new Date());
        }

        @Override
        public void onMessage(ByteBuffer bytes) {
            try {
                String aiuiResultStr = new String(bytes.array(), "utf-8");
                log.info("AIUI服务端返回：" + aiuiResultStr);

                TTSResult ttsResult = JSONObject.parseObject(aiuiResultStr, TTSResult.class);
                
                if (ttsResult.getMAction().equals("result")) {
                    completableFuture.complete(ttsResult);
                }
            } catch (UnsupportedEncodingException e) {
                log.error("解析AIUI技能结果失败");
                completableFuture.complete(null);
            }
        }
    }
}