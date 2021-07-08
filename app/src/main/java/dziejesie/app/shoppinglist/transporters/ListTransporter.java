package dziejesie.app.shoppinglist.transporters;

import dziejesie.app.shoppinglist.models.ShoppingList;

public class ListTransporter {
    private static ShoppingList list;

    public static ShoppingList getList() {
        return list;
    }

    public static void setList(ShoppingList list) {
        ListTransporter.list = list;
    }
}
