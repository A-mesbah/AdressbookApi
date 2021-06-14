package de.inmediasp.adressBook.service;
import de.inmediasp.adressBook.exception.ApiRequestException;
import de.inmediasp.adressBook.model.ContactEntity;
import de.inmediasp.adressBook.model.ContactDTO;
import de.inmediasp.adressBook.repository.ContactRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactServiceImp implements ContactService {
    private final ContactRepo contactRepo;
    private final ModelMapper modelMapper;

    @Autowired
    public ContactServiceImp(ContactRepo contactRepo, ModelMapper modelMapper) {

        this.contactRepo = contactRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ContactDTO> getAllContacts() {

        return contactRepo.findAll()
                .stream()
                .map(contactEntity -> modelMapper.map(contactEntity, ContactDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ContactDTO getContact(Long id) {
        return (
                contactRepo.findById(id).map(contactEntity -> modelMapper.map(contactEntity, ContactDTO.class)))
                .orElseThrow(() -> new ApiRequestException("there is no id with this contact "));
    }

    @Override
    public void addContact(ContactEntity contact) {
        contactRepo.save(contact);
    }

    @Override
    public void addListContacts(List<ContactEntity> contacts) {
        contacts.stream().map(contactEntity -> contactRepo.save(contactEntity))
                .findAny()
                .orElseThrow(() -> new ApiRequestException("There was a false Entered Data "));

    }

    @Override
    public void updateContact(ContactEntity contact) {
        contactRepo.save(contact);
    }

    @Override
    public void deleteContact(Long id) {
        ContactDTO contactDTO = getContact(id);
        contactRepo.deleteById(id);
    }
}

