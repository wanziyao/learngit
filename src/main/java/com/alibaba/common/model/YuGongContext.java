package com.alibaba.common.model;

import javax.sql.DataSource;

import com.alibaba.common.db.meta.Table;
import com.alibaba.common.model.position.Position;
import org.apache.rocketmq.client.producer.DefaultMQProducer;

/**
 * yugong数据处理上下文
 * 
 * @author agapple 2013-9-12 下午5:04:57
 */
public class YuGongContext {

    // 具体每张表的同步
    private Position lastPosition;                  // 最后一次同步的position记录
    private Table tableMeta;                     // 对应的meta
    private boolean    ignoreSchema         = false;  // 同步时是否忽略schema，oracle迁移到mysql可能schema不同，可设置为忽略

    // 全局共享
    private RunMode    runMode;
    private int        onceCrawNum;                   // 每次提取的记录数
    private int        tpsLimit             = 0;      // <=0代表不限制
    private DbType     sourceDbType;                  // 源数据库类型
    private DbType     targetDbType;                  // 目标数据库类型
    private DataSource sourceDs;                      // 源数据库链接
    private DataSource targetDs;                      // 目标数据库链接
    private boolean    batchApply           = false;
    private boolean    skipApplierException = false;  // 是否允许跳过applier异常
    private String     sourceEncoding       = "UTF-8";
    private String     targetEncoding       = "UTF-8";

    private DefaultMQProducer rocketMQProducer;

    // 同步到ES时的rocketmq的name server地址和elasticsearch cluster地址
    private String rocketMQNameServerAddr;
    private String elasticClusterAddr;

    public Position getLastPosition() {
        return lastPosition;
    }

    public void setLastPosition(Position lastPosition) {
        this.lastPosition = lastPosition;
    }

    public int getOnceCrawNum() {
        return onceCrawNum;
    }

    public void setOnceCrawNum(int onceCrawNum) {
        this.onceCrawNum = onceCrawNum;
    }

    public void setSourceDbType(DbType sourceDbType) {
        this.sourceDbType = sourceDbType;
    }

    public DbType getSourceDbType() {
        return sourceDbType;
    }

    public void setTargetDbType(DbType targetDbType) {
        this.targetDbType = targetDbType;
    }

    public DbType getTargetDbType() {
        return targetDbType;
    }

    public DataSource getSourceDs() {
        return sourceDs;
    }

    public void setSourceDs(DataSource sourceDs) {
        this.sourceDs = sourceDs;
    }

    public DataSource getTargetDs() {
        return targetDs;
    }

    public void setTargetDs(DataSource targetDs) {
        this.targetDs = targetDs;
    }

    public boolean isBatchApply() {
        return batchApply;
    }

    public void setBatchApply(boolean batchApply) {
        this.batchApply = batchApply;
    }

    public String getSourceEncoding() {
        return sourceEncoding;
    }

    public void setSourceEncoding(String sourceEncoding) {
        this.sourceEncoding = sourceEncoding;
    }

    public String getTargetEncoding() {
        return targetEncoding;
    }

    public void setTargetEncoding(String targetEncoding) {
        this.targetEncoding = targetEncoding;
    }

    public Table getTableMeta() {
        return tableMeta;
    }

    public void setTableMeta(Table tableMeta) {
        this.tableMeta = tableMeta;
    }

    public int getTpsLimit() {
        return tpsLimit;
    }

    public void setTpsLimit(int tpsLimit) {
        this.tpsLimit = tpsLimit;
    }

    public RunMode getRunMode() {
        return runMode;
    }

    public void setRunMode(RunMode runMode) {
        this.runMode = runMode;
    }

    public boolean isIgnoreSchema() {
        return ignoreSchema;
    }

    public void setIgnoreSchema(boolean ignoreSchema) {
        this.ignoreSchema = ignoreSchema;
    }

    public boolean isSkipApplierException() {
        return skipApplierException;
    }

    public void setSkipApplierException(boolean skipApplierException) {
        this.skipApplierException = skipApplierException;
    }

    public void setRocketMQNameServerAddr(String addr) {
        this.rocketMQNameServerAddr = addr;
    }

    public String getRocketMQNameServerAddr() {
        return this.rocketMQNameServerAddr;
    }

    public void setElasticClusterAddr(String addr) {
        this.elasticClusterAddr = addr;
    }

    public String getElasticClusterAddr() {
        return this.elasticClusterAddr;
    }

    public void setMQProducer(DefaultMQProducer producer) {
        this.rocketMQProducer = producer;
    }

    public DefaultMQProducer getMQProducer() {
        return this.rocketMQProducer;
    }

    public YuGongContext cloneGlobalContext() {
        YuGongContext context = new YuGongContext();
        context.setRunMode(runMode);
        context.setBatchApply(batchApply);
        context.setSourceDbType(sourceDbType);
        context.setTargetDbType(targetDbType);
        context.setSourceDs(sourceDs);
        context.setTargetDs(targetDs);
        context.setSourceEncoding(sourceEncoding);
        context.setTargetEncoding(targetEncoding);
        context.setOnceCrawNum(onceCrawNum);
        context.setTpsLimit(tpsLimit);
        context.setIgnoreSchema(ignoreSchema);
        context.setSkipApplierException(skipApplierException);
        context.setRocketMQNameServerAddr(rocketMQNameServerAddr);
        context.setElasticClusterAddr(elasticClusterAddr);
        context.setMQProducer(rocketMQProducer);
        return context;
    }

}