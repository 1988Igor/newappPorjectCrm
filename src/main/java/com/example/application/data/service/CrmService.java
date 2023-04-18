package com.example.application.data.service;


import com.example.application.data.entity.Contact;
import com.example.application.data.repository.ContactRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CrmService {
    private final ContactRepository contactRepository;

    public CrmService (ContactRepository contactRepository, ContactRepository contactRepository1){


        this.contactRepository = contactRepository;
    }

    public List<Contact> findAllContacts(String filterText){
        if(filterText == null || filterText.isEmpty()){
            return  contactRepository.findAll();
        } else{
            return contactRepository.search(filterText);
        }
    }

    public long countContacts(){
        return  contactRepository.count();
    }

    public void deleteContact(Contact contact){
        contactRepository.delete(contact);
    }

    public  void saveContact(Contact contact){
        if(contact == null){
            System.err.println("Contact is null. ");
            return;
        }

        contactRepository.save(contact);
    }


}
