package site.zido.elise;

import site.zido.elise.configurable.ConfigurableModelExtractor;
import site.zido.elise.configurable.DefRootExtractor;
import site.zido.elise.extractor.ModelExtractor;

/**
 * 可用于分布式传输的task
 * <br>
 * 用作信息载体，仅携带必要信息
 *
 * @author zido
 */
public class DistributedTask implements ExtractorTask {
    private Long id;
    private Site site;
    private DefRootExtractor defExtractor;

    public DistributedTask() {

    }

    public DistributedTask(ExtractorTask task) {
        this.id = task.getId();
        this.site = task.getSite();
        ModelExtractor modelExtractor = task.modelExtractor();
        if (modelExtractor != null) {
            if (modelExtractor instanceof ConfigurableModelExtractor) {
                this.defExtractor = ((ConfigurableModelExtractor) modelExtractor).getDefRootExtractor();
            }
        }
    }

    public DistributedTask(Long id, Site site, DefRootExtractor extractor) {
        this.id = id;
        this.site = site;
        this.defExtractor = extractor;
    }

    @Override
    public Long getId() {
        return id;
    }

    public DistributedTask setId(Long id) {
        this.id = id;
        return this;
    }

    @Override
    public Site getSite() {
        return site;
    }

    public DistributedTask setSite(Site site) {
        this.site = site;
        return this;
    }

    @Override
    public ModelExtractor modelExtractor() {
        return new ConfigurableModelExtractor(defExtractor);
    }

    public DistributedTask setDefExtractor(DefRootExtractor defExtractor) {
        this.defExtractor = defExtractor;
        return this;
    }

    public DefRootExtractor getDefExtractor() {
        return defExtractor;
    }
}
