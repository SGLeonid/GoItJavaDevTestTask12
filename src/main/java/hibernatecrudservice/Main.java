package hibernatecrudservice;

import hibernatecrudservice.data.Client;
import hibernatecrudservice.data.Planet;
import hibernatecrudservice.services.ClientCrudService;
import hibernatecrudservice.services.DatabaseInitService;
import hibernatecrudservice.services.PlanetCrudService;

import java.util.List;
import java.util.logging.Logger;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        // Do database migrations

        try {
            DatabaseInitService.init();
        } catch (DatabaseException e) {
            LOGGER.severe(e.getMessage());
            return;
        }
        LOGGER.info("Migration complete");

        // Test ClientCrudService

        ClientCrudService clientService = new ClientCrudService();
        try {
            // Create new client

            Client client = new Client();
            client.setName("TestClient2");
            long id = clientService.create(client);

            // Read client

            client = clientService.read(id);
            LOGGER.info("Created and read Client " + id + ": " + client);

            // Update client

            client.setName("TestClientUpdated");
            clientService.update(client);
            client = clientService.findByName("TestClientUpdated");
            LOGGER.info("Updated and read Client " + id + ": " + client);

            // Delete client

            clientService.delete(client);
            List<Client> clients = clientService.findAll();
            LOGGER.info("Clients after delete: " + clients);
        } catch (DatabaseException e) {
            LOGGER.severe(e.getMessage());
        }

        // Test PlanetCrudService

        PlanetCrudService planetService = new PlanetCrudService();
        try {
            // Create new planet

            Planet planet = new Planet("PROXIMA", "Proxima Centauri B");
            String planetId = planetService.create(planet);

            // Read planet

            planet = planetService.read(planetId);
            LOGGER.info("Created and read Planet " + planetId + ": " + planet);

            // Update planet

            planet.setName("Proxima Centauri B Updated");
            planetService.update(planet);
            planet = planetService.findByName("Proxima Centauri B Updated");
            LOGGER.info("Updated and read Planet " + planetId + ": " + planet);

            // Delete planet

            planetService.delete(planet);
            List<Planet> planets = planetService.findAll();
            LOGGER.info("Clients after delete: " + planets);
        } catch (DatabaseException e) {
            LOGGER.severe(e.getMessage());
        }

        LOGGER.info("DONE");
    }
}
