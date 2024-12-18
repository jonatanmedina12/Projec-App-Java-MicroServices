package com.listener.report_listener.repository;

import com.listener.report_listener.documents.ReportDocuments;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReportRepository extends MongoRepository<ReportDocuments,String> {





}
