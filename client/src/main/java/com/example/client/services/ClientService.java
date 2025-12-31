package com.example.client.services;

import com.example.client.entities.Client;
import com.example.client.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> listAllClients() {
        return clientRepository.findAll();
    }

    public Client retrieveClient(Long id) throws Exception {
        return clientRepository.findById(id).orElseThrow(() -> new Exception("Client not found with ID: " + id));
    }

    public void registerClient(Client client) {
        clientRepository.save(client);
    }
}
