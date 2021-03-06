package site.zido.elise.pipeline;

import site.zido.elise.ResultItem;
import site.zido.elise.Task;

/**
 * 结果输出处理器
 *
 * @author zido
 */
public interface Pipeline {
    /**
     * 结果处理
     *
     * @param resultItem 结果集
     * @param task       任务
     */
    void process(ResultItem resultItem, Task task);
}
