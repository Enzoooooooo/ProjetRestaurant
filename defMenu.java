public class defMenu {
    public static Menu createMenu() {
        Menu menu = new Menu();

        // Créer des plats
        Plat plat1 = new Plat();
        plat1.setName("Salades");
        plat1.addIngredient("Salade");
        plat1.setPrix(9f);
        menu.ajouterPlat(plat1);

        Plat plat2 = new Plat();
        plat2.setName("Salade avec des Tomates");
        plat2.addIngredient("Salade");
        plat2.addIngredient("Tomate");
        plat2.setPrix(9f);
        menu.ajouterPlat(plat2);

        Plat plat3 = new Plat();
        plat3.setName("Potage potirons");
        plat3.addIngredient("Potirons");
        plat3.addIngredient("Potirons");
        plat3.addIngredient("Potirons");
        plat3.setPrix(8f);
        menu.ajouterPlat(plat3);

        Plat plat4 = new Plat();
        plat4.setName("Potage tomates");
        plat4.addIngredient("Tomate");
        plat4.addIngredient("Tomate");
        plat4.addIngredient("Tomate");
        plat4.setPrix(8f);
        menu.ajouterPlat(plat4);

        Plat plat5 = new Plat();
        plat5.setName("Potage Champignon");
        plat5.addIngredient("Champignons");
        plat5.addIngredient("Champignons");
        plat5.addIngredient("Champignons");
        plat5.setPrix(8f);
        menu.ajouterPlat(plat5);

        Plat plat6 = new Plat();
        plat6.setName("Burger (pain viande)");
        plat6.addIngredient("Pain");
        plat6.addIngredient("Viande");
        plat6.setPrix(15f);
        menu.ajouterPlat(plat6);

        Plat plat7 = new Plat();
        plat7.setName("Burger (pain Salade viande)");
        plat7.addIngredient("Pain");
        plat7.addIngredient("Salade");
        plat7.addIngredient("Viande");
        plat7.setPrix(15f);
        menu.ajouterPlat(plat7);

        Plat plat8 = new Plat();
        plat8.setName("Burger (pain Salade  Tomates viande)");
        plat8.addIngredient("Pain");
        plat8.addIngredient("Salade");
        plat8.addIngredient("Tomate");
        plat5.addIngredient("Viande");
        plat8.setPrix(15f);
        menu.ajouterPlat(plat8);

        Plat plat9 = new Plat();
        plat9.setName("Pizza margharita");
        plat9.addIngredient("Pate");
        plat9.addIngredient("Tomate");
        plat9.addIngredient("Fromage");
        plat9.setPrix(12f);
        menu.ajouterPlat(plat9);

        Plat plat10 = new Plat();
        plat10.setName("Pizza Champignon");
        plat10.addIngredient("Champignons");
        plat10.addIngredient("Fromage");
        plat10.addIngredient("Tomate");
        plat10.addIngredient("Pate");
        plat10.setPrix(12f);
        menu.ajouterPlat(plat10);

        Plat plat11 = new Plat();
        plat11.setName("Pizza Chorizo");
        plat11.addIngredient("Saucisson");
        plat11.addIngredient("Fromage");
        plat11.addIngredient("Tomate");
        plat11.addIngredient("Pate");
        plat11.setPrix(12f);
        menu.ajouterPlat(plat11);

        // Créer des boissons
        Boisson boisson1 = new Boisson();
        boisson1.setName("Limonade");

        boisson1.setPrix(4.00f);
        menu.ajouterBoisson(boisson1);

        Boisson boisson2 = new Boisson();
        boisson2.setName("Cidre doux");
        boisson2.setPrix(5f);
        menu.ajouterBoisson(boisson2);

        Boisson boisson3 = new Boisson();
        boisson3.setName("Biere");
        boisson3.setPrix(5f);
        menu.ajouterBoisson(boisson3);

        Boisson boisson4 = new Boisson();
        boisson4.setName("Jus de fruit");
        boisson4.setPrix(1f);
        menu.ajouterBoisson(boisson4);

        Boisson boisson5 = new Boisson();
        boisson5.setName("Verre d'eau");
        boisson5.setPrix(1f);
        menu.ajouterBoisson(boisson5);

        return menu;
    }
}
