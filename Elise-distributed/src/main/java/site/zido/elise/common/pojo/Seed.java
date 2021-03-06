package site.zido.elise.common.pojo;

import site.zido.elise.DistributedTask;
import site.zido.elise.Page;
import site.zido.elise.Request;
import site.zido.elise.Task;

/**
 * 种子，信息载体
 *
 * @author zido
 */
public class Seed {
    private DistributedTask task;
    private Request request;
    private Page page;

    public Seed(DistributedTask task, Request request, Page page) {
        this.task = task;
        this.request = request;
        this.page = page;
    }

    public Seed() {

    }

    public DistributedTask getTask() {
        return task;
    }

    public Seed setTask(DistributedTask task) {
        this.task = task;
        return this;
    }

    public Request getRequest() {
        return request;
    }

    public Seed setRequest(Request request) {
        this.request = request;
        return this;
    }

    public Page getPage() {
        return page;
    }

    public Seed setPage(Page page) {
        this.page = page;
        return this;
    }
}
