package com.hnqc.ironhand.spider.distributed.pipeline;

import com.hnqc.ironhand.spider.Task;

import java.util.Map;

/**
 * 类page model处理器
 *
 * @author zido
 * @date 2018/04/12
 */
public class ClassPageModelPipeline<T> implements PageModelPipeline {
    private Class<T> classzz;

    public ClassPageModelPipeline(Class<T> classzz) {
        this.classzz = classzz;
    }

    @Override
    public void process(Map<String, Object> t, Task task) {
        //TODO map转化为class实例
    }
}
