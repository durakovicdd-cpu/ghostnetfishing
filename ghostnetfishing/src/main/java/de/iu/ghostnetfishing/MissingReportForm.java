package de.iu.ghostnetfishing;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class MissingReportForm {

    @NotNull(message = "Netz-ID ist erforderlich.")
    private Long netId;

    @NotBlank(message = "Der Name der meldenden Person ist bei einer Verschollen-Meldung erforderlich.")
    private String reporterName;

    @NotBlank(message = "Die Telefonnummer ist bei einer Verschollen-Meldung erforderlich.")
    private String reporterPhone;

    // Getter & Setter
    public Long getNetId() { return netId; }
    public void setNetId(Long netId) { this.netId = netId; }

    public String getReporterName() { return reporterName; }
    public void setReporterName(String reporterName) { this.reporterName = reporterName; }

    public String getReporterPhone() { return reporterPhone; }
    public void setReporterPhone(String reporterPhone) { this.reporterPhone = reporterPhone; }
}