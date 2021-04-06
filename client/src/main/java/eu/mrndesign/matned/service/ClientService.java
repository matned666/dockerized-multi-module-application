package eu.mrndesign.matned.service;

import eu.mrndesign.matned.dto.ClientDTO;
import eu.mrndesign.matned.model.ClientEntity;
import eu.mrndesign.matned.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.rmi.ServerError;
import java.util.List;
import java.util.stream.Collectors;

import static eu.mrndesign.matned.utils.ErrorMessages.CLIENT_NOT_FOUND;

@Service
public class ClientService extends BaseService{

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<ClientDTO> findAllClients(Integer page, Integer itemsPerPage, String[] sortBy) throws ServerError {
        return clientRepository.findAll(getPageable(page, itemsPerPage, sortBy))
                .getContent()
                .stream()
                .map(ClientDTO::apply)
                .collect(Collectors.toList());
    }

    public ClientDTO findClientById(Long id) throws ServerError {
        return ClientDTO.apply(getClientBy(id));
    }

    public ClientDTO saveClient(ClientDTO data) throws ServerError {
        return ClientDTO.apply(clientRepository.save(ClientEntity.create(data)));
    }

    public ClientDTO editClient(Long id, ClientDTO data) throws ServerError {
        ClientEntity toEdit = getClientBy(id);
        toEdit.applyChangesFrom(data);
        return ClientDTO.apply(clientRepository.save(toEdit));
    }




    private ClientEntity getClientBy(Long id) throws ServerError {
        return clientRepository.findById(id).orElseThrow(() -> new ServerError(CLIENT_NOT_FOUND, new Error()));
    }

}