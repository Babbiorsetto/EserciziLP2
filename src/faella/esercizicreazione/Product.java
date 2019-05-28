package faella.esercizicreazione;

import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * Un Product rappresenta un prodotto di supermercato caratterizzato da descrizione e prezzo.
 * I prodotti sono dotati di ordinamento naturale in base alla loro descrizione e di ordinamento
 * in base al prezzo fornito da un comparatore.
 * <p>Si deve tenere traccia del prodotto più costoso creato, da restituire in seguito a una
 * chiamata a getMostExpensive.
 *
 */
public class Product implements Comparable<Product> {

    // instance fields
    private String description;
    private double price;

    // static fields
    private static Product mostExpensive;

    /**
     * Comparatore che confronta i prodotti in base al prezzo.
     */
    public static final Comparator<Product> comparatorByPrice = (a, b) -> (a.price < b.price) ? -1 : (a.price == b.price) ? 0 : 1;


    public Product(String description, double price) {
        // usual constructor assigning parameters to instance fields
        this.description = description;
        this.price = price;

        // code to update mostExpensive Product on creation of any new Product
        // if it's the first Product created, it's the mostExpensive
        if (null == mostExpensive) {
            mostExpensive = this;
        // not the first and more expensive than mostExpensive, it becomes the mostExpensive
        } else if (this.price > mostExpensive.price) {
            mostExpensive = this;
        }
    }

    public int compareTo(Product other) {
        // demand the work to String::compareTo on description fields
        int check = this.description.compareTo(other.description);

        return (check < 0) ? -1 : (check == 0) ? 0 : 1;
    }

    /**
     * Ritorna il Product piu' costoso mai creato.
     */
    public static Product getMostExpensive() {
        // no Product has been created yet
        if (null == mostExpensive) {
            throw new NoSuchElementException();
        }

        return mostExpensive;
    }

    @Override
    public String toString() {
        return this.description + ", " + this.price;
    }

    public static void main(String[] args) {

        Product pizza = new Product("Pizza", 3.0);
        Product mandolino = new Product("Mandolino", 10.0);

        System.out.println(pizza.compareTo(mandolino));
        System.out.println(Product.comparatorByPrice.compare(pizza, mandolino));
        System.out.println(Product.getMostExpensive());
    }

}
