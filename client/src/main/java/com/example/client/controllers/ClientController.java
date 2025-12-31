package com.example.client.controllers;

import com.example.client.entities.Client;
import com.example.client.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/client")
public class ClientController {
    @Autowired
    private ClientService service;

    @GetMapping
    public List<Client> listAllClients() {
        return service.listAllClients();
    }

    @GetMapping("/{id}")
    public Client retrieveClient(@PathVariable Long id) throws Exception {
        return service.retrieveClient(id);
    }

    @PostMapping
    public void register(@RequestBody Client client) {
        service.registerClient(client);
    }
}