package site.hyxy.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@Slf4j
public class ShellUtil {
    private static final ExecutorService pool = Executors.newFixedThreadPool(2);

    /**
     * 执行脚本
     * @param command 要执行的命令
     * @return 执行结果
     * @throws IOException IOException
     * @throws InterruptedException
     */
    public static ShellResult run(String[] command) throws IOException, InterruptedException {

        log.info("running command: " + Arrays.stream(command).collect(Collectors.joining(" ")));
        Process process = Runtime.getRuntime().exec(command);
        ShellResult result = new ShellResult();

        pool.submit(new ReadStreamRunner(process.getInputStream(), result.getSuccessMessage()));
        pool.submit(new ReadStreamRunner(process.getErrorStream(), result.getErrorMessage()));

        result.setCode(process.waitFor());
        return result;
    }

    /**
     * 读取脚本执行结果
     */
    static class ReadStreamRunner implements Runnable {
        InputStream is;
        List result;

        ReadStreamRunner(InputStream is, List result) {
            this.is = is;
            this.result = result;
        }

        public void run() {
            if (is == null) {
                log.warn("ReadStreamRunner InputStream is null");
                return;
            }
            BufferedReader br = null;
            InputStreamReader isr = null;
            try {
                isr = new InputStreamReader(is);
                br = new BufferedReader(isr);
                String line = null;
                while ( (line = br.readLine()) != null) {
                    result.add(line);
                }

            } catch (IOException ioe) {
                ioe.printStackTrace();
            } finally {
                try {
                    is.close();
                    if (br != null) {
                        br.close();
                    }
                    if (isr != null) {
                        isr.close();
                    }
                } catch (IOException e) {
                    log.warn("close stream failed");
                }
            }
        }
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class ShellResult {
        private int code;
        private List<String> successMessage = new ArrayList<>();
        private List<String> errorMessage = new ArrayList<>();

        public void addSuccessMsg(String msg) {
            successMessage.add(msg);
        }

        public void addErrorMsg(String msg) {
            errorMessage.add(msg);
        }
    }
}
