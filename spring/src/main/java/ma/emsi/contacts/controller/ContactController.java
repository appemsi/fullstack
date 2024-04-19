package ma.emsi.contacts.controller;

import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import ma.emsi.contacts.model.Contact;
import ma.emsi.contacts.repository.ContactRepository;

@RestController
@CrossOrigin("*")
public class ContactController {
	
	@Autowired
	private ContactRepository contactRepository;
	private final String storageDirectoryPath = Paths.get("uploaded-images").toAbsolutePath().toString();

	/*@PostMapping("/contact")
	public Contact newContact(@RequestBody Contact newcontact)
	{
		return contactRepository.save(newcontact);
	}*/
	
	 @PostMapping(value = "/contact", consumes = {"multipart/form-data"})
	 public Contact newContact(@RequestParam("name") String name,
             @RequestParam("number") String number,
             @RequestParam("email") String email,
             @RequestParam("note") String note,
             @RequestParam("image") MultipartFile file) throws IOException {
			// Assuming the server is running on localhost:8000
			String baseUrl = "http://localhost:8080/uploaded-images/";
			String filename = StringUtils.cleanPath(file.getOriginalFilename());
			
			Path storageDirectory = Paths.get(storageDirectoryPath);
			if (!Files.exists(storageDirectory)) {
			Files.createDirectories(storageDirectory);
			}
			
			Path destinationPath = storageDirectory.resolve(Path.of(filename));
			file.transferTo(destinationPath);
			
			Contact newContact = new Contact();
			newContact.setName(name);
			newContact.setNumber(number);
			newContact.setEmail(email);
			newContact.setNote(note);
			newContact.setImage(baseUrl + filename);  // Save the URL instead of the path
			return contactRepository.save(newContact);
			}
	
	@GetMapping("/contacts")
	public List<Contact> getAll()
	{
		return contactRepository.findAll();//select * from contacts
	}
	
	@GetMapping("/contact/{id}")
	Optional<Contact> getConatctById(@PathVariable long id)
	{
		return contactRepository.findById(id);
	}
	
	@PutMapping("/contact/{id}")
	Optional<Contact> updateContact(@RequestBody Contact newContact,@PathVariable long id)
	{
		return contactRepository.findById(id).map(contact->{
				if(newContact.getName()!=null)
				contact.setName(newContact.getName());
				if(newContact.getEmail()!=null)
				contact.setEmail(newContact.getEmail());
				if(newContact.getNumber()!=null)
				contact.setNumber(newContact.getNumber());
				if(newContact.getNote()!=null)
				contact.setNote(newContact.getNote());
				if(newContact.getImage()!=null)
				contact.setImage(newContact.getImage());
				return contactRepository.save(contact); // Save the updated Contact
		});
	}
	
	@DeleteMapping("/contact/{id}")
	String deleteContact(@PathVariable long id)
	{
		if(!contactRepository.existsById(id))
		{
			return "Not found";
		}
		
		contactRepository.deleteById(id);
		return "Contact with id "+id+" has been deleted";
	}

}
