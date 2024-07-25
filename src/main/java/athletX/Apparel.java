package athletX;

import java.util.Arrays;
import java.util.Objects;

/**
 * An apparel is a product with the following options:
 * Gender: Men or Women
 * Type: Shirts, Pants, Shorts, Jackets
 * Size: Small, Medium, or Large
*/

public class Apparel extends Product {

    public enum ApparelCategory {
        SHIRTS("SHIRTS"), PANTS("PANTS"), SHORTS("SHORTS"), JACKETS("JACKETS");

        private String name;
        ApparelCategory(String name)
        {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    private String gender, size, colour;
    private ApparelCategory type;

    public Apparel(String name, int prodId, int stock, double price, String size, String colour, String gender,
            ApparelCategory type, String imageDir) {
        super(name, prodId, stock, price, Product.ProductCategory.APPAREL, imageDir);
        this.size = size;
        this.colour = colour;
        this.gender = gender;
        this.type = type;
    }
    //Testing
    public ApparelCategory getType() {
        return type;
    }

    public void setType(ApparelCategory type) {
        this.type = type;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Apparel apparel = (Apparel) o;
        return this.getName().equals(apparel.getName()) &&
                this.getStock() == apparel.getStock() &&
                getPrice() == apparel.getPrice() &&
                this.getSize().equalsIgnoreCase(apparel.getSize()) &&
                this.getColour().equalsIgnoreCase(apparel.getColour()) &&
                this.gender.equalsIgnoreCase(apparel.getGender()) &&
                this.type == apparel.getType() &&
                Arrays.equals(this.getSupImageDir(), apparel.getSupImageDir());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGender(), getSize(), getColour(), getType());
    }
}
