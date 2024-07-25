package athletX;

import java.util.Arrays;
import java.util.Objects;

/**
 * An accessory is a product with the following options:
 * Type: Bag, Glove, or Sock
 * They are one size fits all so no need for size
 * They are also unisex so there is no need for gender either
*/

public class Accessories extends Product {

    public enum AccessoryCategory {
        BAGS, GLOVES, SOCKS, OTHER
    }

    private AccessoryCategory type;

    public Accessories(String name, int prodId, int stock, double price, AccessoryCategory type, String imageDir) {
        super(name, prodId, stock, price, Product.ProductCategory.ACCESSORIES, imageDir);
        this.type = type;
    }

    public AccessoryCategory getType() {
        return type;
    }

    public void setType(AccessoryCategory type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Accessories that = (Accessories) o;
        return Objects.equals(this.getName(), that.getName()) &&
                this.getStock() == that.getStock() &&
                this.getPrice() == that.getPrice() &&
                Arrays.equals(this.getSupImageDir(), that.getSupImageDir());
    }

}
