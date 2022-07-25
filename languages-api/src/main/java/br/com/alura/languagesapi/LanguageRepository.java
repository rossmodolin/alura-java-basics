package br.com.alura.languagesapi;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface LanguageRepository extends MongoRepository<Language, String> {
    
}
