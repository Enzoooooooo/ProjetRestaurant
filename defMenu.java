public class defMenu {
    public static Menu createMenu() {
        Menu menu = new Menu();

        // Créer des plats
        Plat plat1 = new Plat();
        plat1.setName("Salades");
        plat1.setPrix(9f);
        menu.ajouterPlat(plat1);

        Plat plat2 = new Plat();
        plat2.setName("Salade avec des Tomates");
        plat2.setPrix(9f);
        menu.ajouterPlat(plat2);

        Plat plat3 = new Plat();
        plat3.setName("Potage potirons");
        plat3.setPrix(8f);
        menu.ajouterPlat(plat3);

        Plat plat4 = new Plat();
        plat4.setName("Potage tomates");
        plat4.setPrix(8f);
        menu.ajouterPlat(plat4);

        Plat plat5 = new Plat();
        plat5.setName("Potage Champignon");
        plat5.setPrix(8f);
        menu.ajouterPlat(plat5);

        Plat plat6 = new Plat();
        plat6.setName("Burger (pain viande)");
        plat6.setPrix(15f);
        menu.ajouterPlat(plat6);

        Plat plat7 = new Plat();
        plat7.setName("Burger (pain Salade viande)");
        plat7.setPrix(15f);
        menu.ajouterPlat(plat7);

        Plat plat8 = new Plat();
        plat8.setName("Burger (pain Salade  Tomates viande)");
        plat8.setPrix(15f);
        menu.ajouterPlat(plat8);

        Plat plat9 = new Plat();
        plat9.setName("Pizza margharita");
        plat9.setPrix(12f);
        menu.ajouterPlat(plat9);

        Plat plat10 = new Plat();
        plat10.setName("Pizza Champignon");
        plat10.setPrix(12f);
        menu.ajouterPlat(plat10);

        Plat plat11 = new Plat();
        plat11.setName("Pizza Chorizo");
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
        boisson3.setName("Bière sans alcool");
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
