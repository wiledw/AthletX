package athletX;

import java.util.Arrays;
import java.util.Objects;

/**
 * A shoe is a product with the following options:
 * Gender: Men or Women
 * Size: 6, 7, 8, 9, or 10
 * Colour: Black, White, Blue, or Red
*/

public class Shoes extends Product {

    private String gender, size, colour;

    public Shoes(String name, int prodId, int stock, double price, String gender, String size, String colour, String imageDir) {
        super(name, prodId, stock, price, Product.ProductCategory.SHOES, imageDir);
        this.gender = gender;
        this.size = size;
        this.colour = colour;
    }

    /**
     * @return Shoe gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * Set the shoe gender
     * @param gender of the shoe to be set to
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return Shoe size
     */
    public String getSize() {
        return size;
    }

    /**
     * Set shoe size
     * @param size of shoe
     */
    public void setSize(String size) {
        this.size = size;
    }

    /**
     * @return Colour of shoe
     */
    public String getColour() {
        return colour;
    }

    /**
     * Sets colour of shoe
     * @param colour of shoe
     */
    public void setColour(String colour) {
        this.colour = colour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shoes shoes = (Shoes) o;
        return this.getName().equals(shoes.getName()) &&
                this.getStock() == shoes.getStock() &&
                this.getPrice() == shoes.getPrice() &&
                this.getGender().equals(shoes.getGender()) &&
                this.getSize().equals(shoes.getSize()) &&
                this.getColour().equals(shoes.getColour()) &&
                Arrays.equals(this.getSupImageDir(), shoes.getSupImageDir());
    }

}
