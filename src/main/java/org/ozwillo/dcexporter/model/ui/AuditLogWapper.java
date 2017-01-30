package org.ozwillo.dcexporter.model.ui;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;
import org.ozwillo.dcexporter.model.DcModelMapping;
import org.ozwillo.dcexporter.model.SynchronizerAuditLog;

import javax.validation.constraints.NotNull;

public class AuditLogWapper {

    @JsonProperty
    @NotNull
    @NotEmpty
    DcModelMapping dcModelMapping;

    @JsonProperty
    @NotNull
    @NotEmpty
    SynchronizerAuditLog synchronizerAuditLog;

    public DcModelMapping getDcModelMapping() {
        return dcModelMapping;
    }

    public void setDcModelMapping(DcModelMapping dcModelMapping) {
        this.dcModelMapping = dcModelMapping;
    }

    public SynchronizerAuditLog getSynchronizerAuditLog() {
        return synchronizerAuditLog;
    }

    public void setSynchronizerAuditLog(SynchronizerAuditLog synchronizerAuditLog) {
        this.synchronizerAuditLog = synchronizerAuditLog;
    }
}
