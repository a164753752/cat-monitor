package com.oriental.finance.cat.monitor.dal.plugins;

import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.JavaFormatter;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.CompilationUnit;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：
 * <p>
 * #
 * </p>
 * User: lyd Date: 2016/7/7 ProjectName:cat-monitor Version: 1.0
 */
public class ModelAndMapperPlugin extends PluginAdapter {

    @Override
    public boolean validate(List<String> warnings) {
        System.out.println("不校验....");
        return false;
    }

    @Override
    public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {
        JavaFormatter javaFormatter = context.getJavaFormatter();
        List<GeneratedJavaFile> mapperJavaFiles = new ArrayList<GeneratedJavaFile>();
        List<GeneratedJavaFile> generatedJavaFileList = introspectedTable.getGeneratedJavaFiles();
        for(GeneratedJavaFile generatedJavaFile : generatedJavaFileList){
            CompilationUnit compilationUnit = generatedJavaFile.getCompilationUnit();
            compilationUnit.addFileCommentLine("xxxxxxxxxxxxx");
        }
        return super.contextGenerateAdditionalJavaFiles(introspectedTable);
    }
}
