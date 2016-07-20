package com.oriental.finance.cat.monitor.common.util;

import org.apache.commons.lang.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

/**
 * 描述：
 * <p>
 * #
 * </p>
 * User: lyd Date: 2016/7/4 ProjectName:cat-monitor Version:
 */
public class CommandUtils {

    /**
     * 执行shell命令
     * @param command   shell命令
     * @return  执行结果
     * @throws IOException
     */
    public static List<String> runShellReturnList(String command,boolean isWait) throws Exception {
        List<String> result = new LinkedList<>();
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        Process process = null;
        try {
            process = runShell(command);
            if(isWait){
                process.waitFor();
            }
            inputStreamReader = new InputStreamReader(process.getInputStream());
            bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while(null != (line = bufferedReader.readLine()) && !"".equals(line)){
                result.add(line);
            }
        }catch (Exception e){
            throw e;
        }finally {
            close(inputStreamReader,bufferedReader,process);
        }
        return result;
    }

    /**
     * 返回shell执行Process
     * @param command   shell命令
     * @return  Process
     * @throws IOException
     */
    public static Process runShell(String command) throws Exception {
        String[] params = {"/bin/sh","-c",command};
        Runtime runtime = Runtime.getRuntime();
        return runtime.exec(params);
    }

    /**
     * 关闭资源
     * @param inputStreamReader 输入流
     * @param bufferedReader    输入流
     * @param process   处理
     * @throws IOException
     */
    public static void close(InputStreamReader inputStreamReader,
                             BufferedReader bufferedReader,
                             Process process) throws IOException {
        try {
            if (inputStreamReader != null) {
                inputStreamReader.close();
            }
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (process != null) {
                process.destroy();
            }
        }catch (Exception e){
            throw e;
        }

    }
}
