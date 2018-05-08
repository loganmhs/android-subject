package com.example.mhs.memorandum;

import org.litepal.crud.DataSupport;

public class Summary extends DataSupport{
    private String summaryName;
    private String content;
    public Summary(String summary,String content){
        this.content=content;
        this.summaryName=summary;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setSummaryName(String summary) {
        this.summaryName = summary;
    }

    public String getSummaryName() {
        return summaryName;
    }
}