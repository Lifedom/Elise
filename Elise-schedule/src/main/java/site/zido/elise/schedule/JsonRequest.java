package site.zido.elise.schedule;

import site.zido.elise.DistributedTask;
import site.zido.elise.Request;

/**
 * JsonRequest
 *
 * @author zido
 */
public class JsonRequest {
    private DistributedTask task;
    private Request request;

    public DistributedTask getTask() {
        return task;
    }

    public JsonRequest setTask(DistributedTask task) {
        this.task = task;
        return this;
    }

    public Request getRequest() {
        return request;
    }

    public JsonRequest setRequest(Request request) {
        this.request = request;
        return this;
    }
}
