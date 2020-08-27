package com.example.demo.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreService {
     
    @Autowired
    StoreRepository repository;
     
    public List<StoreEntity> getAllStoreData()
    {
        List<StoreEntity> storeList = repository.findAll();
         
        if(storeList.size() > 0) {
            return storeList;
        } else {
            return new ArrayList<StoreEntity>();
        }
    }
     
    public StoreEntity getStoreById(Long id) throws RecordNotFoundException 
    {
        Optional<StoreEntity> storeRecord = repository.findById(id);
         
        if(storeRecord.isPresent()) {
            return storeRecord.get();
        } else {
            throw new RecordNotFoundException("No employee record exist for given id");
        }
    }
     
    public StoreEntity createOrUpdateStore(StoreEntity entity) throws RecordNotFoundException 
    {
       Optional<StoreEntity> storeRecord = repository.findById(entity.getId());
         
        if(storeRecord.isPresent()) 
        {
        	StoreEntity newEntity = storeRecord.get();
        	newEntity.setData(entity.getData());
 
           newEntity = repository.save(newEntity);
             
            return newEntity;
       } else {
            entity = repository.save(entity);
             
            return entity;
        }
    } 
     
    public void deleteStoreById(Long id) throws RecordNotFoundException 
    {
        Optional<StoreEntity> storeRecord = repository.findById(id);
         
        if(storeRecord.isPresent()) 
        {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No store record exist for given id");
        }
    } 
}
