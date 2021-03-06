package site.zido.elise.common.pojo;

import site.zido.elise.DistributedTask;
import site.zido.elise.Request;
import site.zido.elise.common.SavedPage;

/**
 * SavedSeed
 *
 * @author zido
 */
public class SavedSeed extends Seed {
    private SavedPage savedPage;

    public SavedSeed(DistributedTask task, Request request, SavedPage savedPage) {
        super(task, request, null);
        this.savedPage = savedPage;
    }

    public SavedSeed() {

    }

    public SavedPage getSavedPage() {
        return savedPage;
    }

    public SavedSeed setSavedPage(SavedPage savedPage) {
        this.savedPage = savedPage;
        return this;
    }
}
