package com.microsoft.microsofttest;

import org.springframework.stereotype.Component;

@Component
public class ConfigProvider {

  private String filePath;

  public ConfigProvider() {
    filePath = "classpath:Mobile_Food_Facility_Permit.csv";
  }

  public ConfigProvider(String filePath) {
    this.filePath = filePath;
  }

  public String getFilePath() {
    return filePath;
  }

  public void setFilePath(String filePath) {
    this.filePath = filePath;
  }
}
