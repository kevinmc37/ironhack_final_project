package com.ironhack.final_project.demo;

import com.ironhack.final_project.enums.Equipable;
import com.ironhack.final_project.model.*;
import com.ironhack.final_project.repository.EffectRepository;
import com.ironhack.final_project.repository.InventoryRepository;
import com.ironhack.final_project.repository.ItemRepository;
import com.ironhack.final_project.repository.PlayerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GameDataInitializer implements CommandLineRunner {
    private final EffectRepository effectRepository;
    private final ItemRepository itemRepository;
    private final InventoryRepository inventoryRepository;
    private final PlayerRepository playerRepository;

    public GameDataInitializer(EffectRepository effectRepository, ItemRepository itemRepository,
                               InventoryRepository inventoryRepository, PlayerRepository playerRepository) {
        this.effectRepository = effectRepository;
        this.itemRepository = itemRepository;
        this.inventoryRepository = inventoryRepository;
        this.playerRepository = playerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Effect effect1 = new Effect();
        effect1.setDescription("Nada especial.");
        Effect effect2 = new Effect();
        effect2.setDescription("Ralentiza al objetivo.");
        effect2.setTurns(5);

        List<Effect> effects = new ArrayList<>(List.of(effect1, effect2));
        effectRepository.saveAll(effects);

        Equipment weapon = new Equipment();
        weapon.setName("Bastón de Hielo Eterno");
        weapon.setAttack(72);
        weapon.setQuantity(1);
        weapon.setPrice(3300);
        weapon.setWeight(2.6);
        weapon.setDescription("Forjado en las profundidades de una antigua\n" +
                "caverna glacial, el Bastón de Hielo Eterno canaliza el poder\n" +
                "primigenio del invierno. Este bastón mágico emite un frío\n" +
                "perpetuo que congela el aire, permitiendo lanzar proyectiles\n" +
                "de hielo que ralentizan a los enemigos.");
        weapon.setEquipmentDescription();
        weapon.setEffect(List.of(effect2));
        weapon.setType(Equipable.WEAPON);

        Equipment armor = new Equipment();
        armor.setName("Armadura de Telaraña Prístina");
        armor.setDefense(33);
        armor.setSpeed(25);
        armor.setQuantity(1);
        armor.setPrice(3300);
        armor.setWeight(15.8);
        armor.setDescription("Esta armadura ligera está tejida cuidadosamente con" +
                "hilos de Telaraña Prístina, combinando flexibilidad y resistencia" +
                "en perfecta armonía. Proporciona una protección sólida contra" +
                "ataques físicos sin sacrificar la movilidad.");
        armor.setEquipmentDescription();
        armor.setEffect(List.of(effect1));
        armor.setType(Equipable.ARMOR);

        Item spider_key = new Item();
        spider_key.setName("Llave de la Araña");
        spider_key.setQuantity(1);
        spider_key.setPrice(3300);
        spider_key.setWeight(0.5);
        spider_key.setDescription("Llave hecha de telarañas. Sirve para abrir la" +
                "Mazmorra de la Araña en el Bosque Sombrío.");
        spider_key.setAdditionalDescription();
        spider_key.setEffect(List.of(effect1));

        Resource resource = new Resource();
        resource.setName("Telaraña Prístina");
        resource.setPrice(150);
        resource.setQuantity(20);
        resource.setWeight(0.1);
        resource.setDescription("Una fina y resistente telaraña recolectada de" +
                "las arañas místicas del Bosque Sombrío. Utilizada en la" +
                "fabricación de armaduras livianas y llaves de araña.");
        resource.setAdditionalDescription();
        resource.setEffect(List.of(effect1));
        resource.setRecipe(List.of(armor, spider_key));

        List<Item> items = new ArrayList<>(List.of(weapon, armor, resource, spider_key));
        itemRepository.saveAll(items);

        Market market = new Market();
        market.setSpace(10000);
        market.setItems(List.of(resource));
        Player system = new Player();
        market.setPlayer(system);

        Inventory inventory = new Inventory();
        inventory.setSpace(20);
        inventory.setItems(List.of(armor, weapon));
        Player player = new Player();
        inventory.setPlayer(player);

        List<Inventory> inventories = new ArrayList<>(List.of(market, inventory));
        inventoryRepository.saveAll(inventories);

        system.setName("Sistema");
        system.setHealth(1);
        system.setAttack(1);
        system.setDefense(1);
        system.setSpeed(1);
        system.setInventory(market);
        system.setGold(1000000000);
        system.setWeight(0);
        system.setStatus(List.of(effect1));

        player.setName("Player1");
        player.setHealth(1200);
        system.setAttack(80);
        system.setDefense(65);
        system.setSpeed(40);
        system.setInventory(inventory);
        system.setGold(27542);
        system.setWeight(inventory.getWeight());
        system.setStatus(List.of(effect1));

        List<Player> players = new ArrayList<>(List.of(system, player));
        playerRepository.saveAll(players);

        System.out.println("Effects, items, inventories and players created.");
    }
}
