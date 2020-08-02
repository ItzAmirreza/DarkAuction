package Utils;

import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;

public final class DAItem {

    ItemStack itemStack;
    Item itemobj;
    int price;
    int modifier;

    public DAItem(ItemStack itemStack, int price, int modifier) {

        this.itemStack = itemStack;
        this.price = price;
        this.itemobj = null;
        this.modifier = modifier;
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

    public int getModifier() {
        return modifier;
    }
}
