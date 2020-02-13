package com.sally.HealthApp.data.repo;

import com.sally.HealthApp.data.model.LoginDocument;
import org.springframework.data.couchbase.repository.CouchbasePagingAndSortingRepository;

import java.util.Collection;


public interface LoginModelRepo extends CouchbasePagingAndSortingRepository<LoginDocument, String>, LoginModelCustomRepo {

    Collection<LoginDocument> findByEmail(String email);

    Collection<LoginDocument> findByUsername(String username);

}
