package Utils;

import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;

public final class DAItem {

    ItemStack itemStack;
    Item itemobj;
    int price;

    public DAItem(ItemStack itemStack, int price) {

        this.itemStack = itemStack;
        this.price = price;
        this.itemobj = null;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public int getPrice() {
        return price;
    }

    public void setItem(Item item) {
        this.itemobj = item;
    }

    public Item getItem() {
        return itemobj;
    }
}
