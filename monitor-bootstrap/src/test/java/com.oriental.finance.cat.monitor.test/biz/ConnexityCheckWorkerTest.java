package com.oriental.finance.cat.monitor.test.biz;

import com.oriental.finance.cat.monitor.work.check.helper.ConnexityCheckHelper;
import com.oriental.finance.cat.monitor.test.BaseTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：连通性检测测试
 * <p>
 * #
 * </p>
 * User: lyd Date: 2016/7/12 ProjectName:cat-monitor Version: 1.0
 */
public class ConnexityCheckWorkerTest extends BaseTest{

    /**
     * 连通性检测Helper
     */
    @Autowired
    private ConnexityCheckHelper connexityCheckHelper;

    /**
     * 模拟检测失败返回内容
     */
    private static final String abnormalResult = "<html>\n" +
            "<head>\n" +
            "<meta http-equiv=\"Content-Type\" content=\"text/html;charset=utf-8\"/>\n" +
            "<title>Error 404 /cif/cif.jspx</title>\n" +
            "</head>\n" +
            "<body><h2>HTTP ERROR 404</h2>\n" +
            "<p>Problem accessing /cif/cif.jspx. Reason:\n" +
            "<pre>    /cif/cif.jspx</pre></p><hr><a href=\"http://eclipse.org/jetty\">Powered by Jetty:// 9.3.8.v20160314</a><hr/>\n" +
            "\n" +
            "</body>\n" +
            "</html>\n";

    /**
     * 模拟检测正常返回内容
     */
    private static final String normalResult = "SUCCESS";

    /**
     * 检测地址
     */
    private static final String checkUrl = "http://192.168.201.10:8081/cif/cif.jsp";

    /**
     * 检测结果异常集
     */
    private static List<String> abnormalRunResult = new ArrayList<>();
    /**
     * 检测结果正常集
     */
    private static List<String> normalRunResult = new ArrayList<>();

    @Before
    public void before(){
        abnormalRunResult.add(abnormalResult);
        normalRunResult.add(normalResult);
    }

    /**
     * 连通性检测测试
     */
    @Test
    public void test_ConnexityCheck(){
        connexityCheckHelper.handlerCheckResult(connexityCheckHelper.analyze(abnormalRunResult),checkUrl);
        connexityCheckHelper.handlerCheckResult(connexityCheckHelper.analyze(normalRunResult),checkUrl);
    }

    @After
    public void after(){
        lock();
    }

}
