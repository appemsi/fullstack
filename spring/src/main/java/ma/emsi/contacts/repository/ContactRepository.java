package ma.emsi.contacts.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.emsi.contacts.model.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> 
{

}


