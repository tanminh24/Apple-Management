package com.assignment.services;

import java.io.File;

import org.jobrunr.jobs.annotations.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BgJobService {
    
    @Autowired
    ExcelImportService excelImport;
    
    @Job(name = "Import data from excel")
    public void saveFromExcel(File file) {
        try {
            excelImport.readExcel(file);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Import from excel successfully!");
        }
    }
    
}
